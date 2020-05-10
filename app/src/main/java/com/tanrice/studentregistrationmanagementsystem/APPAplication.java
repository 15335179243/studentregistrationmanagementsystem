package com.tanrice.studentregistrationmanagementsystem;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;


import com.tanrice.studentregistrationmanagementsystem.dao.DaoMaster;
import com.tanrice.studentregistrationmanagementsystem.dao.DaoSession;

import java.util.UUID;

public class APPAplication extends Application {
    public static String token = "";
    public static String UserId = "";
    public static UUID mUuid;
    public static String mIMEI;
    public static String mDID;
    public static int width = 0;
    public static int height = 0;
    public static int densityDpi = 0;
    public static float density = 0;
    public static int sw = 0;
    public Context mContext;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        mContext = this;

        WindowManager manager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        //以要素为单位
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        densityDpi = metrics.densityDpi;
        density = metrics.density;
        Log.e("chumu", "width: " + width + "  height: " + height + "dp" + density);

    }


}
