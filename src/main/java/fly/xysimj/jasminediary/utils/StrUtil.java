package fly.xysimj.jasminediary.utils;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @program: JasmineDiary
 * @ClassName StrUtil
 * @description:
 * @author: 徐杨顺
 * @create: 2022-07-06 10:29
 * @Version 1.0
 **/
public class StrUtil {

    private StrUtil() {
    }

    /**
     * 将string按需要格式化,前面加缩进符,后面加换行符
     *
     * @param tabNum 缩进量
     */
    public static String formatSingleLine(int tabNum, String srcString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabNum; i++) {
            sb.append("\t");
        }
        sb.append(srcString);
        sb.append("\n");
        return sb.toString();
    }

    public static String firstToUpperCase(String key) {
        return key.substring(0, 1).toUpperCase(Locale.CHINA) + key.substring(1);
    }

    public static String firstToLowerCase(String key) {
        return key.substring(0, 1).toLowerCase(Locale.CHINA) + key.substring(1);
    }

    public static String gapToCamel(String src) {
        StringBuilder sb = new StringBuilder();
        for (String s : src.trim().split(" ")) {
            sb.append(firstToUpperCase(s));
        }
        return sb.toString();
    }

    public static boolean hasChinese(String s) {
        String regexChinese = "[\u4e00-\u9fa5]+";
        Pattern patternChinese = Pattern.compile(regexChinese);
        return patternChinese.matcher(s).find();
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * 下划线命名转为驼峰命名
     * 划线命名的字符串
     */
    public static String underscoreToCamel(String underscoreStr) {
        if (underscoreStr == null) {
            return null;
        }
        // 分成数组
        char[] charArray = underscoreStr.toCharArray();
        // 判断上次循环的字符是否是"_"
        boolean underlineBefore = false;
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            // 判断当前字符是否是"_",如果跳出本次循环
            if (c == 95) {
                underlineBefore = true;
            } else if (underlineBefore) {
                // 如果为true，代表上次的字符是"_",当前字符需要转成大写
                sb.append((char) (c - 32));
                underlineBefore = false;
            } else {
                // 不是"_"后的字符就直接追加
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰命名转为下划线命名
     * 驼峰命名的字符串
     */
    public static String camelToUnderscore(String camelCaseStr) {
        if (camelCaseStr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 首字符小写
        camelCaseStr = firstToLowerCase(camelCaseStr);
        // 从第二个字符起将驼峰字符串转换成数组
        char[] charArray = camelCaseStr.toCharArray();
        //处理字符串
        for (char c : charArray) {
            if (c >= 65 && c <= 90) {
                sb.append("_").append((char) (c + 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
