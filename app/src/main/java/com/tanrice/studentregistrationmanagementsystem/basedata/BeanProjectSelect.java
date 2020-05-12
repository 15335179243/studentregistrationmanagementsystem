package com.tanrice.studentregistrationmanagementsystem.basedata;

import java.util.ArrayList;
import java.util.List;

public class BeanProjectSelect {
    private ArrayList<ProjectSelect> list;

    public BeanProjectSelect() {
    }

    public BeanProjectSelect(ArrayList<ProjectSelect> list) {
        this.list = list;
    }

    public  static class   ProjectSelect{
       private int userPrject;
       private int userConetent;
       private String userPrjectstr;
       private String userConetentstr;

        public ProjectSelect(int userPrject, int userConetent) {
            this.userPrject = userPrject;
            this.userConetent = userConetent;
        }

        public int getUserPrject() {
            return userPrject;
        }

        public void setUserPrject(int userPrject) {
            this.userPrject = userPrject;
        }

        public int getUserConetent() {
            return userConetent;
        }

        public void setUserConetent(int userConetent) {
            this.userConetent = userConetent;
        }

        public String getUserPrjectstr() {
            return userPrjectstr;
        }

        public void setUserPrjectstr(String userPrjectstr) {
            this.userPrjectstr = userPrjectstr;
        }

        public String getUserConetentstr() {
            return userConetentstr;
        }

        public void setUserConetentstr(String userConetentstr) {
            this.userConetentstr = userConetentstr;
        }

        @Override
        public String toString() {
            return "ProjectSelect{" +
                    "userPrject=" + userPrject +
                    ", userConetent=" + userConetent +
                    ", userPrjectstr='" + userPrjectstr + '\'' +
                    ", userConetentstr='" + userConetentstr + '\'' +
                    '}';
        }
    }

    public List<ProjectSelect> getList() {
        return list;
    }

    public void setList(ArrayList<ProjectSelect> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "BeanProjectSelect{" +
                "list=" + list +
                '}';
    }
    public static BeanProjectSelect getData(){
        ProjectSelect projectSelect1 = new ProjectSelect(0, 0);
        ProjectSelect projectSelect2 = new ProjectSelect(0, 0);
        ProjectSelect projectSelect3 = new ProjectSelect(0, 0);
       ArrayList<ProjectSelect> projectSelects = new ArrayList<>();
        projectSelects.add(projectSelect1);
        projectSelects.add(projectSelect2);
        projectSelects.add(projectSelect3);
        BeanProjectSelect beanProjectSelect = new BeanProjectSelect(projectSelects);
        return beanProjectSelect;
    }
}
