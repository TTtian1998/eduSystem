package edusystem.org.common.exception;

import edusystem.org.common.myEnum.ResultTypeEnum;

/**
 * @author 田泽鑫
 * @date 2019/12/19
 */
public class CourseException extends MyException {
    public CourseException(ResultTypeEnum resultTypeEnum) {
        super(resultTypeEnum);
    }
}
