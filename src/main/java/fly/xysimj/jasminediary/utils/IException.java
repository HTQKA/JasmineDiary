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

import java.sql.SQLException;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class IException extends RuntimeException {
	private static final long serialVersionUID = -8287773948227056062L;

	public IException() {
		super();
	}

	public IException(String message) {
		super(ExceptionMessage.getMessage(message));
	}

	public IException(String message, Throwable cause) {
		super(ExceptionMessage.getMessage(message), cause);
	}

	public IException(Throwable cause) {
		super(ExceptionMessage.getMessage(cause.getMessage()), cause);
	}

	public IException(Integer errorCode) {
		super(IRes.getCM(errorCode));
	}

	public IException(Integer errorCode, Throwable cause) {
		super(IRes.getCM(errorCode), cause);
	}

	public static String getStackTrace(Exception e) {
		if (e == null) return "";
		String stackTrace = ExceptionUtils.getStackTrace(e);
		if (e instanceof SQLException) {
			SQLException nextException = ((SQLException) e).getNextException();
			stackTrace += getStackTrace(nextException);
		}
		return stackTrace;
	}

	public static void throwException(String msg, Exception e) {
		if (e != null) { throw new IException(msg, e); }
	}

	public static void throwException(Exception e) {
		if (e != null) { throw new IException(e); }
	}

	public static void throwException(String msg) {
		if (msg != null) { throw new IException(msg); }
	}
}
