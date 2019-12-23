package edusystem.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edusystem.org.common.entity.Result;
import edusystem.org.entity.Course;
import edusystem.org.entity.Homework;
import edusystem.org.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/12/20
 */
@Mapper
public interface HomeworkMapper extends BaseMapper<Homework> {
    //学生获取作业列表
    @Select("select h.homework_id,homework_name,create_date,urls,deadline,maxMark,teacher,brief,hu.submit_status\n" +
            "from homework h \n" +
            "LEFT JOIN hw_user hu ON (h.homework_id = hu.homework_id)\n" +
            "where h.homework_id in (select homework_id from homework  where course_id = #{courseId} and delete_status=0)\n" +
            "and hu.user_id =#{userId} ORDER BY h.create_date DESC")
    List<Homework> getHwStuList(int courseId,int userId);

    //教师获取作业列表
    @Select("select h.homework_id,homework_name,create_date,urls,deadline,maxMark,teacher,brief,COUNT(IF((read_status=1 and submit_status=1),true,null)) as readed,\n" +
            "       COUNT(IF((read_status=0 and submit_status=1),true,null)) as unread,COUNT(IF(submit_status=0,true,null)) as unsubmit \n" +
            "from homework h,hw_user hu where hu.homework_id = h.homework_id and hu.homework_id in (select homework_id from homework  where course_id = #{courseId}) and h.delete_status=0\n" +
            "GROUP BY h.homework_id ORDER BY h.create_date DESC\n")
    List<Homework> getHwTeacherList(int courseId);

    //教师发布一门作业->获取到发布作业的作业id和课程id ->根据课程id查询选修此门课的学生的id->把作业id和userid插入到hu表
    @Insert("insert into hw_user (user_id,homework_id) values(#{userId},#{homeworkId})")
    void insertHwUser(int userId,int homeworkId);
    //根据课程Id查询用户Id
    @Select("select user_id from user_course where course_id= #{courseId}")
    List<User> getUserIdsByCId(int courseId);
    //获取到最新插入的作业Id
    @Select("select max(homework_id) from homework ")
    int getLastInsetId();

    @Select("SELECT u.user_id,user_num,user_name,hu.mark,hu.read_status,hu.submit_status FROM user u\n" +
            "LEFT JOIN hw_user hu on (u.user_id = hu.user_id)\n" +
            "where hu.homework_id =#{homeworkId} and u.user_id in (select user_id from hw_user where homework_id = #{homeworkId} GROUP BY user_id) GROUP BY u.user_id")
    //根据作业Id查询该作业的完成情况 即获取待批阅学生列表
    List<User> getNeedReadList(int homeworkId);

    //根据作业Id查询作业
    @Select("SELECT course_id,course_name FROM course WHERE course_id in(SELECT course_id from homework WHERE homework_id =#{homeworkId})\n")
    Course getCourseByHwId(int homeworkId);
    //给分 同时批阅
    @Update("update hw_user set mark=#{mark},read_status = 1 where homework_id=#{homeworkId} and user_id = #{userId} and submit_status=1")
    void giveMark(int homeworkId, int userId, double mark);

    //批阅
    @Update("update hw_user set read_status = 1 where homework_id=#{homeworkId} and user_id = #{userId} and submit_status=1")
    void readHomework(int homeworkId, int userId);

    //提交作业
    @Update("update hw_user set submit_status = 1,submit_url = #{submit_url} where homework_id=#{homeworkId} and user_id = #{userId}")
    void submit(int homeworkId,int userId,String submit_url);
}
