package com.tanrice.studentregistrationmanagementsystem.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanrice.studentregistrationmanagementsystem.NoticeAdapter;
import com.tanrice.studentregistrationmanagementsystem.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class NoticeActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rlv_list)
    RecyclerView mRlvList;
    private ArrayList<ArrayList<String>> mArrayLists;


//    //    http://chuantu.xyz/t6/733/1589182114x3661913030.jpg
////    http://chuantu.xyz/t6/733/1589182164x3703728804.jpg
////    http://chuantu.xyz/t6/733/1589182204x3703728804.jpg
////    http://chuantu.xyz/t6/733/1589182227x3661913030.jpg
//
//
////        ArrayList<LocalMedia> selectList = new ArrayList<>();
////        selectList.add(new LocalMedia("http://chuantu.xyz/t6/733/1589182114x3661913030.jpg",0,));
////        PictureSelector.create(this)
////                .themeStyle(R.style.picture_default_style)
////                .isNotPreviewDownload(true)
////                .openExternalPreview(1, selectList);
//        Glide.with(this).load("http://chuantu.xyz/t6/733/1589182114x3661913030.jpg").into(mPhoneview);
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public void initView() {
        mArrayLists = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("http://chuantu.xyz/t6/733/1589182114x3661913030.jpg");
        strings.add("http://chuantu.xyz/t6/733/1589182164x3703728804.jpg");
        strings.add("http://chuantu.xyz/t6/733/1589182204x3703728804.jpg");
        strings.add("http://chuantu.xyz/t6/733/1589182227x3661913030.jpg");
        mArrayLists.add(strings);
        mTvTitle.setText("公告");
        mRlvList.setLayoutManager(new LinearLayoutManager(this));
        NoticeAdapter noticeAdapter = new NoticeAdapter(mArrayLists);
        mRlvList.setAdapter(noticeAdapter);
        noticeAdapter.setOnClickListener(new NoticeAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
                Intent intent = new Intent(NoticeActivity.this, ScanMediaActivity.class);
                intent.putStringArrayListExtra("list",mArrayLists.get(0));
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.img_back)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
}
