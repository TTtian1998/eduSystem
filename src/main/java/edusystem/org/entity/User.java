package edusystem.org.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author 田泽鑫
 * @date 2019/11/21
 */
@TableName("user")
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private int userId;
    @TableField("user_name")
    private String userName;
    @TableField("account")
    private String account;
    @TableField("password")
    private String password;
    @TableField("create_date")
    private Date createDate;
    @TableField("delete_status")
    private boolean deleteStatus;
    @TableField("school")
    private String school;
    // 学生学号/教师工号
    @TableField("user_num")
    private int userNum;

    /**
     * 角色属性，数据库中不存在该字段
     */
    @NotBlank(message = "{required}")
    @TableField(exist = false)
    private int roleId;
    @TableField(exist = false)
    private int readStatus;
    @TableField(exist = false)
    private double mark;
    @TableField(exist = false)
    private int submitStatus;
    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public int getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }
}
