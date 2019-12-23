package edusystem.org.common.exception;

import edusystem.org.common.myEnum.ResultTypeEnum;

/**
 * @author 田泽鑫
 * @date 2019/11/27
 */
public class LoginException extends MyException {
    public LoginException(ResultTypeEnum resultTypeEnum) {
        super(resultTypeEnum);
    }
}
