/**
 *
 *
 * +----------------------------------------------------------------------------------------------+
 * |Date               |  Version  |Author             |Description                              |
 * |==========+=======+==============+===================|
 * |2016.3.3        |  1.0.0       | Kreo                 |Initial                                       |
 * +----------------------------------------------------------------------------------------------+
 */
package fly.xysimj.jasminediary.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @ClassName: INum
 *
 */
public class INum {

	/**
	 * 求给定双精度数组中值的和
	 *
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double sum(double[] inputData) {
		if (inputData == null || inputData.length == 0) {
			return -1;
		}
		int len = inputData.length;
		double sum = 0;
		for (int i = 0; i < len; i++) {
			sum = sum + inputData[i];
		}
		return sum;

	}

	/**
	 * 求给定双精度数组中值的数目
	 *
	 * @param inputData
	 *            Data 输入数据数组
	 * @return 运算结果
	 */
	public static int count(double[] inputData) {
		if (inputData == null) {
			return -1;
		}
		return inputData.length;
	}

	/**
	 * 求给定双精度数组中值的平均值
	 *
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double average(double[] inputData) {
		if (inputData == null || inputData.length == 0) {
			return -1;
		}
		int len = inputData.length;
		double result;
		result = sum(inputData) / len;
		return result;
	}

	/**
	 * 求给定双精度数组中值的平方和
	 *
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double squareSum(double[] inputData) {
		if (inputData == null || inputData.length == 0) {
			return -1;
		}
		int len = inputData.length;
		double sqrsum = 0.0;
		for (int i = 0; i < len; i++) {
			sqrsum = sqrsum + inputData[i] * inputData[i];
		}
		return sqrsum;
	}

	/**
	 * 求给定双精度数组中值的方差
	 *
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double variance(double[] inputData) {
		int count = count(inputData);
		double sqrsum = squareSum(inputData);
		double average = average(inputData);
		double result;
		result = (sqrsum - count * average * average) / count;
		return result;
	}

	/**
	 * 求给定双精度数组中值的标准差
	 *
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double standardDiviation(double[] inputData) {
		double result;
		// 绝对值化很重要
		result = Math.sqrt(Math.abs(variance(inputData)));
		return result;
	}

	/**
	 * 把其他Number类型的数组转成Double类型
	 *
	 * @param numbers
	 * @return
	 */
	public static double[] toDoubleArray(Number[] numbers) {
		if (numbers == null || numbers.length == 0) {
			return null;
		} else {
			double[] result = new double[numbers.length];
			for (int i = 0; i < numbers.length; i++) {
				result[i] = numbers[i].doubleValue();
			}
			return result;
		}
	}

	/**
	 * int数组求和
	 *
	 * @param a
	 *            int数组
	 * @param n
	 *            对前n个数进行求和,n=0,则输出第一个数,n=-1,则输出所有数的和
	 * @return
	 */
	public static int sum(int[] a, int n) {
		n = n == -1 ? a.length - 1 : Math.min(a.length - 1, n);
		return n > 0 ? a[n] + sum(a, n - 1) : a[0];
	}

	/**
	 * int数组求和
	 *
	 * @param a
	 *            int数组
	 * @param n
	 *            对前n个数进行求和,n=0,则输出第一个数,n=-1,则输出所有数的和
	 * @return
	 */
	public static double sum(double[] a, int n) {
		n = n == -1 ? a.length - 1 : Math.min(a.length - 1, n);
		return n > 0 ? a[n] + sum(a, n - 1) : a[0];
	}

	/**
	 * double四舍五入
	 *
	 * @param d
	 * @param digit
	 *            保留小数位数
	 * @return
	 */
	public static Double round(double d, int digit) {
		return (BigDecimal.valueOf(d)).setScale(digit, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 去除数字或字符串前后0的方法
	 *
	 * @param dec
	 * @return
	 */
	public static String removeZero(Object dec) {
		try {
			if (dec != null) {
				return (new DecimalFormat("###############.####")).format(new BigDecimal(dec.toString()));
			} else {
				return "";
			}
		} catch (Exception e) {
			return dec.toString();
		}
	}

	/**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
    	String a = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
           Pattern pattern = Pattern.compile(a);
           Matcher isNum = pattern.matcher(str);
           if( !isNum.matches() ){
               return false;
           }
           return true;
    }
}
