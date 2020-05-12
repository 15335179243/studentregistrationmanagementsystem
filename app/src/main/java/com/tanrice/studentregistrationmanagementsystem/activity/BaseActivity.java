package com.tanrice.studentregistrationmanagementsystem.activity;


import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tanrice.studentregistrationmanagementsystem.BaseApplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.scwang.smartrefresh.layout.util.DensityUtil.px2dp;


public abstract class BaseActivity extends AppCompatActivity {
    private LinearLayoutManager mManager;
    public BaseApplication mApplication;
    private Unbinder mBind;

    private



    final String WeiXin_PACKAGE_NAME = "com.tencent.mm";
    public ClipboardManager mClipboardManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (BaseApplication) getApplication();
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        initmvp();
        initView();
        initData();

    }


    public void initmvp() {

    }



    public void startWeiXin() {
        if (hasInstalledAlipayClient(this)) {
            Intent intent = new Intent();
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            startActivity(intent);

        } else {
            Snackbar.make(getWindow().getDecorView(), "木有检测到微信客户端 T T", Snackbar.LENGTH_SHORT).show();
        }

    }

    //判断本机是否安装了微信
    public boolean hasInstalledAlipayClient(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(

                    WeiXin_PACKAGE_NAME, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }




    protected void steStatusBar(boolean lightStatusBar) {
        View decor = getWindow().getDecorView();
        int ui = decor.getSystemUiVisibility();
        if (lightStatusBar) {
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        } else {
            ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        }
        decor.setSystemUiVisibility(ui);
    }

    public abstract int getLayoutId();


    public abstract void initView();

    public abstract void initData();


    public void showToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    public void initRecycleView(RecyclerView recyclerView, RefreshLayout refreshLayout) {
        mManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManager);
        if (refreshLayout != null) {
            refreshLayout.setHeaderHeight(px2dp(120));
            refreshLayout.setFooterHeight(px2dp(100));
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refresh();
                }
            });
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMore();
                }
            });
        }
    }



    protected int getLoadType(Object[] t) {
        return (int) t[1];
    }

    public void refresh() {

    }

    public void loadMore() {
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mClipboardManager = null;
        mBind.unbind();
    }
}
