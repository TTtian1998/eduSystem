package edusystem.org.common.util;

import edusystem.org.common.entity.Result;
import edusystem.org.common.exception.LoginException;
import edusystem.org.common.exception.MyException;
import edusystem.org.common.myEnum.ResultTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 田泽鑫
 * @date 2019/11/27
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
    //处理所有不可知的异常
    @ExceptionHandler({Exception.class})    //申明捕获那个异常类
    public Result globalExceptionHandler(Exception e) {
        this.logger.error(e.getMessage(), e);
        return new Result().fail();
    }

    //处理所有业务异常
    @ExceptionHandler({MyException.class})
    public Result BusinessExceptionHandler(MyException e) {
        this.logger.error(e);
        return new Result().fail();
    }
    //处理登录异常
    @ExceptionHandler({LoginException.class})
    public Result LoginExceptionHandler(LoginException e) {
        this.logger.error(e);
        return new Result().fail();
    }
}
