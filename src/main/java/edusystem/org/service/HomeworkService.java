package edusystem.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edusystem.org.common.entity.Result;
import edusystem.org.entity.Course;
import edusystem.org.entity.Homework;

/**
 * @author 田泽鑫
 * @date 2019/12/20
 */
public interface HomeworkService extends IService<Homework> {
    //发布作业
    Result publishHw(Homework homework);
    //编辑作业
    Result editHw(Homework homework);
    //删除作业
    Result hwDelete(int homeworkId);
    //获取作业列表
    Result getHwList(int courseId);
    //获取待批学生列表
    Result getNeedReadList(int homeworkId);
    //获取课程
    Course getCourseByHwId(int homeworkId);
    //给学生打分
    Result giveMark(int homeworkId,int userId,double mark);
    //批阅学生作业状态
    Result readHomework(int homeworkId,int userId);
    //学生提交作业
    Result submitHomework(int homeworkId);
}
