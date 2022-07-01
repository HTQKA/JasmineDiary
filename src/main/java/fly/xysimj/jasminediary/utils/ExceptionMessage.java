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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



/**
 * 自定义一些友好的Message,当出现某些字符时,自动替换成友好的信息
 *
 * @ClassName ExceptionMessage
 * @Description
 *
 */
public class ExceptionMessage {
	private static ConcurrentHashMap<String, String> constIMap = new ConcurrentHashMap<>();
	private static final int minLength = 14;

	static {
		// IException 自定义错误
		constIMap.put("TECO", "SAP生产订单已经被关闭，请咨询排产人员");
	}

	public static String getMessage(String message) {
		if (IStr.isNotBlank(message)) {
			if (message == null || message.length() < minLength) {
				return message;
			}
			for (Map.Entry<String, String> constEntry : constIMap.entrySet()) {
				if (message.indexOf(constEntry.getKey()) > 0) {
					return constEntry.getValue();
				}
			}
		} else {
		}
		return message;
	}
}
