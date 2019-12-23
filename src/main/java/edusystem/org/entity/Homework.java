package edusystem.org.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/12/20
 */
@TableName("homework")
public class Homework {
    @TableId(value = "homework_id",type = IdType.AUTO)
    private int homeworkId;
    @TableField("course_id")
    private int courseId;
    @TableField("homework_name")
    private String homeworkName;
    @TableField("urls")
    private String urls;
    @TableField("create_date")
    private Date createDate;
    @TableField("deadline")
    private Date deadline;
    @TableField("maxMark")
    private double maxMark;
    @TableField("teacher")
    private String teacher;
    @TableField("brief")
    private String brief;
    @TableField(exist = false)
    private int readed;
    @TableField(exist = false)
    private int unread;
    @TableField(exist = false)
    private int unsubmit;
    @TableField(exist = false)
    private int submitStatus;

    public int getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public int getUnsubmit() {
        return unsubmit;
    }

    public void setUnsubmit(int unsubmit) {
        this.unsubmit = unsubmit;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public double getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(double maxMark) {
        this.maxMark = maxMark;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
