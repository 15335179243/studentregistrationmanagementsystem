package com.tanrice.studentregistrationmanagementsystem.activity;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tanrice.studentregistrationmanagementsystem.BaseApplication;
import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.pic.DisplayU;
import com.tanrice.studentregistrationmanagementsystem.pic.PreviewViewPager;
import com.tanrice.studentregistrationmanagementsystem.pic.ScanMediaPagerAdapter;

import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ScanMediaActivity extends BaseActivity {
    @BindView(R.id.activity_scan_media_pager)
    PreviewViewPager mMediaPager;
    @BindView(R.id.activity_scan_media_title_tv)
    TextView mTitleTv;
    @BindView(R.id.activity_scan_media_title_rl)
    View mTitleRl;
    private ArrayList<String> mStrings;


    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_media;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        translucentFit(mTitleRl);

    }
    @Override
    public void initData() {
        mStrings = getIntent().getStringArrayListExtra("list");
        showPositionIndicator(0);
        mMediaPager.addOnPageChangeListener(mOnPageChangeListener);
        mMediaPager.setAdapter(new ScanMediaPagerAdapter(mStrings, new ScanMediaPagerAdapter.OnScanAdapterListener() {
            @Override
            public void onLongClick(String item) {

            }
        }));


        mMediaPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mMediaPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void showPositionIndicator(int position) {
        mTitleTv.setText(getString(R.string.divider_int, position + 1, mStrings.size()));
    }

    static void translucentFit(View view){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        DisplayU.init(BaseApplication.getAppContext());
        int topMargin = DisplayU.getStatusBarHeight();
        if(layoutParams instanceof RelativeLayout.LayoutParams){
            ((RelativeLayout.LayoutParams)layoutParams).topMargin = topMargin;
        }else if(layoutParams instanceof FrameLayout.LayoutParams){
            ((FrameLayout.LayoutParams)layoutParams).topMargin = topMargin;
        }else if(layoutParams instanceof LinearLayout.LayoutParams){
            ((LinearLayout.LayoutParams)layoutParams).topMargin = topMargin;
        }
        view.setLayoutParams(layoutParams);
    }
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        @Override
        public void onPageSelected(int position) {
            showPositionIndicator(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
