package fly.xysimj.jasminediary.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @program: JasmineDiary
 * @ClassName IStr
 * @description: 字符串工具类
 * @author: 徐杨顺
 * @create: 2022-06-30 13:21
 * @Version 1.0
 **/
public class IStr extends StringUtils {
    /**
     * 把数据库/国际化key转换成正常的英文字段
     * 比如 : maintenance_period -> Maintenance Period
     */
    public static String keyToLang(String key_name) {
        key_name = key_name.substring(key_name.lastIndexOf(".") + 1);
        char underLine = '_', empty = ' ';
        char[] charArray = key_name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i == 0) {
                charArray[i] = Character.toUpperCase(charArray[i]);
            }
            if (charArray[i] == underLine && i < charArray.length - 1) {
                charArray[i] = empty;
                charArray[i + 1] = Character.toUpperCase(charArray[i + 1]);
            }
        }
        return new String(charArray);
    }

    /**
     * 定制功能 把表明转换成英文
     * ct_eqp_status_category -> EQP Status Category
     *
     * @param tableName
     * @return
     */
    public static String tableToLang(String tableName) {
        String[] strs = tableName.split("_");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                continue; // 去除CT RT HT CV RV HV前缀
            }
            if (strs[i].length() <= 3) {
                sb.append(strs[i].toUpperCase()); // 如果小于3位,则认为是缩写,全部大写
            } else {
                sb.append(IStr.UpperFirst(strs[i]));
            }
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 把null返回defaultValue
     *
     * @param
     * @param defaultValue
     * @return
     */
    public static String getEmpty(String str, String defaultValue) {
        String value = getDefault(str, defaultValue);
        return value != null ? value : "";
    }

    /**
     * 把null返回defaultValue
     *
     * @param
     * @param defaultValue
     * @return
     */
    public static String getDefault(String str, String defaultValue) {
        return isNotBlank(str) ? str : defaultValue;
    }

    /**
     * 转字符成urlEncode
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        try {
            return java.net.URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 把中文转成Unicode码
     *
     * @param str
     * @return
     */
    public static String chinaToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {
                // 汉字范围 \\u4e00-\\u9fa5
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    /**
     * 判断是否为中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 字符串转换unicode
     */
    public static String unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String decodeUnicode(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     * JAVA判断字符串数组中是否包含某字符串元素
     *
     * @param
     * @param
     * @return 包含则返回true，否则返回false
     */
    public static boolean isIn(String str, String[] strArray) {
        return ArrayUtils.contains(strArray, str);
    }

    /**
     * JAVA判断字符串数组中是否包含某字符串元素
     *
     * @param substring 某字符串
     * @param source    源字符串
     * @return 包含则返回true，否则返回false
     */
    public static boolean isIn(String substring, String source, String split) {
        if (source == null) {
            return false;
        }
        split = IUtils.isEmpty(split) ? "," : split;
        return isIn(substring, source.split(split));
    }

    /**
     * 截取字符串,截取前n位
     *
     * @param s
     * @return
     * @date 2013-12-29 下午7:26:51
     */
    public static String subString(String s, Integer n) {
        if (s.length() <= n) {
            return s;
        } else {
            return s.substring(0, n) + "...";
        }
    }

    /**
     * 首字母大写
     *
     * @param str
     * @date 2013-12-29 下午6:36:33
     */
    public static String UpperFirst(String str) {
        char[] array = str.toCharArray();
        if (Character.isLowerCase(array[0])) {
            array[0] -= 32;
        }
        return String.valueOf(array);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @date 2013-12-29 下午6:36:33
     */
    public static String LowerFirst(String str) {
        char[] array = str.toCharArray();
        if (Character.isUpperCase(array[0])) {
            array[0] += 32;
        }
        return String.valueOf(array);
    }

    /**
     * 去除下划线,下划线后首字母大写
     *
     * @param s
     * @return
     * @date 2013-12-29 下午7:26:51
     */
    public static String UpperFirst2(String s) {
        StringBuffer sb = new StringBuffer(s);
        int n;
        while ((n = sb.indexOf("_")) >= 0) {
            if (Character.isLowerCase(sb.charAt(n + 1))) {
                sb.replace(n, n + 2, (char) (sb.charAt(n + 1) - 32) + "");
            } else {
                sb.deleteCharAt(n);
            }
        }
        return sb.toString();
    }

    /**
     * 去除下划线,下划线后首字母小写
     *
     * @param s
     * @return
     * @date 2013-12-29 下午7:26:51
     */
    public static String LowerFirst2(String s) {
        StringBuffer sb = new StringBuffer(s);
        int n;
        while ((n = sb.indexOf("_")) >= 0) {
            if (Character.isUpperCase(sb.charAt(n + 1))) {
                sb.replace(n, n + 2, (char) (sb.charAt(n + 1) + 32) + "");
            } else {
                sb.deleteCharAt(n);
            }
        }
        return sb.toString();
    }

    public static String replaceEnter(String oldString) {
        // 正则表达式的匹配一定要是这样，单个替换\r|\n的时候会错误
        return oldString.replaceAll("(\r\n|\r|\n|\n\r)", " ");
    }

    /**
     * 去除前导0
     * 2017年8月30日 上午9:14:15
     *
     * @param str
     * @return
     * @Author kreo
     */
    public static String replaceFirstZeros(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("^(0+)", "");
    }

    /**
     * 消息替换,可以把[#0#],[#1#]等替换成相应的字符
     *
     * @param str
     * @param replace
     * @return
     */
    public static String format(String str, String... replace) {
        if (replace == null || replace.length == 0 || isBlank(str)) return str;
        else {
            for (int i = 0; i < replace.length; i++) {
                str = str.replaceAll("\\[#" + i + "#\\]", IStr.getDefault(replace[i], ""));
            }
            return str;
        }
    }

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    public static String getAnyNotBlank(String... cs) {
        if (cs != null) {
            for (final String c : cs) {
                if (isNotBlank(c)) {
                    return c;
                }
            }
        }
        return null;
    }

    public static String getAnyNotEmpty(String... cs) {
        if (cs != null) {
            for (final String c : cs) {
                if ((isNotEmpty(c))) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * 前导补0函数,支持字符和数字(整形,浮点数)
     * 如果totalLength小于字符串长度,则截取字符串后totalLength位字符
     *
     * @param obj
     * @param totalLength
     * @return
     */
    public static String frontCompWithZore(Object obj, Integer totalLength) {
        return frontComp(obj, totalLength, '0');
    }

    public static String frontComp(Object obj, Integer totalLength, char compChar) {
        if (obj instanceof Number || obj instanceof String) { // 只有数字和字符串
            String str = obj.toString();
            char[] dst = new char[totalLength];
            str.getChars(str.length() <= totalLength ? 0 : str.length() - totalLength, str.length(), dst, str.length() <= totalLength ? totalLength - str.length() : 0);
            for (int i = 0; i < dst.length; i++) {
                if (dst[i] == '\0') {
                    dst[i] = compChar;
                }
            }
            return new String(dst);
        } else {
            return null;
        }
    }

    /**
     * 后导补0函数,支持字符和数字(整形,浮点数)
     * 如果totalLength小于字符串长度,则截取字符串后totalLength位字符
     *
     * @param obj
     * @param
     * @return
     */
    public static String behindCompWithZore(Object obj, Integer totalLength) {
        return behindComp(obj, totalLength, '0');
    }

    public static String behindComp(Object obj, Integer totalLength, char compChar) {
        if (obj instanceof Number || obj instanceof String) { // 只有数字和字符串
            String str = obj.toString();
            char[] dst = new char[totalLength];
            str.getChars(str.length() <= totalLength ? 0 : str.length() - totalLength, str.length(), dst, 0);
            for (int i = 0; i < dst.length; i++) {
                if (dst[i] == '\0') {
                    dst[i] = compChar;
                }
            }
            return new String(dst);
        } else {
            return null;
        }
    }

    /**
     * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytes2Hex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

    public static String string2Hex(String src) {
        return string2Hex(src, Charset.forName("UTF-8"));
    }

    public static String string2Hex(String src, Charset charset) {
        return bytes2Hex(src.getBytes(charset));
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hex2Bytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static String hex2String(String hexString) {
        return hex2String(hexString, Charset.forName("UTF-8"));
    }

    public static String hex2String(String hexString, Charset charset) {
        return new String(hex2Bytes(hexString), charset);
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


}

