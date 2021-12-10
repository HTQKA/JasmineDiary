package fly.xysimj.jasminediary.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @program: JasmineDiary
 * @ClassName IJson
 * @description:
 * @author: 徐杨顺
 * @create: 2021-12-10 13:40
 * @Version 1.0
 **/
public class IJson {
    private static Logger log = Logger.getLogger(IJSON.class);

    /**
     * 将json字符串转换为List&lt;Map&lt;String,beanClass&gt;&gt;形式
     *
     * @param json
     * @param beanClass
     * @return
     */
    public static List<Map<String, ?>> parseListMap(String json, Class<?> cls) {
        return IJSON.parseObject(json, new TypeReference<List<Map<String, ?>>>() {
        });
    }

    /**
     * 合并2个json
     *
     * @param json1
     * @param json2
     * @return
     */
    public static JSONObject mergeJson(String json1, String json2) {
        JSONObject jsonObject = parseObject(json1);
        jsonObject.putAll(parseObject(json2));
        return jsonObject;
    }

    /**
     * 直接从json字符串中,取得key的value
     *
     * @param json
     * @param key
     * @return
     */
    public static Object getValue(String json, String key) {
        return parseObject(json).get(key);
    }

    /**
     * 如果参数是model,则调用本身方法进行解析
     *
     * @param baseModel
     * @return
     */
    public static <T extends BaseModel<?>> String toJSONString(T baseModel) {
        return baseModel != null ? baseModel.toJson() : null;
    }

    /**
     * 如果参数是model,则调用本身方法进行解析
     *
     * @param baseModel
     * @return
     */
    public static <T extends BaseModel<?>> String toJSONString(List<T> baseModels) {
        if (baseModels == null) return null;
        List<Map<String, Object>> listAttrs = new ArrayList<>();
        for (T t : baseModels)
            listAttrs.add(t.getAttrs());
        return toJSONString(listAttrs);
    }

    /**
     * 如果参数是model,则调用本身方法进行解析
     *
     * @param baseModel
     * @return
     */
    public static <T extends BaseModel<?>> String toJSONString(Map<Object, T> baseModelMap) {
        if (baseModelMap == null) return null;
        Map<Object, Map<String, Object>> mapAttrs = new HashMap<>();
        for (Map.Entry<Object, T> entry : baseModelMap.entrySet()) {
            mapAttrs.put(entry.getKey(), entry.getValue().getAttrs());
        }
        return toJSONString(mapAttrs);
    }

    /**
     * 把json转换成Model的方法
     *
     * @param json
     * @param baseModel
     * @return
     */
    public static <T extends BaseModel<?>> T parseModel(String json, Class<T> modelClass) {
        try {
            T model = null;
            model = modelClass.newInstance();
            return model.fromJson(json);
        } catch (Exception e) {
            log.error("解析错误", e);
            throw new RuntimeException("解析错误", e);
        }
    }

    /**
     * 把json转换成Model的方法
     *
     * @param json
     * @param baseModel
     * @return
     */
    public static <T extends BaseModel<?>> List<T> parseModels(String json, Class<T> modelClass) {
        try {
            List<T> result = new ArrayList<>();
            List<String> modelJsons = parseArray(json, String.class);
            for (String modelJson : modelJsons) {
                result.add(parseModel(modelJson, modelClass));
            }
            return result;
        } catch (Exception e) {
            log.error("解析错误", e);
            throw new RuntimeException("解析错误", e);
        }
    }

