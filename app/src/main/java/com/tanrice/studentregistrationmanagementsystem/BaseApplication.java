package com.tanrice.studentregistrationmanagementsystem;


import android.content.Context;
import android.util.Log;


import com.tanrice.studentregistrationmanagementsystem.dao.DaoMaster;
import com.tanrice.studentregistrationmanagementsystem.dao.DaoSession;

import androidx.multidex.MultiDex;


public class BaseApplication extends APPAplication {
    public static BaseApplication sApplication;
    private static DaoSession mDaoSession;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        init();
    }

    public static Context getAppContext() {
        return sApplication.getApplicationContext();
    }

    public static BaseApplication getApplication() {
        return sApplication;
    }

    private void init() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "student.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = daoMaster.newSession();

    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

}
