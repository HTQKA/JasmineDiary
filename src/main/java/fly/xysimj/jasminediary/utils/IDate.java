/**
 * Copyright (c) 2016 Zhicheng Qiming Technology Co. Ltd.. All Rights Reserved.
 * This software is the confidential and proprietary information of Zhicheng Qiming
 * Technology Co. Ltd. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Zhicheng Qiming.
 *
 * ZHICHENGQIMING MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SYNERGY SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 *
 * +----------------------------------------------------------------------------------------------+
 * |Date               |  Version  |Author             |Description                              |
 * |==========+=======+==============+===================|
 * |2016.3.3        |   1.0.0     | Kreo                 |Initial                                        |
 * +----------------------------------------------------------------------------------------------+
 *
 */
package fly.xysimj.jasminediary.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;


public class IDate extends DateUtils {
	public static final String TYPE_YEAR = "YEAR";
	public static final String TYPE_MONTH = "MONTH";
	public static final String TYPE_DAY = "DAY";
	public static final String TYPE_WEEK = "WEEK";

	public static String getNow(String patten) {
		DateTime dt = new DateTime();
		return dt.toString(patten);
	}

	public static String getDate(Date d, String patten) {
		DateTime dt = new DateTime(d.getTime());
		return dt.toString(patten);
	}

	/**
	 * 判断d是否在start_d end_d中间
	 *
	 * @param d
	 * @param start_d
	 * @param end_d
	 * @return
	 */
	public static boolean isBetween(Date d, Date start_d, Date end_d) {
		return d.getTime() > start_d.getTime() && d.getTime() < end_d.getTime();
	}

	/**
	 * 得到patten指定的日期,返回Date
	 *
	 * @param d
	 * @param patten
	 * @return
	 */
	public static Date getPattenDate(Date d, String patten) {
		return DateTime.parse(getDate(d, patten)).toDate();
	}

	/**
	 * 返回今天yyyy-MM-dd
	 *
	 * @return
	 */
	public static Date getToday() {
		return getPattenDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 返回昨天yyyy-MM-dd
	 *
	 * @return
	 */
	public static Date getYestoday() {
		return getPattenDate(addDays(new Date(), -1), "yyyy-MM-dd");
	}

	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Date getDate() {
		return new Date();
	}

	/**
	 * 得到当前月第一天
	 *
	 * @return
	 */
	public static String getPresentMonthFirstDay() {
		return getDate(setDays(new Date(), 1), "yyyy-MM-dd");
	}

	/**
	 * 取上个月的最后一天
	 *
	 * @return
	 */
	public static String getPrevMonthLastDay(Object year, Object month) {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();
		if (year != null) {
			cal.set(Calendar.YEAR, (int) year);
		}
		if (month != null) {
			cal.set(Calendar.MONTH, (int) month - 1);
		}
		// 调到上个月
		cal.add(Calendar.MONTH, -1);
		// 得到一个月最最后一天日期(31/30/29/28)
		int MaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 按你的要求设置时间
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay);
		// 按格式输出
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	/**
	 * 查询日期间有几天一周中的某一天
	 *
	 * @param startDate
	 *            准备查询的起始日期
	 * @param endDate
	 *            准备查询的结束日期
	 * @param dayOfWeek
	 *            准备查的一周中的某一天(准备查周几？) 1-7 周一 ---- 周日
	 * @return 包含所查周几的天数 不支持跨年查询、不支持结束日期早于起始日期、周几输入错误等
	 */
	public static int getMondayNumber(Date startDate, Date endDate, int dayOfWeek) {
		int differenceDay = 0;
		// 实例化起始和结束Calendar对象
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		// 分别设置Calendar对象的时间
		startCalendar.setTime(startDate);
		endCalendar.setTime(endDate);

		// 定义起始日期和结束日期分别属于第几周
		int startWeek = startCalendar.get(Calendar.WEEK_OF_YEAR);
		int endWeek = endCalendar.get(Calendar.WEEK_OF_YEAR);

		// 拿到起始日期是星期几
		int startDayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);
		if (startDayOfWeek == 1) {
			startDayOfWeek = 7;
			startWeek--;
		} else {
			startDayOfWeek--;
		}

		// 拿到结束日期是星期几
		int endDayOfWeek = endCalendar.get(Calendar.DAY_OF_WEEK);
		if (endDayOfWeek == 1) {
			endDayOfWeek = 7;
			endWeek--;
		} else {
			endDayOfWeek--;
		}

		// 计算相差的周数
		int differenceWeek = endWeek - startWeek;

		// 开始计算
		if (startDayOfWeek <= dayOfWeek) {
			if (endDayOfWeek >= dayOfWeek) {
				differenceDay = differenceWeek + 1;
			}
		} else if (startDayOfWeek > dayOfWeek) {
			if (endDayOfWeek < dayOfWeek) {
				differenceDay = differenceWeek - 1;
			}
		} else {
			differenceDay = differenceWeek;
		}
		return differenceDay;
	}

