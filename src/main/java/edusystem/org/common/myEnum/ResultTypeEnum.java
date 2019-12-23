package edusystem.org.common.myEnum;

/**
 * @author 田泽鑫
 * @date 2019/11/22
 *@description: 响应状态枚举类
 */
public enum ResultTypeEnum {
    /**
    * 响应信息枚举
    * */
    SERVICE_SUCCESS(200,"成功"),
    PARAM_ERROR(40001,"入参异常"),
    ERROR_20011(20011, "登陆已过期,请重新登陆"),
    SERVICE_ERROR(500,"服务异常"),
    LOGIN_ERROR(414,"登录失败"),
    REGIST_FAILED(415,"注册失败"),
    OPERATE_FAIL(444,"操作失败");
    private Integer code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
