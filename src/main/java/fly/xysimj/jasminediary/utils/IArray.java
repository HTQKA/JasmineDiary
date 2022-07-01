/**
 *
 *
 * +----------------------------------------------------------------------------------------------+
 * |Date               |  Version  |Author             |Description                              |
 * |==========+=======+==============+===================|
 * |2017年9月18日     |  1.0.0       | kreo                 |Initial                                       |
 * +----------------------------------------------------------------------------------------------+
 */
package fly.xysimj.jasminediary.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @ClassName IArray
 * @Description
 *
 */
public class IArray extends ArrayUtils {
	/**
	 * 将一个List按照固定的大小拆成很多个小的List
	 *
	 * @param list
	 *            需要拆分的List
	 * @param groupNum
	 *            每个List的最大长度
	 * @return 拆分后的List的集合
	 */
	public static <T> List<List<T>> splitList(List<T> list, int groupNum) {
		List<List<T>> resultList = new ArrayList<List<T>>();
		// 获取需要拆分的List个数
		int loopCount = (list.size() % groupNum == 0) ? (list.size() / groupNum) : ((list.size() / groupNum) + 1);
		// 开始拆分
		for (int i = 0; i < loopCount; i++) {
			// 子List的起始值
			int startNum = i * groupNum;
			// 子List的终止值
			int endNum = (i + 1) * groupNum;
			// 不能整除的时候最后一个List的终止值为原始List的最后一个
			if (i == loopCount - 1) {
				endNum = list.size();
			}
			// 拆分List
			List<T> listSub = list.subList(startNum, endNum);
			// 保存差分后的List
			resultList.add(listSub);
		}
		return resultList;
	}

	/**
	 *
	 * 2017年10月17日 下午12:30:06
	 *
	 * @Author kreo
	 * @param strs
	 * @return
	 */
	// TODO : 待测试
	public static String[] distinct(String[] strs) {
		if (strs == null || strs.length == 0) return null;
		else return toStream(strs).distinct().toArray(n -> new String[n]);
	}

	/**
	 * 数组转换成Stream
	 * 2017年10月17日 下午12:03:33
	 *
	 * @Author kreo
	 * @param array
	 * @return
	 */
	public static <T> Stream<T> toStream(T[] array) {
		return Arrays.stream(array);
	}

}
