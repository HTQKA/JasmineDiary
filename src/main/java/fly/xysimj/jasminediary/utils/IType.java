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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public final class IType {
	public static String getStr(Object obj) {
		return getStr(obj, null);
	}

	public static String getStr(Object obj, String defaultValue) {
		if (obj == null) {
			return defaultValue;
		} else if (obj instanceof String) {
			return (String) obj;
		} else {
			return obj.toString();
		}
	}

	public static Integer getInt(Object obj) {
		return getInt(obj, null);
	}

	public static Integer getInt(Object obj, Integer defaultValue) {
		if (obj == null || "".equals(obj)) {
			return defaultValue;
		} else if (obj instanceof Number) {
			return ((Number) obj).intValue();
		}
		return Integer.parseInt(getStr(obj));
	}

	public static Double getDouble(Object obj) {
		return getDouble(obj, null);
	}

	public static Double getDouble(Object obj, Double defaultValue) {
		if (obj == null || "".equals(obj)) {
			return defaultValue;
		} else if (obj instanceof Number) {
			return ((Number) obj).doubleValue();
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).doubleValue();
		} else {
			return Double.parseDouble(getStr(obj));
		}
	}

	public static BigDecimal getBigDecimal(Object obj) {
		return getBigDecimal(obj, null);
	}

	public static BigDecimal getBigDecimal(Object obj, BigDecimal defaultValue) {
		if (obj == null || "".equals(obj)) {
			return defaultValue;
		} else if (obj instanceof BigDecimal) {
			return (BigDecimal) obj;
		} else {
			return new BigDecimal(obj.toString());
		}
	}

	public static Long getLong(Object obj) {
		return getLong(obj, null);
	}

	public static Long getLong(Object obj, Long defaultValue) {
		if (obj == null || "".equals(obj)) {
			return defaultValue;
		} else if (obj instanceof Number) {
			return ((Number) obj).longValue();
		} else {
			return Long.parseLong(getStr(obj));
		}
	}

	public static Boolean getBoolean(String value) {
		return getBoolean(value, null);
	}

	public static Boolean getBoolean(String value, Boolean defaultValue) {
		if (value == null || "".equals(value.trim())) {
			return defaultValue;
		}
		value = value.trim().toLowerCase();
		if ("1".equals(value) || "true".equals(value)) {
			return Boolean.TRUE;
		} else if ("0".equals(value) || "false".equals(value)) {
			return Boolean.FALSE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static Date getDate(Object obj) {
		if (obj == null) {
			return null;
		} else if (obj instanceof Date) {
			return (Date) obj;
		} else {
			try {
				// 如果是字符串而且是空,则返回null
				if (obj instanceof String && IStr.isBlank(obj.toString())) { return null; }
				return IDate.parseDate(IType.getStr(obj), IDate.defaultParsePatterns);
			} catch (Exception e) {
				return null;
			}
		}
	}

	public static Timestamp getTimestamp(Date d, Timestamp defaultValue) {
		return d == null ? defaultValue : new Timestamp(d.getTime());
	}

	public static Timestamp getTimestamp(Date d) {
		return getTimestamp(d, null);
	}

	public static Timestamp getTimestamp(Object d) {
		Date xDate = getDate(d);
		return xDate == null ? null : new Timestamp(xDate.getTime());
	}
}