	/**
	 *
	 * 2017年8月10日 下午12:12:36
	 *
	 * @Author kreo
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getBetweenDayList(Date startDate, Date endDate) {
		List<Date> dataList = new ArrayList<>();
		if (endDate.getTime() >= startDate.getTime()) {
			for (Date i = new Date(startDate.getTime()); i.getTime() <= endDate.getTime(); i = IDate.addDays(i, 1)) {
				dataList.add(i);
			}
		}
		return dataList;
	}

	/**
	 * 取上月字符串 传入201701格式字符串，返回201612
	 *
	 * @param yearmonth
	 * @return
	 */
	public static String getPrevYearMonth(String yearmonth, String patten) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		if ("".equals(patten)) {
			format = new SimpleDateFormat("yyyyMM");
		} else {
			format = new SimpleDateFormat(patten);
		}
		try {
			Date now = format.parse(yearmonth);
			Calendar c = Calendar.getInstance();
			c.setTime(now);
			c.add(Calendar.MONTH, -1);
			String time = format.format(c.getTime());
			return time;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return yearmonth;
	}

	/**
	 * 取下月字符串 传入201701格式字符串，返回201702
	 *
	 * @param yearmonth
	 * @return
	 */
	public static String getNextYearMonth(String yearmonth, String patten) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		if ("".equals(patten)) {
			format = new SimpleDateFormat("yyyyMM");
		} else {
			format = new SimpleDateFormat(patten);
		}
		try {
			Date now = format.parse(yearmonth);
			Calendar c = Calendar.getInstance();
			c.setTime(now);
			c.add(Calendar.MONTH, 1);
			String time = format.format(c.getTime());
			return time;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return yearmonth;
	}

