package edusystem.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edusystem.org.common.entity.Result;
import edusystem.org.controller.FileUpload;
import edusystem.org.entity.Course;
import edusystem.org.entity.Homework;
import edusystem.org.entity.User;
import edusystem.org.mapper.HomeworkMapper;
import edusystem.org.service.HomeworkService;
import edusystem.org.util.UserRoleTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static edusystem.org.util.Constants.ROLE_STUDENT;
import static edusystem.org.util.Constants.ROLE_TEACHER;

/**
 * @author 田泽鑫
 * @date 2019/12/20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private UserRoleTool userRoleTool;
    @Override
    public Result publishHw(Homework homework) {
        User userInfo = userRoleTool.getCurrentUser();
        homework.setTeacher(userInfo.getUserName());
        homework.setCreateDate(new Date());
        homework.setUrls(FileUpload.urls);
        if (homework!=null){
            //发布作业成功
            if (homeworkMapper.insert(homework)>0){
                //获取所有选修该课程的学生
                List<User> list = homeworkMapper.getUserIdsByCId(homework.getCourseId());
                //循环向关联表插入数据
                for (User user : list){
                    int lastHwId = homeworkMapper.getLastInsetId();
                    homeworkMapper.insertHwUser(user.getUserId(),lastHwId);
                }
                return new Result().success();
            }
        }
        return new Result().fail();
    }

    @Override
    public Result editHw(Homework homework) {
        if (homework!=null){
            if (homeworkMapper.updateById(homework)>0){
                return new Result().success();
            }
        }
        return new Result().fail();
    }

    @Override
    public Result hwDelete(int homeworkId) {
        if (homeworkId>0){
            if (homeworkMapper.deleteById(homeworkId)>0){
                return new Result().success();
            }
        }
        return new Result().fail();
    }

    @Override
    public Result getHwList(int courseId) {
        List<Homework> homeworkList = null;
        String roleName = userRoleTool.getCurrentUserRole();
        User user = userRoleTool.getCurrentUser();
        //将已知非null的变量放在前面
        if (ROLE_TEACHER.equals(roleName)){
            homeworkList = homeworkMapper.getHwTeacherList(courseId);
        }else if (ROLE_STUDENT.equals(roleName)){

            homeworkList = homeworkMapper.getHwStuList(courseId,user.getUserId());
        }
        return new Result().info(homeworkList);
    }

    @Override
    public Result getNeedReadList(int homeworkId) {
        List<User> userList = null;
        userList = homeworkMapper.getNeedReadList(homeworkId);
        return new Result().info(userList);
    }

    @Override
    public Course getCourseByHwId(int homeworkId) {
        if (homeworkId>0){
            return homeworkMapper.getCourseByHwId(homeworkId);
        }
        return null;
    }

    @Override
    public Result giveMark(int homeworkId, int userId,double mark) {
        double maxMark = homeworkMapper.selectOne(new QueryWrapper<Homework>().lambda().eq(Homework::getHomeworkId,homeworkId)).getMaxMark();
        if (homeworkId>0 && userId>0 && 0<=mark &&mark<=maxMark){
            homeworkMapper.giveMark(homeworkId,userId,mark);
            return new Result().success();
        }
        return new Result().fail();
    }

    @Override
    public Result readHomework(int homeworkId, int userId) {
        if (homeworkId>0&&userId>0){
            homeworkMapper.readHomework(homeworkId,userId);
            return new Result().success();
        }
        return new Result().fail();
    }

    @Override
    public Result submitHomework(int homeworkId) {
        User user = userRoleTool.getCurrentUser();
        try {
            homeworkMapper.submit(homeworkId,user.getUserId(),FileUpload.submitUrl);
            return new Result().success();
        }catch (Exception e){
            return new Result().fail();
        }
    }
}
