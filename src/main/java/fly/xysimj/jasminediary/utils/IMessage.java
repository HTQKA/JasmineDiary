package fly.xysimj.jasminediary.utils;

/**
 * @program: JasmineDiary
 * @ClassName IMessage
 * @description: 信息
 * @author: 徐杨顺
 * @create: 2021-12-10 12:51
 * @Version 1.0
 **/
public class IMessage {
    private String code;
    private Object message;

    public IMessage() {
    }

    public IMessage(String code, Object message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public static IMessage create(String code, Object message) {
        return new IMessage(code, message);
    }

    public static IMessage createOK(Object message) {
        return new IMessage(CODE_OK, message);
    }

    public static IMessage createWARN(Object message) {
        return new IMessage(CODE_WARN, message);
    }

    public static IMessage createERROR(Object message) {
        return new IMessage(CODE_ERROR, message);
    }

    // 前端会检测error这个参数,所以避免冲突,后面加上Code
    public boolean isErrorCode() {
        return CODE_ERROR.equals(code);
    }

    public boolean isOkCode() {
        return CODE_OK.equals(code);
    }

    public boolean isWarnCode() {
        return CODE_WARN.equals(code);
    }

//    public String toString() {
//        return IJSON.toJSONString(this);
//    }
    
    public final static String CODE_OK = "ok";
    public final static String CODE_WARN = "warn";
    public final static String CODE_ERROR = "error";

    public final static IMessage ERROR_PARAM = new IMessage(CODE_ERROR, "缺少参数");
}
