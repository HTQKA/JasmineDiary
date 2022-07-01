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

/**
 * 资源获取工具类,作为缓存国际化的入口
 *
 * @ClassName IRes
 * @Description
 *
 */
public class IRes {
	/**
	 * 得到Code Message
	 *
	 * @param code
	 * @return
	 */
	public static String getCM(Integer code, String... paras) {
		return IStr.getDefault(IStr.format(IConst.CM.get(code), paras), "");
	}
}
