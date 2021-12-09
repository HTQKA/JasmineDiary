package fly.xysimj.jasminediary.utils;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: XYSWorld
 * @ClassName MybatisUtil
 * @description: mybatis工具类
 * @author: 徐杨顺
 * @create: 2021-11-09 13:58
 * @Version 1.0
 **/
public class MybatisUtil {

    public static DataSource getUserDataSource(){
        return new PooledDataSource("com.mysql.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/tinyworld?characterEncoding=UTF-8",
                "root","2811107845");
    }

    static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputStream;
        try {
            String resource = "config/mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            Configuration config;
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        InputStream inputStream = null;
        try {
            String resource = "config/mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            return sqlSessionFactory;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    //    关闭资源流
    public static void closeSqlSession(SqlSession sqlSession) {

        sqlSession.close();

    }

}
