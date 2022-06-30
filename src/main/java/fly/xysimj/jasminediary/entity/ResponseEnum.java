package fly.xysimj.jasminediary.entity;

/**
 * @author TheShun
 */

public enum ResponseEnum {
    SUCCESS(200,"成功"),
    USER_REG_USER_PASSWORD_CODE(401,"用户名和密码错误"),
    USER_REG_USER_PASSWORD_CONFIRM(402,"密码和确认密码不一致"),
    ORDER_FAIL(601,"订单失败"),
    ORDER_MESSAGE_FAIL(602,"订单发送消息失败") ;

    private Integer code;
    private String message;

    ResponseEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}
