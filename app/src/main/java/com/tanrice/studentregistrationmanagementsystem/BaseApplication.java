package com.tanrice.studentregistrationmanagementsystem;


import android.content.Context;


import com.tanrice.studentregistrationmanagementsystem.basedata.User;
import com.tanrice.studentregistrationmanagementsystem.dao.DaoMaster;
import com.tanrice.studentregistrationmanagementsystem.dao.DaoSession;
import com.tanrice.studentregistrationmanagementsystem.logcollector.LogCollector;
import com.tanrice.studentregistrationmanagementsystem.logcollector.capture.CrashHandler;
import com.tanrice.studentregistrationmanagementsystem.logcollector.upload.HttpParameters;
import com.tanrice.studentregistrationmanagementsystem.pic.DisplayU;

import androidx.multidex.MultiDex;


public class BaseApplication extends APPAplication {
    public static BaseApplication sApplication;
    private static DaoSession mDaoSession;
    public User userBean;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        DisplayU.init(base);

        CrashHandler instance = CrashHandler.getInstance(this);
        instance.init();
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
