package edusystem.org.common.exception;

import edusystem.org.common.myEnum.ResultTypeEnum;

/**
 * @author 田泽鑫
 * @date 2019/11/26
 */
public class MyException extends RuntimeException{
    private Integer code;

    public MyException(ResultTypeEnum resultTypeEnum){
        this(resultTypeEnum.getCode(),resultTypeEnum.getMessage());
    }
    private MyException( Integer code,String message) {
        super(message);
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
