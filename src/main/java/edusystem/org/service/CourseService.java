package edusystem.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edusystem.org.common.entity.Result;
import edusystem.org.entity.Course;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/12/18
 */
public interface CourseService extends IService<Course> {
    //获取所有课程
    Result getAll();
    //学生根据加课码加入课程
    Result joinCourse(String joinCode);
    //教师创建课程
    Result createCourse(Course course);
    //根据课程Id获取课程
    Result getCourseById (int courseId);
    //根据课程id获取选修该门课的人数
    int getStuNumByID(int courseId);
    //用map形式存储 key:课程Id,value:选修该课程人数
    Result getStuNums(Course[] array);
    //课程编辑
    Result courseEdit(Course course);
    //课程归档
    Result courseFile(int courseId);
    //删除课程
    Result courseDelete(int courseId);
    //归档课程恢复
    Result courseFileRecover(int courseId);
    //获取所有归档课程
    Result getAllFileCourse();
}
