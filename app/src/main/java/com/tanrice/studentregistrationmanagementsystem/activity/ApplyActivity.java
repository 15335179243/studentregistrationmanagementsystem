package com.tanrice.studentregistrationmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chumu.dt.v24.permissions.ChuMuSharedPreferences;
import com.google.gson.Gson;
import com.tanrice.studentregistrationmanagementsystem.BaseDialog.CommonTipDialog;
import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanList;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanProjectSelect;
import com.tanrice.studentregistrationmanagementsystem.basedata.User;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProject;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProjectDB;
import com.tanrice.studentregistrationmanagementsystem.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ApplyActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.submit)
    Button mSubmit;
    @BindView(R.id.constraintLayout2)
    ConstraintLayout mConstraintLayout2;
    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.time_set)
    ConstraintLayout mTimeSet;
    private ApplyRlvAdapter mRlvAdapter;
    private ArrayList<ArrayList<BeanList>> mList;
    private ArrayList<BeanList> mData;
    private long studentNumber;
    private User bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply;
    }

    @Override
    public void initView() {
        String value = (String) new ChuMuSharedPreferences(this, "time").getValue("times", "");
        if (!value.isEmpty()) {
            try {
                Long data = DateUtils.getStringToData(value);
                long currentTimeMillis = DateUtils.getCurrentTimeMillis();
                int i = DateUtils.compareDate(new Date(data), new Date(currentTimeMillis));
                if (i == -1) {
                    mTimeSet.setVisibility(View.VISIBLE);
                    mConstraintLayout2.setVisibility(View.GONE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        bean = mApplication.userBean;
        studentNumber = Long.valueOf(bean.getStudentNumber());
        steStatusBar(true);
        mImgBack.setVisibility(View.GONE);
        mTvTitle.setText("报名");
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mData = (ArrayList<BeanList>) BeanList.getData();
        mList.add(mData);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRlvAdapter = new ApplyRlvAdapter();
        mRlv.setAdapter(mRlvAdapter);
        mRlvAdapter.setType(1);
        if (mApplication.userBean.getStudent()) {
            mRlvAdapter.setIsStudent(1);
        } else {
            mRlvAdapter.setIsStudent(2);
        }
        mRlvAdapter.setnewList(mList);
        mRlvAdapter.setOnClickListener(new ApplyRlvAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
                showDialog("取消", "确定", "一但确定就必须得报名!");
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
                        mList.add(mData);
                        mRlvAdapter.setnewList(mList);
                        mRlvAdapter.setType(mRlvAdapter.getType() + 1);
                    }
                });
    }

    @OnClick(R.id.submit)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.submit:
                int i = 0;
                for (BeanProjectSelect.ProjectSelect projectSelect : mRlvAdapter.mData1.getList()) {
                    i++;

                    if (projectSelect.getUserConetent() == 0 || projectSelect.getUserPrject() == 0) {
                        showToast("请选报名择项目");
                        return;
                    }
                    if (mRlvAdapter.getType() == i) break;
                }
                showDialogSubmit("取消", "确定", "确定报名?", i);
                break;
        }
    }

    private void showDialogSubmit(String left, String right, String centr, int i) {
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
                        BeanProjectSelect data1 = new BeanProjectSelect();
                        ArrayList<BeanProjectSelect.ProjectSelect> list = new ArrayList<>();
                        for (int j = 0; j < i; j++) {

                            list.add(mRlvAdapter.mData1.getList().get(j));
                        }
                        data1.setList(list);
                        String s = new Gson().toJson(data1);
//                        Log.e("chumu", "没插入之前: " + new UserProject((long) 16111111, s).toString());
                        UserProjectDB.insert(new UserProject(studentNumber, bean.getDepartment(), bean.getStudent(), bean.getTeacher(), bean.getName(), bean.getUserName(), bean.getGender(), s));
//                        UserProject userProject = UserProjectDB.queryItem(new UserProject((long) 16111111, s));
//                        Log.e("chumu", "没插入之后: " + userProject.toString());
                        Intent intent = new Intent(ApplyActivity.this, ApplyLIstActivity.class);
                        intent.putExtra("studentNumber", studentNumber);
                        startActivity(intent);
                        finish();

                    }
                });
    }


}
