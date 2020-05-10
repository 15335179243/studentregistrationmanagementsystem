package com.tanrice.studentregistrationmanagementsystem;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private String userName;
    private String userPwd;
    private int age;
    private String gender;
    private String name;
    private String school;
    private String studentNumber;
    private String department;
    private Boolean student;
    private Boolean teacher;
    private int registrationProject;
    @Generated(hash = 1672106176)
    public User(Long id, String userName, String userPwd, int age, String gender,
            String name, String school, String studentNumber, String department,
            Boolean student, Boolean teacher, int registrationProject) {
        this.id = id;
        this.userName = userName;
        this.userPwd = userPwd;
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.school = school;
        this.studentNumber = studentNumber;
        this.department = department;
        this.student = student;
        this.teacher = teacher;
        this.registrationProject = registrationProject;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPwd() {
        return this.userPwd;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSchool() {
        return this.school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getStudentNumber() {
        return this.studentNumber;
    }
    public void setStudentNumber(String studentNumber) {
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
    public int getRegistrationProject() {
        return this.registrationProject;
    }
    public void setRegistrationProject(int registrationProject) {
        this.registrationProject = registrationProject;
    }


}
