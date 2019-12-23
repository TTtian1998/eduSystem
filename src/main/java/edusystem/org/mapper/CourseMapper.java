package edusystem.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edusystem.org.common.entity.Result;
import edusystem.org.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/12/18
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    // 学生根据userId获取未归档且加入课程
    @Select("select c.course_id,course_name,course_num,join_code,term_year,term_detail,teacher from \n" +
            "course c,user_course uc where c.course_id = uc.course_id and uc.user_id =#{userId} and c.delete_status =0 and c.course_id in \n" +
            "(select course_id from user_course where user_id = #{userId} and delete_status =0 GROUP BY course_id) ")
    List<Course> getCoursesByUserId(int userId );
    //学生加入课程
    @Insert("insert into user_course (user_id,course_id) values " +
            "(#{userId},(select course_id from course where join_code = #{joinCode})) ")
    void joinCourse(int userId,String joinCode);
    //根据课程ID获得返回的课程数
    @Select("select COUNT(user_id) stuNum from user_course where course_id = #{courseId}")
    int getStuNumByID(int courseId);
    //学生归档课程
    @Update("update user_course set delete_status = 1 where course_id=#{courseId} and user_id = #{userId}")
    void studentFile(int courseId,int userId);
    //学生获取归档课程
    @Select("select course_id,course_name,teacher from course where course_id in (select course_id from user_course where user_id = #{userId} and delete_status =1)")
    List<Course> fileCourseList(int userId);
    //学生归档课程恢复
    @Update("update user_course set delete_status = 0 where user_id = #{userId} and course_id = #{courseId}")
    void recoverCourse(int userId,int courseId);
    //学生删除课程
    @Delete("delete from user_course where user_id = #{userId} and course_id = #{courseId}")
    void deleteStuCourse(int userId,int courseId);

}
