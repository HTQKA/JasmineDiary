package fly.xysimj.jasminediary.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.Annotation;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @program: JasmineDiary
 * @ClassName IUtils
 * @description: UUID自动生成主键
 * @author: 徐杨顺
 * @create: 2022-06-30 13:17
 * @Version 1.0
 **/

public class IUtils {
    private static Log log = LogFactory.getLog(IUtils.class);

    private final static String[] simpleClass = { "java.lang.String", "java.lang.Double", "java.lang.Integer",
            "java.lang.Float", "java.lang.Lang", "java.lang.Number", "java.lang.Short" };

    /**
     * JAVA自带UUID生成器,推荐使用
     *
     * @return
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * JAVA自带UUID生成器,只取前8位(时钟相关),,推荐使用
     *
     * @return
     */
    public static String getShortUUID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private static long seqId = System.currentTimeMillis() * 1000 + 1;

    public static synchronized long getNextID() {
        return seqId++;
    }

    /**
     * 生成固定长度整数
     *
     * @param len
     *            len 长度
     * @return 固定长度整数
     */
    public static int getRandomInt(int len) {
        Random r = new Random();
        return r.nextInt((int) Math.pow(10, len) - 1);
    }

    /**
     * 生成固定长度随机密码,把L大写以免混淆
     *
     * @param pwd_len
     *            生成的密码的总长度
     * @return 密码的字符串
     */
    public static String getRandomCode(int pwd_len) {
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        final char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', '~', '!', '@', '#', '$', '%', '^', '&', '*' };
        final int maxNum = str.length;
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 生成固定长度随机密码,把L大写以免混淆
     *
     * @param pwd_len
     *            生成的密码的总长度
     * @return 密码的字符串
     */
    public static String getRandomCodeSimple(int pwd_len) {
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        final char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9' };
        final int maxNum = str.length;
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 根据传入的参数,计算出下一个ID
     *
     * @param current_id
     *            当前ID
     * @param prefix
     *            前缀
     * @return NextID
     *
     *         比如传入(q000155,q)则返回q000156
     */
    public static String getNextID(String current_id, String prefix) {
        Long l = Long.parseLong(current_id.substring(prefix.length()));
        return prefix + String.format("%0" + (current_id.length() - prefix.length()) + "d", ++l);
    }

    /**
     * 根据当前传入的参数,计算出下一个ID
     *
     * <pre>
     * 规则(2位为例子,多位类推) <br />
     * 00->01 ... ->09->0A->0B...->0Y->0Z->10->11....->ZZ
     * </pre>
     *
     * <pre>
     * 只大写,传入小写也转换成大写
     * </pre>
     *
     * 2017年9月7日 下午5:37:58
     *
     * @Author kreo
     * @param current_id
     *            当前ID
     * @return
     */
    public static String getNextSpecialID(String current_id) {
        return getNextSpecialID(current_id, true);
    }

    /**
     * 根据当前传入的参数,计算出下一个ID
     *
     * <pre>
     * 规则(2位为例子,多位类推) <br />
     * 00->01 ... ->09->0A->0B...->0Y->0Z->10->11....->ZZ
     * </pre>
     *
     * <pre>
     * 只大写,传入小写也转换成大写
     * </pre>
     *
     * 2017年11月20日 上午9:22:00
     *
     * @Author kreo
     * @param current_id
     *            当前ID
     * @param numFirst
     *            是否数字优先,如果是true,则开始已数字优先,比如01-99为数字,然后从A0开始计数
     * @return
     */
    public static String getNextSpecialID(String current_id, boolean numFirst) {
        if (IStr.isBlank(current_id)) {
            return "10"; // 默认是2位,并从10开始
        } else {
            if (numFirst) { // 如果是数字优先,则
                String maxNum = IStr.repeat("9", current_id.length());
                String firstNoNum = IStr.repeat("A", current_id.length());
                if (maxNum.equals(current_id)) {
                    return firstNoNum;
                }
                char[] nCHars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
                char firstLetter = current_id.charAt(0);
                if (IArray.contains(nCHars, firstLetter)) {
                    return getNextID(current_id, "");
                }
            }
            // 转成大写
            current_id = current_id.toUpperCase();
            char[] chars = current_id.toCharArray();
            boolean carryFlag = true; // 进位标记
            for (int i = chars.length - 1; i >= 0; i--) {
                if (carryFlag) {
                    chars[i]++;
                    carryFlag = false;
                }
                if (chars[i] == 58) {
                    chars[i] = 65;
                } else if (chars[i] == 91) {
                    chars[i] = 48;
                    carryFlag = true;
                }
            }
            return new String(chars);
        }
    }

    /**
     * 检查obj是否为空(包括""和null)
     *
     * @param obj
     * @return 空返回true
     */
    public static boolean isEmpty(Object obj) {
        return obj == null || "".equals(obj.toString().trim());
    }

    /**
     * 检查str是否为空(包括""和null)
     *
     * @param str
     * @return 空返回true
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim()) ? true : false;
    }

    /**
     * 返回第一个非空元素
     *
     * @param obj
     * @return
     */
    public static Object getNull(Object obj, Object defaultValue) {
        return obj != null ? obj : defaultValue;
    }

    /**
     * 返回第一个非空元素
     *
     * @param values
     * @return
     */
    public static <T> T getNotNull(@SuppressWarnings("unchecked") final T... values) {
        if (values != null) {
            for (final T val : values) {
                if (val != null) {
                    return val;
                }
            }
        }
        return null;
    }

    /**
     * 把一个bean对象转换成Map
     *
     * @param bean
     * @return
     */
    public static Map<String, Object> getMapByBean(Object bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(bean.getClass());
            if (info != null) {
                java.beans.PropertyDescriptor pds[] = info.getPropertyDescriptors();
                for (java.beans.PropertyDescriptor pd : pds) {
                    String fieldName = pd.getName();
                    if (fieldName != null && !fieldName.equals("class")) {
                        java.lang.reflect.Method method = pd.getReadMethod();
                        try {
                            Object value = method.invoke(bean);
                            // System.out.println("[" +
                            // bean.getClass().getName() + "]." +
                            // pd.getName() + "("
                            // + pd.getPropertyType().getName() + ") = " +
                            // value);
                            if (!IUtils.isEmpty(value)) {
                                map.put(pd.getName(), value);
                                // System.out.println("PUT:" + pd.getName() +
                                // "=" + value);
                            }
                        } catch (Exception e) {
                            System.out.println("读取[" + pd.getName() + "]参数值异常!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 和上述相反,把map转换成bean
     *
     * @param bean
     * @param map
     * @return
     */
    public static Object getBeanByMap(Object bean, Map<String, Object> map) {
        try {
            java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(bean.getClass());
            if (info != null) {
                java.beans.PropertyDescriptor pds[] = info.getPropertyDescriptors();
                for (java.beans.PropertyDescriptor pd : pds) {
                    String fieldName = pd.getName();
                    if (fieldName != null && !fieldName.equals("class")) {
                        try {
                            for (Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator(); iterator
                                    .hasNext();) {
                                Map.Entry<String, Object> m = iterator.next();
                                if ((fieldName.toUpperCase()).equals(m.getKey().toString().toUpperCase())) {
                                    java.lang.reflect.Method method = pd.getWriteMethod();
                                    Object[] args = new Object[1];
                                    args[0] = m.getValue();
                                    method.invoke(bean, args);
                                }

                            }
                        } catch (Exception e) {
                            System.out.println("写入[" + pd.getName() + "]参数值异常!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Class<?> getClassInit(String class_name) {
        Class<?> c = null;
        try {
            if (!isEmpty(class_name)) {
                c = Class.forName(class_name);
            }
        } catch (Exception e) {
            log.error("Init Class Failed.", e);
        }
        return c;
    }

    /**
     * 从原有的list(存在树型结构)中,根据指定的mode返回id相关的节点,如中途出现异常,则直接返回原list
     *
     * @param list
     *            原list
     * @param id_col
     *            id在Bean Class的属性名
     * @param p_id_col
     *            parent_id在Bean Class的属性名
     * @param id
     *            需要查询的id
     * @param mode
     *            模式(0:只根据_c参数列出id本身,1:所有父节点,2:所有子节点,3:所有子节点及父节点)
     * @param _c
     *            是否包含节点本身
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getTreeNode(List list, String id_col, String p_id_col, String id, int mode, boolean _c) {
        try {
            List p_list = new ArrayList();
            List c_list = new ArrayList();
            List f_list = new ArrayList();
            Map<String, String> m_map = new HashMap<String, String>();
            Map<String, Object> o_map = new HashMap<String, Object>();
            String tmp_p = null, tmp_ = null;
            if (list == null || id_col == null || p_id_col == null || id == null) {
                return null;
            } else if (!(mode == 0 || mode == 1 || mode == 2 || mode == 3)) {
                mode = 0;
            }
            // 循环得到特定的map: map<id,p_id>
            for (int i = 0, length = list.size(); i < length; i++) {
                Object obj = list.get(i);
                java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(obj.getClass());
                if (info != null) {
                    for (java.beans.PropertyDescriptor pd : info.getPropertyDescriptors()) {
                        String fieldName = pd.getName();
                        if (fieldName != null && !fieldName.equals("class")) {
                            java.lang.reflect.Method method = pd.getReadMethod();
                            if (id_col.equals(fieldName)) {
                                tmp_ = (String) method.invoke(obj);
                            } else if (p_id_col.equals(fieldName)) {
                                tmp_p = (String) method.invoke(obj);
                            }
                        }
                    }
                    if (!isEmpty(tmp_) || !isEmpty(tmp_p)) {
                        m_map.put(tmp_, tmp_p);
                        o_map.put(tmp_, obj);
                    }
                }
            }
            if (m_map.containsKey(id)) {
                if (mode != 0) { // 如mode是0,则不用遍历
                    for (Map.Entry<String, String> m : m_map.entrySet()) {
                        tmp_ = m.getKey();
                        // tmp_p = m.getValue();
                        if (mode == 1 || mode == 3) {
                            if (checkParent(m_map, id, tmp_)) {
                                c_list.add(o_map.get(tmp_));
                            }
                        }
                        if (mode == 2 || mode == 3) {
                            if (checkParent(m_map, tmp_, id)) {
                                p_list.add(o_map.get(tmp_));
                            }
                        }
                    }
                }
                if (_c) {
                    f_list.add(o_map.get(id));
                }
                f_list.addAll(c_list);
                f_list.addAll(p_list);
            }
            return f_list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 在特定的map&lt;id:p_id&gt;中,用来判定是否存在从属关系,即判断id是否从属于p_id(可能包含多层),
     * id和p_id相同将返回false
     *
     * @param map
     *            特定格式的map,格式为&lt;id,p_id&gt;
     * @param id
     * @param p_id
     * @return
     */
    public static boolean checkParent(Map<String, String> map, String id, String p_id) {
        while (true) {
            if (map.containsKey(id)) {
                return p_id.equals(map.get(id)) || checkParent(map, map.get(id), p_id);
            } else {
                break;
            }
        }
        return false;
    }

    private static String getStackTrace(Exception e, String type) {
        String n = "", t = "";
        if ("HTML".equals(type)) {
            n = "<br />";
            t = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        } else if ("Text".equals(type)) {
            n = "\n";
            t = "\t";
        }
        StackTraceElement[] sts = e.getStackTrace();
        StringBuffer sb = new StringBuffer();
        sb.append(e.toString()).append(n);
        for (StackTraceElement st : sts) {
            sb.append(t).append("at ").append(st.toString()).append(n);
        }
        return sb.toString();
    }

    /**
     *
     * @param list
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void removeNullElement(List list) {
        list.removeAll(IConst.NULL_ELEMENT_LIST);
    }

    /**
     * 得到Exception抛出的错误字符串文本
     *
     * @param e
     * @return
     */
    public static String getStackTrace(Exception e) {
        return getStackTrace(e, "Text");
    }

    /**
     * 得到Exception抛出的错误字符串HTML(页面显示时用)
     *
     * @param e
     * @return
     */
    public static String getStackTraceHTML(Exception e) {
        return getStackTrace(e, "HTML");
    }

    /**
     * 得到正在运行的方法名
     *
     * @return
     */
    public static String getRuntimeMethodName() {
        return new Exception().getStackTrace()[1].getMethodName();
    }

    /**
     * 把List<String>转换成分隔符隔开的字符串
     *
     * @param list
     * @param sep
     *            分隔符
     * @return
     */
    public static String List2StringBySep(List<String> list, String sep) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, n = list.size(); i < n; i++) {
            sb.append(list.get(i)).append(sep);
        }
        sb.delete(sb.length() - sep.length(), sb.length());
        return sb.toString();
    }

    /**
     * 字符串数组转换成字符串,用split隔开
     *
     * @param list
     *            字符串数组
     * @param split
     *            分隔字符
     * @return
     */
    public static String List2String(List<String> list, String split) {
        if (!(list == null || list.size() == 0)) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0, n = list.size(); i < n; i++) {
                sb.append(list.get(i)).append(split);
            }
            sb.delete(sb.length() - split.length(), sb.length());
            return sb.toString();
        } else {
            return null;
        }
    }

    /**
     * 调试, 返回当前所有属性的值
     *
     * @date 2005-07-31
     * @author BeanSoft
     * @param bean
     */
    public static String dumpObject(Object bean) {
        if (bean == null) {
            return null;
        } else {
            // instanceof
            if (IStr.isIn(bean.getClass().getName(), IUtils.simpleClass) || bean instanceof Map
                    || bean instanceof Collections || bean instanceof Collection) {
                return bean.toString();
            } else {
                StringBuffer sb = new StringBuffer();
                java.beans.PropertyDescriptor[] descriptors = IUtils.getAvailablePropertyDescriptors(bean);
                sb.append(bean.getClass().getName()).append(":{");
                for (int i = 0; descriptors != null && i < descriptors.length; i++) {
                    java.lang.reflect.Method readMethod = descriptors[i].getReadMethod();

                    try {
                        Object value = readMethod.invoke(bean);
                        // Object value = readMethod.invoke(bean, null);
                        // System.out.println("[" + bean.getClass().getName() +
                        // "]." + descriptors[i].getName()+ "("+
                        // descriptors[i].getPropertyType().getName() + ") = " +
                        // value);
                        if (value != null) {
                            sb.append(descriptors[i].getName()).append(":").append(value).append(",");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                sb.append("}");
                return sb.toString();
            }
        }
    }

    // JavaBean 中遍历解析属性信息并查找相关的read/write 方法
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static java.beans.PropertyDescriptor[] getAvailablePropertyDescriptors(Object bean) {
        try {
            java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(bean.getClass());
            if (info != null) {
                java.beans.PropertyDescriptor pd[] = info.getPropertyDescriptors();
                Vector columns = new Vector();
                for (int i = 0; i < pd.length; i++) {
                    String fieldName = pd[i].getName();
                    if (fieldName != null && !fieldName.equals("class")) {
                        columns.add(pd[i]);
                    }
                }

                java.beans.PropertyDescriptor[] arrays = new java.beans.PropertyDescriptor[columns.size()];

                for (int j = 0; j < columns.size(); j++) {
                    arrays[j] = (PropertyDescriptor) columns.get(j);
                }

                return arrays;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return null;
    }

    public static List<Map<String, Object>> conventTable(List<Map<String, Object>> oTable, String[] p_key,
                                                         String[] v_key, String[] v_key_name, String v_key_title, String value_key) {
        return conventTable(oTable, p_key, v_key, v_key_name, v_key_title, value_key, true, null);
    }

    /**
     * 竖转横 注意,*参数都不能为null,如无参数,也需要new一个对象,比如 new String[0]
     *
     * @param oTable
     *            原数据
     * @param p_key
     *            元数据中,作为primary key的列表
     * @param v_key
     *            横转竖的key列表
     * @param v_key_name
     *            横转竖的key需要转换的名字(横列变成竖列后的显示名称)
     * @param v_key_title
     *            竖列名称(横列变成竖列后的key name)
     * @param value_key
     *            竖转横的key(竖列需变成横列的key列)
     * @return
     */
    public static List<Map<String, Object>> conventTable(List<Map<String, Object>> oTable, String[] p_key,
                                                         String[] v_key, String[] v_key_name, String v_key_title, String value_key, boolean isOverride,
                                                         String splitStr) {
        // 定义返回的变量
        Map<String, HashMap<String, Object>> nTable1 = new LinkedHashMap<String, HashMap<String, Object>>();
        Map<String, HashMap<String, Object>> nTable2 = new LinkedHashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> nMap; // 返回变量nTable的元素赋予变量

        String tmpKey;
        // 竖转横后,横列的列表
        HashSet<String> value_key_set = new HashSet<String>();

        // 第一次遍历oTable,得到新的Map Key和提取的value_key列表
        for (Iterator<Map<String, Object>> iterator = oTable.iterator(); iterator.hasNext();) {
            Map<String, Object> oMap = iterator.next();
            tmpKey = "";
            nMap = new HashMap<String, Object>();
            nMap.clear();
            for (int i = 0; i < p_key.length; i++) {
                Object to = oMap.get(p_key[i]);
                nMap.put(p_key[i], to);
                if (to instanceof java.lang.Number) {
                    java.lang.Number tn = (java.lang.Number) to;
                    tmpKey += frontCompWithZore(tn.doubleValue(), 20);
                } else {
                    tmpKey += to;
                }
            }
            nTable1.put(tmpKey, nMap);
            value_key_set.add(oMap.get(value_key).toString());
        }
        // 判断变量,判断是否对号
        boolean tmp_flag = true;

        // 第二次循环,开始填入数据
        for (Iterator<Map.Entry<String, HashMap<String, Object>>> iterator = nTable1.entrySet().iterator(); iterator
                .hasNext();) {
            Map.Entry<String, HashMap<String, Object>> m = iterator.next();
            String oTableKey = m.getKey();
            HashMap<String, Object> oTableValue = m.getValue();
            HashMap<String, Object> tmpValue;
            for (Iterator<Map<String, Object>> iterator2 = oTable.iterator(); iterator2.hasNext();) {
                Map<String, Object> oMap = iterator2.next();

                // System.out.println(oTableValue.toString() + oMap.toString());
                // 首先检查p_key是否对应
                tmp_flag = true;
                for (int i = 0; i < p_key.length; i++) {
                    tmp_flag = tmp_flag && oTableValue.get(p_key[i]).equals(oMap.get(p_key[i]));
                }
                if (tmp_flag) {
                    // 如果p_key对应,则去查value_key的对应情况
                    for (Iterator<String> iterator3 = value_key_set.iterator(); iterator3.hasNext();) {
                        String tmp_value_key = iterator3.next();

                        if (tmp_value_key.equals(oMap.get(value_key))) {
                            // 找到对应的列,则根据v_key自动填写数据(拆分成多列)
                            for (int i = 0; i < v_key.length; i++) {
                                if (nTable2.containsKey(oTableKey + v_key[i])) {
                                    tmpValue = nTable2.get(oTableKey + v_key[i]);
                                } else {
                                    tmpValue = new HashMap<String, Object>();
                                }
                                tmpValue.putAll(oTableValue);
                                tmpValue.put(v_key_title, v_key_name[i]);
                                tmpValue.put(tmp_value_key, oMap.get(v_key[i]));
                                // 根据isOverride情况自动判断插入值
                                if (isOverride) {
                                    tmpValue.put(tmp_value_key, oMap.get(v_key[i]));
                                } else {
                                    IMap.putEx(tmpValue, tmp_value_key, oMap.get(v_key[i]), splitStr);
                                }
                                nTable2.put(oTableKey + v_key[i], tmpValue);
                            }
                        }
                    }
                }
            }
        }
        // 清空数据,以免内存溢出
        value_key_set.clear();
        value_key_set = null;
        nTable1.clear();
        nTable1 = null;
        // 排序需要
        TreeMap<String, HashMap<String, Object>> tmp = new TreeMap<String, HashMap<String, Object>>();
        tmp.putAll(nTable2);

        // for (Iterator<Map.Entry<String, HashMap<String, Object>>> iterator =
        // tmp.entrySet().iterator(); iterator
        // .hasNext();) {
        // Map.Entry<String, HashMap<String, Object>> tm = iterator.next();
        // System.out.println(tm.getKey() + ":" + tm.getValue().toString());
        // }

        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        l.addAll(tmp.values());
        return l;
    }

    /**
     * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回 (将有2位小数的形式)
     *
     * @param sourceDate
     * @param formatLength
     * @return 重组后的数据
     */
    public static String frontCompWithZore(double sourceDate, int formatLength) {
        String format = String.format("%0" + formatLength + ".3g", 0.0);
        DecimalFormat df = new DecimalFormat(format);
        return df.format(sourceDate);
    }

    /**
     * 防止分隔符中间还有空格的情况
     *
     * @param str
     * @param split
     * @return
     */
    public static String[] spliten(String str, String split) {
        if (IUtils.isEmpty(str)) {
            return new String[0];
        } else {
            return str.split("[ ]*" + split + "[ ]*");
        }
    }

    public static String[] spliten(String str) {
        return spliten(str, ",");
    }

    /**
     * 数组转换成字符串,用split隔开
     *
     * @param array
     *            数组,可以是简单类型的数组String[],Integer[],Double[]等
     * @param split
     *            分隔字符
     * @return
     */
    public static String Array2String(Object[] array, String split) {
        if (!(array == null || array.length == 0)) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]).append(split);
            }
            sb.delete(sb.length() - split.length(), sb.length());
            return sb.toString();
        } else {
            return null;
        }
    }

    public static byte[] File2byte(String filePath) {
        return File2byte(new File(filePath));
    }

    public static byte[] File2byte(File file) {
        if (!file.exists() || !file.isFile()) {
            return new byte[0];
        }
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static String formatNum(Double d, Integer digit) {
        return String.format("%." + digit + "f", d);
    }

    public static boolean isNumeric(String str) {
        String a = "^(-?\\d+)(\\.\\d+)?";
        Pattern pattern = Pattern.compile(a);
        return pattern.matcher(str).matches();
    }

    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'J', 'K', 'L', 'M', 'N', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z' };

    /**
     * 将十进制的数字转换为指定进制的字符串。
     *
     * @param i
     *            十进制的数字。
     * @param system
     *            指定的进制，常见的2/8/16。
     * @return 转换后的字符串。
     */
    public String numericToString(int i, int system) {
        long num = 0;
        if (i < 0) {
            num = ((long) 2 * 0x7fffffff) + i + 2;
        } else {
            num = i;
        }
        char[] buf = new char[32];
        int charPos = 32;
        while ((num / system) > 0) {
            buf[--charPos] = digits[(int) (num % system)];
            num /= system;
        }
        buf[--charPos] = digits[(int) (num % system)];
        return new String(buf, charPos, (32 - charPos));
    }

    /**
     * 将其它进制的数字（字符串形式）转换为十进制的数字。
     *
     * @param s
     *            其它进制的数字（字符串形式）
     * @param system
     *            指定的进制，常见的2/8/16。
     * @return 转换后的数字。
     */
    public int stringToNumeric(String s, int system) {
        char[] buf = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        long num = 0;
        for (int i = 0; i < buf.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == buf[i]) {
                    num += j * Math.pow(system, buf.length - i - 1);
                    break;
                }
            }
        }
        return (int) num;
    }

    public static boolean isArray(Object obj) {
        if ((obj != null) && (obj.getClass().isArray())) {
            return true;
        }
        return false;
    }

    public static boolean isList(Object obj) {
        if (obj instanceof Collection) {
            return true;
        }
        return false;
    }

    /**
     * 把字符串转成整形数组
     *
     * @param str
     * @return
     */
    public static Integer[] split2Integer(String str, String split) {
        if (isEmpty(str)) {
            return new Integer[0];
        }
        String[] strs = spliten(str, split);
        Integer[] ints = new Integer[strs.length];
        for (int i = 0; i < ints.length; i++) {
            try {
                ints[i] = Integer.parseInt(strs[i]);
            } catch (Exception e) {
                ints[i] = -1;
            }
        }
        return ints;
    }

    public static Integer[] split2Integer(String str) {
        return split2Integer(str, ",");
    }

    /**
     * 序列化对象
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 反序列化对象
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
    }

    public static Object[] mergeArray(Object[] al, Object[] bl) {
        if (bl == null || bl.length == 0) {
            return al;
        } else if (al == null || al.length == 0) {
            return bl;
        } else {
            Object[] a = al;
            Object[] b = bl;
            Object[] c = new Object[a.length + b.length];
            System.arraycopy(a, 0, c, 0, a.length);
            System.arraycopy(b, 0, c, a.length, b.length);
            return c;
        }
    }

    public static String[] mergeArray(String[] al, String[] bl) {
        if ((bl == null || bl.length == 0) && (al == null || al.length == 0)) {
            return null;
        } else if (bl == null || bl.length == 0) {
            return al;
        } else if (al == null || al.length == 0) {
            return bl;
        } else {
            String[] a = al;
            String[] b = bl;
            String[] c = new String[a.length + b.length];
            System.arraycopy(a, 0, c, 0, a.length);
            System.arraycopy(b, 0, c, a.length, b.length);
            return c;
        }
    }

    /**
     * AutoBindModel assist <br />
     * Use :
     * org.springframework.core.io.support.PathMatchingResourcePatternResolver
     * <br />
     *
     * @param patten
     *            :
     *
     *            <pre class="code">
     * classpath*:com/mchange/db/*&#42;/* <br />
     * classpath*:com/tqms/admin/*&#42;/controller/
     *            </pre>
     *
     * @return
     */
    public static Resource[] getResources(String patten) {
        try {
            return new PathMatchingResourcePatternResolver().getResources(patten);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 给Bean直接设置值(反射)
     *
     * @param o
     * @param name
     * @param value
     */
    public static void setProperty(Object o, String name, Object value) {
        try {
            PropertyUtils.setProperty(o, name, value);
        } catch (Exception e) {
            log.warn("The property" + name + " may not be initialize.", e);
        }
    }

    /**
     * 得到值,如果异常则返回空
     *
     * @param o
     * @param name
     * @return
     */
    public static Object getProperty(Object o, String name) {
        try {
            return PropertyUtils.getProperty(o, name);
        } catch (Exception e) {
            log.warn("The property" + name + " may not be initialize.", e);
            return null;
        }
    }

    /**
     * 转换成标准Restful资源的样子 <br />
     * exp . aaa/bbb/ --> /aaa/bbb <br />
     * exp. aaa/bbb -> /aaa/bbb
     *
     * @param val
     * @return
     */
    public static String getRestful(String val) {
        if (isEmpty(val)) {
            return "";
        }
        if (!val.startsWith("/")) {
            val = "/" + val;
        }
        if (val.endsWith("/")) {
            val = val.substring(0, val.length() - 1);
        }
        return val;
    }

    /**
     * 转换成标准Restful资源的样子 <br />
     * exp . aaa/bbb/ --> /aaa/bbb <br />
     * exp. aaa/bbb -> /aaa/bbb
     *
     * @param val
     * @return
     */
    public static String getRelativeRestful(String val) {
        if (isEmpty(val)) {
            return "";
        }
        if (val.startsWith("/")) {
            val = val.substring(1, val.length());
        }
        if (val.endsWith("/")) {
            val = val.substring(0, val.length() - 1);
        }
        return val;
    }

    public static Map<?, Object> removeAllNullValue(Map<?, Object> map) {
        if (map == null || map.size() == 0) {
            return map;
        }
        map.values().removeAll(IConst.NULL_ELEMENT_LIST);
        return map;
    }

    /**
     * 把Exception转成字符串
     *
     * @param e
     * @return
     */
    public static String getErrorInfo(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "Bad GetErrorInfoFromException";
        }
    }

    /**
     * 快速创建LinkedCaseInsensitiveMap,格式为 key1,value1,key2,value2...
     *
     * @param paras
     * @return
     */
    public static LinkedCaseInsensitiveMap<Object> createLinkedCaseInsensitiveMap(Object... paras) {
        return IMap.createAttrMap(paras);
    }

    /**
     * 快速创建LinkedCaseInsensitiveMap
     *
     * @param map
     * @return
     */
    public static LinkedCaseInsensitiveMap<Object> createLinkedCaseInsensitiveMap(Map<String, Object> map) {
        return IMap.createAttrMap(map);
    }

    /**
     * 对象转byte[]
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static byte[] objectToBytes(Object obj) throws Exception {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        byte[] bytes = bo.toByteArray();
        bo.close();
        oo.close();
        return bytes;
    }

    /**
     * byte[]转对象
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object bytesToObject(byte[] bytes) throws Exception {
        ObjectInputStream sIn = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return sIn.readObject();
    }

    public static Log getLog() {
        return log;
    }

    /**
     * 比较2个Set内容是否一致,只测试了Number和String
     *
     * @return
     */
    public static <T> boolean equalSet(Set<T> a, Set<T> b) {
        boolean result = false;
        if (a == null || b == null) {
            // 2个都是null,则返回true;否则返回false
            if (a == null && b == null) {
                result = true;
            } else {
                result = false;
            }
        } else if (a.size() == 0 && b.size() == 0) {
            result = true;
        } else {
            return a.containsAll(b) && b.containsAll(a);
        }
        // Arrays.deepEquals(a1, a2)
        return result;
    }

    // 获得当前IP
    public static String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return null;
    }

    // 获取MAC地址的方法
    public static String getMAC() {
        try {
            // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
            byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
            // 下面代码是把mac地址拼装成String
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                // mac[i] & 0xFF 是为了把byte转化为正整数
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            // 把字符串所有小写字母改为大写成为正规的mac地址并返回
            return sb.toString().toUpperCase();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 使用循环延迟的方法
     *
     * @param sleepMillis
     *            延迟的毫秒数
     */
    public static void sleep(long sleepMillis) {
        long startTime = System.currentTimeMillis();
        while (true) {
            if ((System.currentTimeMillis() - startTime) > sleepMillis) {
                break;
            }
        }
    }

    public static Integer specialLength(String str) {
        String E1 = "[\u4e00-\u9fa5]"; // 中文
        String E2 = "[0-9a-zA-Z .,_-]"; // 英文加数字加逗号加句号
        int length = 0;
        String temp;
        for (int i = 0; i < str.length(); i++) {
            temp = String.valueOf(str.charAt(i));
            if (temp.matches(E1)) {
                length = length + 2;
            } else if (temp.matches(E2)) {
                length = length + 1;
            } else {
                length = length + 2;
            }
        }
        return length;
    }

    /**
     * 判断IP是否在IP段范围内
     *
     * @param ipSection
     *            IP段范围 192.168.6.100-192.168.7.254
     * @param ip
     *            192.168.7.100
     * @return
     */
    public static boolean validIP(String ipSection, String ip) {
        if (ipSection == null) {
            return false;
        }
        if (ip == null) {
            return false;
        }
        ipSection = ipSection.trim();
        ip = ip.trim();
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
        if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP)) {
            return false;
        }
        int idx = ipSection.indexOf('-');
        String[] sips = ipSection.substring(0, idx).split("\\.");
        String[] sipe = ipSection.substring(idx + 1).split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }

    /**
     * 拆分List,把List分为等量的子List 2017年8月12日 下午10:03:27
     *
     * @Author kreo
     * @param targe
     * @param size
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> targe, int size) {
        List<List<T>> listArr = new ArrayList<List<T>>();
        // 获取被拆分的数组个数
        int arrSize = targe.size() % size == 0 ? targe.size() / size : targe.size() / size + 1;
        for (int i = 0; i < arrSize; i++) {
            List<T> sub = new ArrayList<>();
            // 把指定索引数据放入到list中
            for (int j = i * size; j <= size * (i + 1) - 1; j++) {
                if (j <= targe.size() - 1) {
                    sub.add(targe.get(j));
                }
            }
            listArr.add(sub);
        }
        return listArr;
    }


    /**
     * 去除不需要的前缀或者小数位的0
     *
     * @param number
     * @return
     */
    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number)).stripTrailingZeros().toPlainString();
    }
}
