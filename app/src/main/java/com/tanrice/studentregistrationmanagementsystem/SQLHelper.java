package com.tanrice.studentregistrationmanagementsystem;

import android.util.Log;

import com.tanrice.studentregistrationmanagementsystem.dao.DaoSession;
import com.tanrice.studentregistrationmanagementsystem.dao.UserDao;

import java.util.List;

public class SQLHelper {
    private static final String TAG = "DButils";

    private static DaoSession sDaoSession = BaseApplication.getDaoSession();

    public static User queryItem(User User) {
        return sDaoSession.queryBuilder(User.class).where(UserDao.Properties.UserName.eq(User.getUserName())).build().unique();
    }

    public static List<User> queryAll() {
        return sDaoSession.loadAll(User.class);
    }

    public static void insert(User User) {
        if (queryItem(User) == null) {
            sDaoSession.insert(User);
        } else {
            Log.d(TAG, "insert: 已经添加");
        }
    }

    public static void delete(User User) {
        if (queryItem(User) != null) {
            sDaoSession.delete(User);
        } else {
            Log.d(TAG, "delete: 删除内容不存在");
        }
    }

    public static void update(User User, User newBean) {
        if (queryItem(User) != null) {
            sDaoSession.update(newBean);
        } else {
            Log.d(TAG, "update: 修改内容不存在");
        }
    }
}
