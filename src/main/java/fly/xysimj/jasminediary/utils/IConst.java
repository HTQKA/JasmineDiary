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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class IConst {
	// 系统定义级别参数
	public static final String DEFAULT_TOKEN_NAME = "wmmestoken";
	public static final String DEFAULT_AES_KEY = "DUbSB%qb*ounLxbHr2a&3GPhuDGy5kgw";
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final Charset DEFAULT_CHARSET_OBJ = Charset.forName("UTF-8");

	// Prop key
	public static final String I18N_KEY = "_res";
	public static final String I18N_LANGUAGE_PROPERTIES = "sys.i18n.defaultLanguage";
	public static final String I18N_LOCALE_COOKIE = "_TQ_I18N_";

	// 预定义空元素
	public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
	public static final String EMPTY_STRING = "";
	public static final ArrayList<?> NULL_ELEMENT_LIST = new ArrayList<>();
	public static final String EMPTY_JSON_STR = "{}";

	// 数据库约定字段定义
	public static final String PK = "id"; // 定义默认主键字段
	public static final String ADD_ORG = "add_org";// 机构戳
	public static final String ADD_DEPT = "add_dept";// 部门戳
	public static final String ADD_USER = "add_user";// 人员戳
	public static final String ADD_TIME = "add_time";// 时间戳
	public static final String LAST_MODIFY_USER = "lm_user";// 人员戳
	public static final String LAST_MODIFY_TIME = "lm_time";// 时间戳
	// log中需要隐藏的字段
	public static final HashSet<String> SECRET_PARAMS = new HashSet<>(Arrays.asList("pwd", "password", "passwords"));
	public static final int DEFAULT_BATCH_SIZE = 100; // 默认事务的Batch Size

	// 用户参数
	public static final String USER_COOKIE = "_U_"; // 存储user的cookie
	public static final String USER_SALT = "zcqm";// 存入cache的user加上的salt(不是密码的salt)
	public static final String USER_LOGIN_STATUS_COOKIE = "_US_"; // 存储user的cookie
	public static final String USER_ISLOGIN_STATUS = "_UILS_"; // 存储User是否登录的变量
	public static final String AUTH_LEVEL = "_AL_"; // 存放当前方法的AUTH_LEVEL
	public static final Long ADMIN_ROLEID = 1l;// 重要:系统管理员角色ID,有此ID的role,将跳过所有权限判断
	public static final String USER_PEMIT_STATUS = "_PS_"; // 存储授权状态,详见AuthInterceptor
	public static final String USER_PEMIT_FUNCTION = "_PF_"; // 存储授权状态,详见AuthInterceptor
	public static final String CHECK_AUTH_PARA = "_CA"; // 只检查权限时的参数
	public static final String SYSTEM_USER = "SYSTEM"; // 系统自动操作时,使用的USER TITLE
	public static final String LOGIN_MODE = "_LM";// 登录模式 0 : 正常登录 1 : SK登录
	// 权限参数
	public static final String FUNC_DEFAULT_SPLIT = "#"; // 特殊权限分隔符

	// Controller 页面参数
	public static final String BASE_INFO = "baseInfo";
	public static final String ERROR_MSG = "errorMsg";
	public static final String RESULT_CODE = "resultCode";
	public static final String RESULT_FORMAT = "resultFormat";
	public static final String KEEP_PARA = "keepParaMap";
	public static final String JS_REFRESH = "jsrefresh"; // 左边菜单默认参数,区别左侧链接和页面内链接,清空保存的查询条件用,需要和util.js中的对应
	public static final String TRANSACTION_CODE = "trCode";// 一次Controller请求唯一的事务代码
	public static final String JSONP_PARAMETER = "jsonpCallback";// jsonp约定callback的参数
	public static final String CURR_ORG = "currOrg";
	public static final String CURR_DEPT = "currDept";
	public static final String CURR_USER = "currUser";

	// SSO 约定参数
	public static final String SSO_RETURN = "_RU_";// SSO默认传入的返回URL
	public static final String SSO_APP_PARA = "_APP_";// SSO约定传入app标识的参数

	// Controller BaseInfo 参数
	public static final String ACTION_KEY = "currActionKey";
	public static final String URL_PARA = "currUrlPara";
	public static final String QUERY_STRING = "currQueryString";

	// Controller 约束参数
	public static final String LOGIN_SKEY_PARA = "_SK_";// 使用skey登录的参数Key
	public static final String LOGIN_SKEY_CONSTRAINT = "_SKV_";// skey登录参数的约束的参数Key

	// Controller 输出模式参数
	public static final String REQUEST_MODE_JSON = "jsonMode";
	public static final String REQUEST_MODE_JSONP = "jsonpMode";
	public static final String REQUEST_MODE_EXPORT = "exportMode";
	public static final String PAGE_VIEW_MODE = "viewMode";
	public static final String RETURN_MODE = "returnMode";
	public static final Integer EXPORT_MAX_ROWS = 10000; // 单次最大导出数量

	public static final String PAGE_SIZE_PREFIX = ""; // 实际单页数量

	// 主键规则
	public static final int PK_RULE_NULL = -1;// 返回NULL,等同于isAutoID=false
	public static final int PK_RULE_LONGVAL = 0; // 使用IUtils.getNextID()生成下一个ID,格式为Long int8
	public static final int PK_RULE_MAXINC = 1;// 查询表,自动使用最大的ID+1,初始ID为 ct_sys_parameter表中SYS_SERIAL * 100000
	public static final int PK_RULE_GUID = 2;// 使用UUID,返回为String
	public static final int PK_RULE_ORGSERIAL = 3;// 查询表,ORG_ID作为前缀,后加序列 比如 10011,10012,....100110,.....10011000 (Long类型)
	public static final int PK_RULE_DEFAULT = PK_RULE_MAXINC; // 默认 = PK_RULE_MAXINC

	// 常用分隔符
	public static final String SPLIT_HASH = "#";
	public static final String SPLIT_COLON = ":";
	public static final String SPLIT_COMMA = ",";
	public static final String SPLIT_DOT = ".";
	public static final String SPLIT_AT = "@";
	public static final String SPLIT_MINUS = "-";
	public static final String SPLIT_ADD = "+";
	public static final String SPLIT_SLASH = "/";
	public static final String SPLIT_BACKSLASH = "\\";
	public static final String SPLIT_UNDERLINE = "_";

	public static final String SPLIT_PAIRAT = "@@";
	public static final String SPLIT_DOUBLESLASH = "//";
	public static final String SPLIT_ARRAY3 = " >>> ";

	public static final char SPLIT_HASH_C = '#';
	public static final char SPLIT_COLON_C = ':';
	public static final char SPLIT_COMMA_C = ',';
	public static final char SPLIT_DOT_C = '.';
	public static final char SPLIT_AT_C = '@';
	public static final char SPLIT_MINUS_C = '-';
	public static final char SPLIT_ADD_C = '+';
	public static final char SPLIT_SLASH_C = '/';
	public static final char SPLIT_BACKSLASH_C = '\\';
	public static final char SPLIT_UNDERLINE_C = '_';

	// 定义readerJSON不解析的attr
	public static final String[] NOT_RENDER_JSON = { I18N_KEY, USER_ISLOGIN_STATUS, AUTH_LEVEL, USER_LOGIN_STATUS_COOKIE, KEEP_PARA, ACTION_KEY, URL_PARA, QUERY_STRING,
			RESULT_CODE, RESULT_FORMAT, REQUEST_MODE_JSON, REQUEST_MODE_EXPORT, JSONP_PARAMETER, CURR_ORG, CURR_DEPT, CURR_USER, PAGE_VIEW_MODE, "breadcrumbHtml", "ctx", "user" };

	// CODE MESSAGE
	public static final ConcurrentHashMap<Integer, String> CM = new ConcurrentHashMap<>();

	static {
		NULL_ELEMENT_LIST.add(null);
		CM.put(1, "[#0#]");
		// 信息对应的MAP //替代字符串[#0#][#1#]
		CM.put(-1, "用户不存在或密码错误.");
		CM.put(-2, "登录超时.");
		CM.put(-3, "用户名不能为空");
		CM.put(-4, "用户名不存在");
		CM.put(-5, "用户未启用短信校验");
		CM.put(-6, "用户已启用短信校验, 但未维护手机号");
		CM.put(-7, "短信校验码输入有误");
		CM.put(-1000, "【[#0#]】");
		CM.put(-1001, "此任务下还有报工未确认,完成失败!");
		CM.put(-1002, "此工单还没有进行任何报工,请先执行【重新排产】之后,再使用【工单取消】!");
		CM.put(-1003, "报工数量不对应,请检查是否已经完成报工!");
		CM.put(-1004, "还有工单在生产，订单不能结束!");
		CM.put(-1005, "SAP入库同步有错误，请稍后刷新重试!");
		CM.put(-1006, "存在已经确认的入库单!");
		CM.put(-1007, "A物料库存不足!");
		CM.put(-1008, "[#0#]");// AB料重复制造
		CM.put(-1009, "此任务下还有入库未确认,完成失败!");

		CM.put(-2001, "没有人工信息,请确认是否已经排班!");

		CM.put(-10000, "报工单【[#0#]】物料报工同步sap出错!");
	}

}
