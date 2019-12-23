package edusystem.org.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author 田泽鑫
 * @date 2019/11/28
 */
@TableName("course")
public class Course {
    @TableId(value = "course_id",type = IdType.AUTO)
    private int courseId;
    @TableField("course_name")
    private String courseName;
    //班级代号
    @TableField("course_num")
    private String courseNum;
    @TableField("join_code")
    private String joinCode;
    @TableField("term_year")
    private String termYear;
    @TableField("term_detail")
    private String termDetail;
    @TableField("create_date")
    private Date createDate;
    @TableField("join_picture")
    private String joinPicture;
    @TableField("delete_status")
    private boolean deleteStatus;
    //创建教师
    @TableField("teacher")
    private String teacher;
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public String getTermYear() {
        return termYear;
    }

    public void setTermYear(String termYear) {
        this.termYear = termYear;
    }

    public String getTermDetail() {
        return termDetail;
    }

    public void setTermDetail(String termDetail) {
        this.termDetail = termDetail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getJoinPicture() {
        return joinPicture;
    }

    public void setJoinPicture(String joinPicture) {
        this.joinPicture = joinPicture;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
