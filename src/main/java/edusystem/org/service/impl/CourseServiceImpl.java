package edusystem.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edusystem.org.common.entity.Result;
import edusystem.org.entity.Course;
import edusystem.org.entity.User;
import edusystem.org.mapper.CourseMapper;
import edusystem.org.service.CourseService;
import edusystem.org.util.UserRoleTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

import static edusystem.org.util.Constants.ROLE_STUDENT;
import static edusystem.org.util.Constants.ROLE_TEACHER;

/**
 * @author 田泽鑫
 * @date 2019/12/18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserRoleTool userRoleTool;
    @Override
    public Result getAll() {
        List<Course> courseList =null;
        String roleName = userRoleTool.getCurrentUserRole();
        User user = userRoleTool.getCurrentUser();
        //将已知非null的变量放在前面
        if (ROLE_TEACHER.equals(roleName)){
            courseList = courseMapper.selectList(new QueryWrapper<Course>()
                    .select("course_id","course_name","course_num","join_code","term_year","term_detail","teacher")
                    .lambda()
                    .eq(Course::getTeacher,user.getUserName())
                    .eq(Course::isDeleteStatus,0));
        }else if (ROLE_STUDENT.equals(roleName)){
            courseList = courseMapper.getCoursesByUserId(user.getUserId());
        }
        return new Result().info(courseList);
    }

    @Override
    public Result joinCourse(String joinCode) {
        Course course = courseMapper.selectOne(
                new QueryWrapper<Course>()
                        .lambda()
                        .eq(Course::getJoinCode, joinCode)
        );
        //从session获取用户信息
        User user = userRoleTool.getCurrentUser();
        if (!joinCode.isEmpty()){
            if (course!=null){
                courseMapper.joinCourse(user.getUserId(),joinCode);
                return new Result().success();
            }
        }
        return new Result().fail();
    }

    @Override
    public Result createCourse(Course course) {
        //从session获取用户信息
        User user = userRoleTool.getCurrentUser();
        //创建日期
        course.setCreateDate(new Date());
        // 产生6位长度的随机字符串 加课码
        course.setJoinCode(RandomStringUtils.randomAlphanumeric(6));
        //创建教师
        course.setTeacher(user.getUserName());
        int result = courseMapper.insert(course);
        if (result>0){
            return new Result().success();
        }
        return new Result().fail();
    }

    @Override
    public Result getCourseById(int courseId) {
        Course course = courseMapper.selectById(courseId);
        return new Result().info(course);
    }

    @Override
    public int getStuNumByID(int courseId) {
        int num =courseMapper.getStuNumByID(courseId);
        return num;
    }

    @Override
    public Result getStuNums(Course[] array) {
        Map map = new HashMap();
        for (int i = 0;i<array.length;i++){
            map.put(array[i].getCourseId(),getStuNumByID(array[i].getCourseId()));
        }
        return new Result().info(map);
    }

    @Override
    public Result courseEdit(Course course) {
        if (courseMapper.updateById(course)>0){
            return new Result().success();
        }
        return new Result().fail();
    }

    @Override
    public Result courseFile(int courseId) {
        String roleName = userRoleTool.getCurrentUserRole();
        User user = userRoleTool.getCurrentUser();
        //将已知非null的变量放在前面
        if (ROLE_TEACHER.equals(roleName)){
            if (courseMapper.update(null,new UpdateWrapper<Course>()
                    .set("delete_status",1)
                    .lambda()
                    .eq(Course::getCourseId,courseId))>0){
                return new Result().success();
            }
        }else if (ROLE_STUDENT.equals(roleName)){
            try {
                courseMapper.studentFile(courseId,user.getUserId());
                return new Result().success();
            }catch (Exception e){
                return new Result().fail();
            }
        }

        return new Result().fail();
    }

    @Override
    public Result courseDelete(int courseId) {
        String roleName = userRoleTool.getCurrentUserRole();
        User user = userRoleTool.getCurrentUser();
        if (ROLE_TEACHER.equals(roleName)){
            if (courseMapper.deleteById(courseId)>0){
                return new Result().success();
            }
        }else if (ROLE_STUDENT.equals(roleName)){
            try {
                courseMapper.deleteStuCourse(user.getUserId(),courseId);
                return new Result().success();
            }catch (Exception e){
                return new Result().fail();
            }
        }
        return new Result().fail();
    }

    @Override
    public Result courseFileRecover(int courseId) {
        String roleName = userRoleTool.getCurrentUserRole();
        User user = userRoleTool.getCurrentUser();
        if (ROLE_TEACHER.equals(roleName)){
            if (courseMapper.update(null,new UpdateWrapper<Course>()
                    .set("delete_status",0)
                    .lambda()
                    .eq(Course::getCourseId,courseId)) >0) {
                return new Result().success();
            }
        }else if (ROLE_STUDENT.equals(roleName)){
            try {
                courseMapper.recoverCourse(user.getUserId(),courseId);
                return new Result().success();
            }catch (Exception e){
                return new Result().fail();
            }
        }
        return new Result().fail();
    }

    @Override
    public Result getAllFileCourse() {
        List<Course> fileCourseList =null;
        String roleName = userRoleTool.getCurrentUserRole();
        User user = userRoleTool.getCurrentUser();
        //将已知非null的变量放在前面
        if (ROLE_TEACHER.equals(roleName)){
            fileCourseList = courseMapper.selectList(new QueryWrapper<Course>()
                    .select("course_id","course_name","teacher")
                    .lambda()
                    .eq(Course::isDeleteStatus,1));
        }else if (ROLE_STUDENT.equals(roleName)){
            fileCourseList = courseMapper.fileCourseList(user.getUserId());
        }
        return new Result().info(fileCourseList);
    }


}
