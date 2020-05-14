package com.tanrice.studentregistrationmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chumu.dt.v24.permissions.ChuMuSharedPreferences;
import com.google.gson.Gson;
import com.tanrice.studentregistrationmanagementsystem.BaseDialog.CommonTipDialog;
import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanProjectSelect;
import com.tanrice.studentregistrationmanagementsystem.basedata.User;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProject;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProjectDB;
import com.tanrice.studentregistrationmanagementsystem.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;

import androidx.constraintlayout.widget.ConstraintLayout;
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
    @BindView(R.id.include2)
    ConstraintLayout mInclude2;
    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.time_set)
    ConstraintLayout mTimeSet;
    private Long mStudentNumber;

    private ApplyListRlvAdapter mRlvAdapter;
    private User bean;


    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_list;
    }

    @Override
    public void initView() {
        String value = (String) new ChuMuSharedPreferences(this, "time").getValue("times", "");
        if (!value .isEmpty()) {
            try {
                Long data = DateUtils.getStringToData(value);

                long currentTimeMillis = DateUtils.getCurrentTimeMillis();
                int i = DateUtils.compareDate(new Date(data), new Date(currentTimeMillis));
                if (i == -1) {
                    mTimeSet.setVisibility(View.VISIBLE);
                    mRlv.setVisibility(View.GONE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        mImgBack.setVisibility(View.GONE);
        steStatusBar(true);
        mTvTitle.setText("已报名项目");
        mStudentNumber = getIntent().getLongExtra("studentNumber", 0);
        bean = mApplication.userBean;

    }

    @Override
    public void initData() {
        UserProject userProject = UserProjectDB.queryItem(new UserProject(mStudentNumber, bean.getDepartment(), bean.getStudent(), bean.getTeacher(), bean.getName(), bean.getUserName(), bean.getGender(), ""));
        BeanProjectSelect beanProjectSelect = new Gson().fromJson(userProject.getJson(), BeanProjectSelect.class);
        beanProjectSelect.getList();
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRlvAdapter = new ApplyListRlvAdapter(mStudentNumber.toString());
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
                startActivity(new Intent(ApplyLIstActivity.this, NoticeActivity.class));
                break;
        }
    }

}
