package com.tanrice.studentregistrationmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tanrice.studentregistrationmanagementsystem.BaseDialog.CommonTipDialog;
import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProject;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProjectDB;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    }

    @Override
    public void initData() {
        List<UserProject> userProjects = UserProjectDB.queryAll();
        if (userProjects != null && userProjects.size() > 0) {
            mRlvAdministratorAdapter.setNewlist(userProjects);
            Log.e("chumu", "initData: "+  userProjects.toString() );

        }else {
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
                break;
            case R.id.img_back:
                finish();
                break;
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
