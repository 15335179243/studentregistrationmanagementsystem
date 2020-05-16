package com.tanrice.studentregistrationmanagementsystem.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chumu.dt.v24.permissions.DynamicPermissions;
import com.google.gson.Gson;
import com.tanrice.studentregistrationmanagementsystem.BaseDialog.CommonTipDialog;
import com.tanrice.studentregistrationmanagementsystem.ExcelUtils;
import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanList;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanProjectSelect;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProject;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProjectDB;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AdministratorActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_set)
    TextView mTvSet;
    @BindView(R.id.rlv_list)
    RecyclerView mRlvList;
    @BindView(R.id.export)
    Button mExport;
    @BindView(R.id.constraintLayout)
    ConstraintLayout mConstraintLayout;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.time_set)
    ConstraintLayout mTimeSet;
    private RlvAdministratorAdapter mRlvAdministratorAdapter;
    private List<UserProject> mUserProjects;
    private DynamicPermissions mDynamicPermissions;

    @Override
    public int getLayoutId() {
        return R.layout.activity_administrator;
    }

    @Override
    public void initView() {
        mRlvList.setLayoutManager(new LinearLayoutManager(this));
        mRlvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRlvAdministratorAdapter = new RlvAdministratorAdapter();
        mRlvList.setAdapter(mRlvAdministratorAdapter);

        mDynamicPermissions = new DynamicPermissions(AdministratorActivity.this,new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE,READ_PHONE_STATE,ACCESS_WIFI_STATE});
        mDynamicPermissions.init();
    }

    @Override
    public void initData() {
        mUserProjects = UserProjectDB.queryAll();
        if (mUserProjects != null && mUserProjects.size() > 0) {
            mRlvAdministratorAdapter.setNewlist(mUserProjects);
            Log.e("chumu", "initData: " + mUserProjects.toString());

        } else {
            mExport.setVisibility(View.GONE);
            mRlvList.setVisibility(View.GONE);
            mTimeSet.setVisibility(View.VISIBLE);
            mTextView2.setText("糟糕!还没有人报名,快去通知报名吧!");
            return;
        }
        mRlvAdministratorAdapter.setOnClickListener(new RlvAdministratorAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
                Intent intent = new Intent(AdministratorActivity.this, AdministratorDetailsActivity.class);
                intent.putExtra("studentNumber", String.valueOf(mRlvAdministratorAdapter.mList.get(i).getStudentNumber()));
                intent.putExtra("json", mRlvAdministratorAdapter.mList.get(i).getJson());
                startActivity(intent);
            }
        });
        mRlvAdministratorAdapter.setOnLongClickListener(new RlvAdministratorAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(View v, int i) {
                showDialog("否", "是", "是否要删除此条报名信息", i);
            }
        });


    }


    @OnClick({R.id.tv_set, R.id.export, R.id.img_back})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_set:
                startActivity(new Intent(AdministratorActivity.this, SetActivity.class));
                break;
            case R.id.export:

                if (mDynamicPermissions.isFlag()) {
                    initStringData();
                }

                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

    //        list.add("院校");
//        list.add("院系");
//        list.add("学号");
//        list.add("账号");
//        list.add("姓名");
//        list.add("职务");
//        list.add("报名项目一");
//        list.add("报名项目二");
//        list.add("报名项目三");
    private void initStringData() {

        ArrayList<ArrayList<String>> mGroupList = new ArrayList<>();
        if (mUserProjects != null && mUserProjects.size() > 0) {
            for (UserProject userProject : mUserProjects) {
                ArrayList<String> list = new ArrayList<>();
                list.add("阳光学院");
                list.add(userProject.getDepartment());
                list.add(userProject.getStudentNumber().toString());
                list.add(userProject.getUserName());
                list.add(userProject.getName());
                if (userProject.getTeacher()) {
                    list.add("教师");
                }
                if (userProject.getStudent()) {
                    list.add("学生");
                }
                List<BeanProjectSelect.ProjectSelect> list1 = null;
                if (!userProject.getJson().isEmpty()) {
                    BeanProjectSelect beanProjectSelect = new Gson().fromJson(userProject.getJson(), BeanProjectSelect.class);
                    list1 = beanProjectSelect.getList();
                    if (list1 != null && list1.size() > 0) {
                        for (BeanProjectSelect.ProjectSelect projectSelect : list1) {
                            StringBuffer sb = new StringBuffer();
                            sb.append(projectSelect.getUserPrjectstr());
                            sb.append("----");
                            sb.append(projectSelect.getUserConetentstr());
                            list.add(sb.toString());
                        }
                    }
                }

                mGroupList.add(list);
            }

            ExcelUtils excelUtils = new ExcelUtils();
            excelUtils.exportExcel(BeanList.getExceTitle(),mGroupList,AdministratorActivity.this);
        } else {
            showToast("没有报名信息");
        }


    }

    private void showDialog(String left, String right, String centr, int i) {
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
                        mRlvAdministratorAdapter.mList.remove(i);
                        mRlvAdministratorAdapter.notifyItemRangeRemoved(i, mRlvAdministratorAdapter.getItemCount() - 1);
                        UserProjectDB.delete(mRlvAdministratorAdapter.mList.get(i));
                        showToast("已删除!");
                    }
                });
    }


}
