/**
 *
 *
 * +----------------------------------------------------------------------------------------------+
 * |Date               |  Version  |Author             |Description                              |
 * |==========+=======+==============+===================|
 * |2016.3.3        |  1.0.0       | kreo                 |Initial                                       |
 * +----------------------------------------------------------------------------------------------+
 */
package fly.xysimj.jasminediary.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 *
 * @ClassName IMap
 * @Description
 *
 */
public class IMap {
	/**
	 * 快速创建LinkedCaseInsensitiveMap,格式为 key1,value1,key2,value2...
	 *
	 * @param paras
	 * @return
	 */
	public static LinkedCaseInsensitiveMap<Object> createAttrMap(Object... paras) {
		if (paras == null || paras.length < 2) {
            return null;
        }
		LinkedCaseInsensitiveMap<Object> paraMap = new LinkedCaseInsensitiveMap<>();
		for (int i = 0; i < paras.length / 2; i++) {
			if (paras[2 * i] == null || paras[2 * i + 1] == null) {
                continue;
            }
			paraMap.put((String) paras[2 * i], paras[2 * i + 1]);
		}
		return paraMap;
	}

	/**
	 *
	 * @param map
	 *            原map
	 * @param paras
	 *            额外添加参数
	 * @return
	 */
	public static LinkedCaseInsensitiveMap<Object> createAttrMap(LinkedCaseInsensitiveMap<Object> map, Object... paras) {
		if (map == null && paras == null) {
			return null;
		} else if (map == null) {
            return createAttrMap(paras);
        } else if (paras == null) {
            return map;
        } else {
			map.putAll(createAttrMap(paras));
			return map;
		}
	}

	/**
	 * 快速创建LinkedCaseInsensitiveMap
	 *
	 * @param map
	 * @return
	 */
	public static LinkedCaseInsensitiveMap<Object> createAttrMap(Map<String, Object> map) {
		if (map == null) {
            return null;
        }
		LinkedCaseInsensitiveMap<Object> paraMap = new LinkedCaseInsensitiveMap<>();
		paraMap.putAll(map);
		return paraMap;
	}

	/**
	 * 支持LinkedCaseInsensitiveMap,BaseModel,Map
	 *
	 * @param para
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LinkedCaseInsensitiveMap<Object> getAttrMap(Object para) {
		if (para instanceof LinkedCaseInsensitiveMap) {
            return (LinkedCaseInsensitiveMap<Object>) para;
        } else if (para instanceof Map) {
            return IMap.createAttrMap((Map) para);
        }else {
            return null;
        }
	}

	/**
	 * 特殊put,如果key存在,则使用value1.toString,value2.toString形式,使用split,默认是","
	 *
	 * @param m
	 * @param key
	 * @param value
	 */
	public static void putEx(Map<String, Object> m, String key, Object value, String split) {
		Object oldVal = m.put(key, value);
		if (oldVal != null && !oldVal.equals(value)) {
			split = split != null ? split : ",";
			m.put(key, IType.getStr(oldVal) + split + IType.getStr(value));
		}
	}

	/**
	 * 解析字符串,01类型
	 * <br />
	 * 样例:300#172.18.0.51:8000,800#172.18.0.55:8000|172.18.0.57:8000
	 *
	 * @return
	 */
	public static LinkedHashMap<String, String[]> parse01(String str) {
		if (IStr.isBlank(str)) return null;
		LinkedHashMap<String, String[]> m = new LinkedHashMap<>();
		String[] eles = str.split(",");
		for (String ele : eles) {
			String[] vals = ele.split("\\#");
			// 只有长度是2的才标准表格,否则格式不符则不进入返回列表
			if (vals.length == 2) {
				m.put(vals[0], vals[1].split("\\|"));
			} else if (vals.length == 1) {
				m.put(vals[0], null);
			}
		}
		return m;
	}

	/**
	 * 快速创建Map,格式为 key1,value1,key2,value2...
	 *
	 * @param paras
	 * @return
	 */
	public static Map<String, String> createParaMap(String... paras) {
		if (paras == null || paras.length < 2) {
			return null;
		}
		Map<String, String> paraMap = new HashMap<>();
		for (int i = 0; i < paras.length / 2; i++) {
			if (paras[2 * i] == null || paras[2 * i + 1] == null) {
				continue;
			}
			paraMap.put((String) paras[2 * i], paras[2 * i + 1]);
		}
		return paraMap;
	}

	/**
	 * 得到LinkedHashMap的第一个
	 *
	 * @param map
	 * @return
	 */
	public static <K, V> Map.Entry<K, V> getHead(LinkedHashMap<K, V> map) {
		if (map != null && map.size() > 0) {
			return map.entrySet().iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * 得到LinkedHashMap的最后一个
	 *
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map.Entry<K, V> getTailByReflection(LinkedHashMap<K, V> map) throws NoSuchFieldException, IllegalAccessException {
		if (map == null || map.size() == 0) {
			return null;
		} else if (map.size() == 1) {
			return getHead(map);
		} else {
			Field tail = map.getClass().getDeclaredField("tail");
			tail.setAccessible(true);
			return (Map.Entry<K, V>) tail.get(map);
		}
	}

	/**
	 * Map 累加功能,key相同时,value是累加而不是覆盖
	 * 2017年8月31日 下午2:56:16
	 *
	 * @Author kreo
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, Double> merge(HashMap<String, Double> map, String key, Double value) {
		map.merge(key, value, (v1, v2) -> {
			return INum.round(v1 + v2, 4);
		});
		return map;
	}

	/**
	 * 打印成HTML格式
	 *
	 * <pre>
	 * key1 : value1 &lt;br /&gt; <br />
	 * key2 : value2 &lt;br /&gt;
	 *
	 * <pre>
	 *
	 * @param map
	 * @return
	 */
	public static String printHtml(@SuppressWarnings("rawtypes") Map map) {
		StringBuilder sb = new StringBuilder();
		for (Object key : map.keySet()) {
			sb.append(key).append(" : ").append(map.get(key)).append("\n<br />");
		}
		return sb.toString();
	}

	/**
	 * 合并Map,重复时,把值相加
	 * 2018年9月23日 上午2:20:08
	 *
	 * @Author kreo
	 * @param o
	 * @param n
	 * @return
	 */
	public static Map<String, Double> mergeDoubleAdd(Map<String, Double> o, Map<String, Double> n) {
		if (n == null || n.isEmpty()) { return o; }
		if (o == null || o.isEmpty()) { return n; }
		n.forEach((k, v) -> {
			o.merge(k, v, (v1, v2) -> v1 + v2);
		});
		return o;
	}
}