	/**
	 * 得到所有域的值 [年,月,日,时,分,秒,毫秒,周] 2017年11月25日 上午8:47:12
	 *
	 * @Author kreo
	 * @param d
	 * @return
	 */
	public static int[] getFields(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return new int[] { c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND), c.get(Calendar.MILLISECOND), c.get(Calendar.DAY_OF_WEEK) };
	}

	/**
	 * 根据type取上一周期日期
	 *
	 * @param d
	 *            日期
	 * @param type
	 *            YEAR/ MONTH/ DAY/ WEEK
	 * @return
	 */
	public static Date getLasts(Date d, String type, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		if ("YEAR".equals(type)) {
			c.add(Calendar.YEAR, num);
		} else if ("MONTH".equals(type)) {
			c.add(Calendar.MONTH, num);
		} else if ("DAY".equals(type)) {
			c.add(Calendar.DATE, num);
		} else if ("WEEK".equals(type)) {
			c.add(Calendar.WEEK_OF_YEAR, num);
		}
		return c.getTime();
	}

	/**
	 * 獲取兩個日期之間的月份集合
	 *
	 * @param date1
	 * @param date2
	 */
	public static List<String> getMonths(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		if (date1 == null) {
			date1 = date2;
		} else if (date2 == null) {
			date2 = date1;
		}
		// 定义集合存放月份
		List<String> list = new ArrayList<>();
		c1.setTime(date1);
		c2.setTime(date2);

		if (c1.compareTo(c2) >= 0) {
			// 结束日期大于开始日期，返回空
			return list;
		}
		while (c1.compareTo(c2) <= 0) {
			// 判断日期
			Date ss = c1.getTime();
			String str = IDate.getDate(ss, "yyyyMM");
			list.add(str);
			c1.add(Calendar.MONTH, 1);// 开始日期加一个月直到等于结束日期为止
		}

		// 当结束日期在list中不存在时添加结束日期的月份
		if (!list.contains(IDate.getDate(date2, "yyyyMM"))) {
			list.add(IDate.getDate(date2, "yyyyMM"));
		}
		return list;
	}

	/**
	 * 捕获错误的时间转换 2018年3月17日 下午10:49:29
	 *
	 * @Author kreo
	 * @param o
	 * @return
	 */
	public static Date parseDefault(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof String) {
			if (IStr.isBlank((String) o)) {
				return null;
			} else {
				try {
					return IDate.parseDate((String) o, defaultParsePatterns);
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
			}
		} else if (o instanceof Date) {
			return (Date) o;
		} else {
			return null;
		}
	}

	/**
	 * 根据日期获取当前日期属于第几周
	 *
	 * @param o
	 * @return
	 */
	public static int getWeekByDate(Object o) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(IType.getDate(o));
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		return week;
	}

	/**
	 * 获取当前日期之前的第N天日期
	 */
	public static Date getNumPreday(Integer num) {
		return getPattenDate(addDays(new Date(), -num), "yyyy-MM-dd");
	}

	/**
	 * 获取前一个月的第一天
	 */
	public static String getPreviousMonthFirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// 获取前月的第一天
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return format.format(calendar.getTime());
	}

	/**
	 * 取上个月的第一天
	 *
	 * @return
	 */
	public static String getPresentMonthFirstDay(Object year, Object month) {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();
		if (year != null) {
			cal.set(Calendar.YEAR, (int) year);
		}
		if (month != null) {
			cal.set(Calendar.MONTH, (int) month - 1);
		}
		// 得到一个月第一天
		int MinDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 按你的要求设置时间
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MinDay);
		// 按格式输出
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	public static long getNowMillis() {
		return SystemClock.now();
	}


	final public static String[] defaultParsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM", "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm", "yyyy", "HH:mm:ss" };

	public static class SystemClock {
		private final long period;
		private final AtomicLong now;

		private SystemClock(long period) {
			this.period = period;
			this.now = new AtomicLong(System.currentTimeMillis());
			scheduleClockUpdating();
		}

		private static SystemClock instance() {
			return InstanceHolder.INSTANCE;
		}

		public static long now() {
			return instance().currentTimeMillis();
		}

		public static String nowDate() {
			return new Timestamp(instance().currentTimeMillis()).toString();
		}

		private void scheduleClockUpdating() {
			ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
				@Override
				public Thread newThread(Runnable r) {
					Thread thread = new Thread(r, "System Clock");
					thread.setDaemon(true);
					return thread;
				}
			});
			scheduler.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					now.set(System.currentTimeMillis());
				}
			}, period, period, TimeUnit.MILLISECONDS);
		}

		private long currentTimeMillis() {
			return now.get();
		}

		private static class InstanceHolder {
			public static final SystemClock INSTANCE = new SystemClock(1);
		}

		// 将20200408 字符类型时间转化为Date类型2020-04-08
	}

	public static Date getPattenToDate(String date) {
		StringBuilder sb = new StringBuilder(date);
		sb.insert(4, "-");
		sb.insert(7, "-");
		return parseDefault(sb.toString());
	}

	public static Integer getIntegerDate(Integer date, int num) {
		StringBuilder sb = new StringBuilder(date.toString());
		sb.insert(4, "-");
		sb.insert(7, "-");
		return IType.getInt(new SimpleDateFormat("yyyyMMdd").format(IDate.addDays(IType.getDate(sb), -num)));
	}

	// 获取月份的最后一天
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
	}

	// 获取月份的第一天
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
	}

}
