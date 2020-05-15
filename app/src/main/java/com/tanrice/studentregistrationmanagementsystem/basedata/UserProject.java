package com.tanrice.studentregistrationmanagementsystem.basedata;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
//院系 id 学号 姓名 身份（学生教师）
public class UserProject {
    @Id
    private Long studentNumber;
    private String department;
    private Boolean student;
    private Boolean teacher;
    private String name;
    private String userName;
    private String gender;
    private String json;
    @Generated(hash = 769233858)
    public UserProject(Long studentNumber, String department, Boolean student,
            Boolean teacher, String name, String userName, String gender,
            String json) {
        this.studentNumber = studentNumber;
        this.department = department;
        this.student = student;
        this.teacher = teacher;
        this.name = name;
        this.userName = userName;
        this.gender = gender;
        this.json = json;
    }
    @Generated(hash = 1123305143)
    public UserProject() {
    }
    public Long getStudentNumber() {
        return this.studentNumber;
    }
    public void setStudentNumber(Long studentNumber) {
        this.studentNumber = studentNumber;
    }
    public String getDepartment() {
        return this.department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public Boolean getStudent() {
        return this.student;
    }
    public void setStudent(Boolean student) {
        this.student = student;
    }
    public Boolean getTeacher() {
        return this.teacher;
    }
    public void setTeacher(Boolean teacher) {
        this.teacher = teacher;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }

    public UserProject(Long studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "UserProject{" +
                "studentNumber=" + studentNumber +
                ", department='" + department + '\'' +
                ", student=" + student +
                ", teacher=" + teacher +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", json='" + json + '\'' +
                '}';
    }
}
