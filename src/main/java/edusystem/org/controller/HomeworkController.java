package edusystem.org.controller;

import edusystem.org.common.entity.Result;
import edusystem.org.entity.Course;
import edusystem.org.entity.Homework;
import edusystem.org.service.HomeworkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 田泽鑫
 * @date 2019/12/20
 */
@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;
    // 教师发布作业
    @RequiresPermissions("hw:pulish")
    @PostMapping("/publishHw")
    public Result publishHw(@RequestBody Homework homework){

        return this.homeworkService.publishHw(homework);
    }

    //教师编辑作业
    @RequiresPermissions("hw:edit")
    @PostMapping("/editHw")
    public Result editHw(@RequestBody Homework homework){

        return this.homeworkService.editHw(homework);
    }
    //教师删除作业
    @RequiresPermissions("hw:delete")
    @GetMapping("/hwDelete")
    public Result hwDelete(@RequestParam int homewrokId){

        return this.homeworkService.hwDelete(homewrokId);
    }
    //根据课程id  查询该课程作业列表(分教师 学生两种情况 service实现)
    @RequiresPermissions("hw:get")
    @GetMapping("/getHwList")
    public Result getHwList(@RequestParam int courseId){

        return this.homeworkService.getHwList(courseId);
    }

    //获取待阅学生
    @RequiresPermissions("hw:get")
    @GetMapping("/getNeedReadList")
    public Result getNeedReadList(@RequestParam int homeworkId){
        return this.homeworkService.getNeedReadList(homeworkId);

    }
    //获取待阅学生页面的课程ID和名字
    @RequiresPermissions("hw:get")
    @GetMapping("/getCourseByHwId")
    public Course getCourseByHwId(@RequestParam int homeworkId){
        return this.homeworkService.getCourseByHwId(homeworkId);
    }

    //打分
    @RequiresPermissions("hw:show")
    @GetMapping("/giveMark")
    public Result giveMark(@RequestParam int homeworkId,@RequestParam int userId,@RequestParam double mark){
        return this.homeworkService.giveMark(homeworkId,userId,mark);
    }
    //批阅
    @RequiresPermissions("hw:show")
    @GetMapping("/readHomework")
    public Result readHomework(@RequestParam int homeworkId,@RequestParam int userId){
        return this.homeworkService.readHomework(homeworkId,userId);
    }
    //提交作业
    @RequiresPermissions("hw:submit")
    @GetMapping("/submitHomework")
    public Result submitHomework(@RequestParam int homeworkId){
        return this.homeworkService.submitHomework(homeworkId);
    }
}