    /**
     * 比较两个json串是否相同
     *
     * @param j1
     *            第一个json串(json串中不能有换行)
     * @param j2
     *            第二个json串(json串中不能有换行)
     * @return 布尔型比较结果
     */
    public static boolean equal(String j1, String j2) {
        // 将json中表示list的[]替换成{}。思想：只保留层次结构，不区分类型
        // 这样直接替换，可能会导致某些value中的符号也被替换，但是不影响结果，因为j1、j2的变化是相对的
        j1 = j1.replaceAll("\\[", "{");
        j1 = j1.replaceAll("]", "}");
        j2 = j2.replaceAll("\\[", "{");
        j2 = j2.replaceAll("]", "}");
        // 将json中，字符串型的value中的{},字符替换掉，防止干扰(并没有去除key中的，因为不可能存在那样的变量名)
        // 未转义regex：(?<=:")(([^"]*\{[^"]*)|([^"]*\}[^"]*)|([^"]*,[^"]*))(?=")
        Pattern pattern = Pattern.compile("(?<=:\")(([^\"]*\\{[^\"]*)|([^\"]*\\}[^\"]*)|([^\"]*,[^\"]*))(?=\")");
        j1 = cleanStr4Special(j1, pattern.matcher(j1));
        j2 = cleanStr4Special(j2, pattern.matcher(j2));
        // 转义字符串value中的空格
        // 未转义regex:"[^",]*?\s+?[^",]*?"
        pattern = Pattern.compile("\"[^\",]*?\\s+?[^\",]*?\"");
        j1 = cleanStr4Space(j1, pattern.matcher(j1));
        j2 = cleanStr4Space(j2, pattern.matcher(j2));
        // 现在可以安全的全局性去掉空格
        j1 = j1.replaceAll(" ", "");
        j2 = j2.replaceAll(" ", "");
        // 调用递归方法
        return compareAtom(j1, j2);
    }

    /**
     * 比较字符串核心递归方法
     *
     * @param j1
     * @param j2
     * @return
     */
    private static boolean compareAtom(String j1, String j2) {
        if (!j1.equals("?:\"?\"")) {
            // 取出最深层原子
            String a1 = j1.split("\\{", -1)[j1.split("\\{", -1).length - 1].split("}", -1)[0];
            String a2 = j2.split("\\{", -1)[j2.split("\\{", -1).length - 1].split("}", -1)[0];
            String j2_ = j2;
            String a2_ = a2;
            // 转换成原子项
            String i1[] = a1.split(",");
            // 在同级原子中寻找相同的原子
            while (!a2.startsWith(",") && !a2.endsWith(",") && a2.indexOf(":,") < 0 && a2.indexOf(",,") < 0) {
                // 遍历消除
                for (String s : i1) {
                    a2_ = a2_.replace(s, "");
                }
                // 此时a2_剩下的全是逗号，如果长度正好等于i1的长度-1，说明相等
                if (a2_.length() == i1.length - 1) {
                    // 相等则从j1、j2中消除，消除不能简单的替换，因为其他位置可能有相同的结构，必须从当前位置上消除
                    int index = 0;
                    index = j1.lastIndexOf("{" + a1 + "}");
                    j1 = j1.substring(0, index) + j1.substring(index).replace("{" + a1 + "}", "?:\"?\"");
                    index = j2.lastIndexOf("{" + a2 + "}");
                    j2 = j2.substring(0, index) + j2.substring(index).replace("{" + a2 + "}", "?:\"?\"");
                    // 递归
                    return compareAtom(j1, j2);
                } else {
                    // 寻找下一个同级原子
                    j2_ = j2_.replace("{" + a2 + "}", "");
                    a2 = j2_.split("\\{", -1)[j2_.split("\\{", -1).length - 1].split("}", -1)[0];
                    a2_ = a2;
                }
            }
            return false;
        } else {
            // 比较是否相同
            return j1.equals(j2);
        }
    }

    /**
     * json字符串特殊字符清理辅助方法
     *
     * @param j
     *            需要清理的json字符串
     * @param matcher
     *            正则表达式匹配对象
     * @return 净化的json串
     */
    private static String cleanStr4Special(String j, Matcher matcher) {
        String group = "";
        String groupNew = "";
        while (matcher.find()) {
            group = matcher.group();
            groupNew = group.replaceAll("\\{", "A");
            groupNew = groupNew.replaceAll("}", "B");
            groupNew = groupNew.replaceAll(",", "C");
            j = j.replace(group, groupNew);
        }
        return j;
    }

    /**
     * json串字符串类型的value中的空格清理辅助方法
     *
     * @param j
     *            需要清理的json字符串
     * @param matcher
     *            正则表达式匹配对象
     * @return 净化的json串
     */
    private static String cleanStr4Space(String j, Matcher matcher) {
        String group = "";
        String groupNew = "";
        while (matcher.find()) {
            group = matcher.group();
            groupNew = group.replaceAll(" ", "S");
            j = j.replace(group, groupNew);
        }
        return j;
    }
}
