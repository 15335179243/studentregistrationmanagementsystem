package com.tanrice.studentregistrationmanagementsystem.basedata;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class UserProject {
    @Id
    private Long studentNumber;
    private String json;
    @Generated(hash = 892236047)
    public UserProject(Long studentNumber, String json) {
        this.studentNumber = studentNumber;
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
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "UserProject{" +
                "studentNumber=" + studentNumber +
                ", json='" + json + '\'' +
                '}';
    }
}
