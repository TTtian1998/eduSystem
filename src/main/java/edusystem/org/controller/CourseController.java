package edusystem.org.controller;

import edusystem.org.common.entity.Result;
import edusystem.org.common.exception.CourseException;
import edusystem.org.common.myEnum.ResultTypeEnum;
import edusystem.org.entity.Course;
import edusystem.org.service.CourseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 田泽鑫
 * @date 2019/11/28
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //查询所有课程
    @RequiresPermissions("course:list")
    @PostMapping("/getAll")
    public Result getAll(){
        return this.courseService.getAll();
    }

    //查询所有归档课程
    @RequiresPermissions("more:file")
    @PostMapping("/getAllFileCourse")
    public Result getAllFileCourse(){
        return this.courseService.getAllFileCourse();
    }
    //根据加课码加入课程
    @RequiresPermissions("banner:join")
    @PostMapping("/joinCourse")
    public Result joinCourse(@RequestBody Course course){
        return this.courseService.joinCourse(course.getJoinCode());
    }

    //教师创建课程
    @RequiresPermissions("banner:joinANDcreate")
    @PostMapping("/createCourse")
    public Result createCourse(@RequestBody Course course){
        return this.courseService.createCourse(course);
    }

    //根据Id查询课程
    @RequiresPermissions("course:getOne")
    @GetMapping("/getCourseById")
    public Result getCourseById(@RequestParam int courseId){
        return this.courseService.getCourseById(courseId);
    }

    //根据Id查询选修该课程人数
    @RequiresPermissions("more:showCNum")
    @PostMapping("/getStuNums")
    public Result getStuNums(@RequestBody Course[] courseIds){
        if (courseIds.length==0){
            throw new CourseException(ResultTypeEnum.PARAM_ERROR);
        }
        return courseService.getStuNums(courseIds);
    }

    //课程归档
    @RequiresPermissions("more:file")
    @GetMapping("/courseFile")
    public Result courseFile(@RequestParam(value = "courseId", required = true) int courseId){
        return courseService.courseFile(courseId);
    }

    //课程归档恢复
    @RequiresPermissions("more:file")
    @GetMapping("/courseFileRecover")
    public Result courseFileRecover(@RequestParam int courseId){
        return courseService.courseFileRecover(courseId);
    }

    //课程编辑
    @RequiresPermissions("more:edit")
    @PostMapping("/courseEdit")
    public Result courseEdit(@RequestBody Course course){
        return courseService.courseEdit(course);
    }

    //课程删除
    @RequiresPermissions("more:delete")
    @GetMapping("/courseDelete")
    public Result courseDelete(@RequestParam int courseId){
        return courseService.courseDelete(courseId);
    }

}
