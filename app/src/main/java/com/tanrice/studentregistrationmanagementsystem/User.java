package com.tanrice.studentregistrationmanagementsystem;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id(autoincrement = true)
    private long id;
    private String userName;
    private String userpwd;
    private int age;
    private String gender;
    private String name;
    private String  school;
    private  String academy;
    private  int  registrationProject;
    @Generated(hash = 886357983)
    public User(long id, String userName, String userpwd, int age, String gender,
            String name, String school, String academy, int registrationProject) {
        this.id = id;
        this.userName = userName;
        this.userpwd = userpwd;
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.school = school;
        this.academy = academy;
        this.registrationProject = registrationProject;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserpwd() {
        return this.userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
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
    public String getAcademy() {
        return this.academy;
    }
    public void setAcademy(String academy) {
        this.academy = academy;
    }
    public int getRegistrationProject() {
        return this.registrationProject;
    }
    public void setRegistrationProject(int registrationProject) {
        this.registrationProject = registrationProject;
    }
}
