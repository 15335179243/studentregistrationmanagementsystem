package com.tanrice.studentregistrationmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tanrice.studentregistrationmanagementsystem.BaseDialog.CommonTipDialog;
import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanProjectSelect;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProject;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProjectDB;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyLIstActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.track)
    TextView mTrack;
    private Long mStudentNumber;

    private ApplyListRlvAdapter mRlvAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_list;
    }

    @Override
    public void initView() {
        mImgBack.setVisibility(View.GONE);
        steStatusBar(true);
        mTvTitle.setText("已报名项目");
        mStudentNumber = getIntent().getLongExtra("studentNumber", 0);


    }

    @Override
    public void initData() {
        UserProject userProject = UserProjectDB.queryItem(new UserProject(mStudentNumber, ""));
        BeanProjectSelect beanProjectSelect = new Gson().fromJson(userProject.getJson(), BeanProjectSelect.class);
        beanProjectSelect.getList();
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRlvAdapter = new ApplyListRlvAdapter(mStudentNumber);
        mRlv.setAdapter(mRlvAdapter);
        mRlvAdapter.setnewList(beanProjectSelect.getList());
        mRlvAdapter.setOnClickListener(new ApplyListRlvAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
                showToast("长按可以取消报名");
            }
        });
        mRlvAdapter.setOnLongClickListener(new ApplyListRlvAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(View v, int i) {
                showDialog("取消", "确认", "确定取消报名");
            }
        });


    }

    private void showDialog(String left, String right, String centr) {
        CommonTipDialog.showTip(getSupportFragmentManager(),
                new CommonTipDialog.Option()
                        .reversePositiveNegative()
                        .setPositiveText(left)
                        .setPositiveTextColor(getResources().getColor(R.color.color_333333))
                        .setNegativeText(right)
                        .setNegativeTextColor(getResources().getColor(R.color.color_3AA2E1))
                        .setContent(centr),
                new CommonTipDialog.OnTipDialogListener() {
                    @Override
                    public void onNegativeClick() {
                        super.onNegativeClick();
                        Intent intent = new Intent(ApplyLIstActivity.this, ApplyActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
    }


    @OnClick(R.id.track)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.track:
                startActivity(new Intent(ApplyLIstActivity.this,NoticeActivity.class));
                break;
        }
    }
}
