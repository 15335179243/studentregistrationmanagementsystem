package com.tanrice.studentregistrationmanagementsystem.basedata;

import java.util.ArrayList;
import java.util.List;

public class BeanList {


    private String school = "阳光学院";
    private List<String> department;
    private TProject studentProject;
    private TProject teacherProject;

    public BeanList(String school, List<String> department, TProject studentProject, TProject teacherProject) {
        this.school = school;
        this.department = department;
        this.studentProject = studentProject;
        this.teacherProject = teacherProject;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<String> getDepartment() {
        return department;
    }

    public void setDepartment(List<String> department) {
        this.department = department;
    }

    public TProject getStudentProject() {
        return studentProject;
    }

    public void setStudentProject(TProject studentProject) {
        this.studentProject = studentProject;
    }

    public TProject getTeacherProject() {
        return teacherProject;
    }

    public void setTeacherProject(TProject teacherProject) {
        this.teacherProject = teacherProject;
    }

    public static class TProject {
        private List<String> track;
        private List<String> fieldEvents;

        public TProject(List<String> track, List<String> fieldEvents) {
            this.track = track;
            this.fieldEvents = fieldEvents;
        }

        public List<String> getTrack() {
            return track;
        }

        public void setTrack(List<String> track) {
            this.track = track;
        }

        public List<String> getFieldEvents() {
            return fieldEvents;
        }

        public void setFieldEvents(List<String> fieldEvents) {
            this.fieldEvents = fieldEvents;
        }
    }

    public static List<BeanList> getData() {
        ArrayList<BeanList> lists = new ArrayList<>();
        ArrayList<String> department = new ArrayList<>();
        department.add("请选择--");
        department.add("土木学院");
        department.add("人工智能学院");
        department.add("外语系");
        department.add("商学院");
        department.add("公共教学队");
        department.add("机关联队");
        department.add("法律系");
        department.add("艺术系");
        department.add("人文学院");
        ArrayList<String> studentTrack = new ArrayList<>();
        studentTrack.add("请选择--");
        studentTrack.add("男子100 米");
        studentTrack.add("女子100米");
        studentTrack.add("男子1000");
        studentTrack.add("女子800");
        studentTrack.add("男子3000");
        studentTrack.add("女子3000");
        studentTrack.add("男5000");
        studentTrack.add("男子4x100");
        studentTrack.add("女子4x100");
        studentTrack.add("男子110米栏");
        ArrayList<String> studentFieldEvents = new ArrayList<>();
        studentFieldEvents.add("请选择--");
        studentFieldEvents.add("铅球");
        studentFieldEvents.add("跳远");
        studentFieldEvents.add("三级跳");
        TProject studentProject = new TProject(studentTrack, studentFieldEvents);

        ArrayList<String> teacherTrack = new ArrayList<>();
        teacherTrack.add("请选择--");
        teacherTrack.add("教工女子100米");
        teacherTrack.add("教工男子100米");
        teacherTrack.add("教工女子200米");
        teacherTrack.add("教工女子800米");
        teacherTrack.add("教工男子400米");
        teacherTrack.add("教工女子1500米");
        teacherTrack.add("教工女子青年4x100米混合接力");
        ArrayList<String> teacherFieldEvents = new ArrayList<>();
        teacherFieldEvents.add("请选择--");
        teacherFieldEvents.add("教工男子青年铅球");
        teacherFieldEvents.add("教工男子青年跳高");
        teacherFieldEvents.add("教工女子青年跳远");
        teacherFieldEvents.add("教工中老年飞镖");
        teacherFieldEvents.add("教工中老年足踢保龄球");
        teacherFieldEvents.add("教工中老年拖球跑");
        teacherFieldEvents.add("教工女子青年三角拔河比赛");
        teacherFieldEvents.add("教工中老年定点投篮");
        teacherFieldEvents.add("教工女子青年三角拔河比赛");
        teacherFieldEvents.add("教工女子铅球");
        teacherFieldEvents.add("教工女子跳高");
        teacherFieldEvents.add("教工女子三分钟8字绳");
        TProject teacherProject = new TProject(teacherTrack, teacherFieldEvents);
        BeanList beanList = new BeanList("阳光学院", department, studentProject, teacherProject);
        lists.add(beanList);

        return lists;
    }

    public static List<String> getDataTypetitle() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("请选择--");
        strings.add("径赛");
        strings.add("田赛");
        return strings;
    }
///修改公告图片
//    http://chuantu.biz/
//    上面是上传图片地址,想要的图片上传了,把地址添加进去集合
    public static List<String> getDataNotice() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("http://chuantu.xyz/t6/733/1589182114x3661913030.jpg");
//        strings.add("http://chuantu.xyz/t6/733/1589182164x3703728804.jpg");
//        strings.add("http://chuantu.xyz/t6/733/1589182204x3703728804.jpg");
//        strings.add("http://chuantu.xyz/t6/733/1589182227x3661913030.jpg");
        return strings;
    }

    public static List<String> getVerificationCode () {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("00581ys");
        strings.add("00111");

        return strings;
    }

}
