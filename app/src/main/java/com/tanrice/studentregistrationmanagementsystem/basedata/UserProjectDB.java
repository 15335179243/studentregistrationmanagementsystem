package com.tanrice.studentregistrationmanagementsystem.basedata;

import android.util.Log;

import com.tanrice.studentregistrationmanagementsystem.BaseApplication;
import com.tanrice.studentregistrationmanagementsystem.dao.DaoSession;
import com.tanrice.studentregistrationmanagementsystem.dao.UserProjectDao;

import java.util.List;

public class UserProjectDB {

    private static final String TAG = "DButils";

    private static DaoSession sDaoSession = BaseApplication.getDaoSession();

    public static UserProject queryItem(UserProject UeserProject) {
        return sDaoSession.queryBuilder(UserProject.class).where(UserProjectDao.Properties.StudentNumber.eq(UeserProject.getStudentNumber())).build().unique();
    }

    public static List<UserProject> queryAll() {
        return sDaoSession.loadAll(UserProject.class);
    }

    public static void insert(UserProject UeserProject) {

        if (queryItem(UeserProject) == null) {
            sDaoSession.insert(UeserProject);
        } else {
            delete(UeserProject);
            insert(UeserProject);

        }
    }

    public static void delete(UserProject UeserProject) {
        if (queryItem(UeserProject) != null) {
            sDaoSession.delete(UeserProject);
        } else {
            Log.d(TAG, "delete: 删除内容不存在");
        }
    }

    public static void update(UserProject UeserProject, UserProject newBean) {
        if (queryItem(UeserProject) != null) {
            sDaoSession.update(newBean);
        } else {
            Log.d(TAG, "update: 修改内容不存在");
        }
    }
}
