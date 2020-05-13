package com.tanrice.studentregistrationmanagementsystem.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanrice.studentregistrationmanagementsystem.BaseDialog.CommonTipDialog;
import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProject;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProjectDB;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
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
        }
        mRlvAdministratorAdapter.setOnClickListener(new RlvAdministratorAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
                Intent intent = new Intent(AdministratorActivity.this, AdministratorDetailsActivity.class);
                intent.putExtra("studentNumber", String.valueOf(mRlvAdministratorAdapter.mList.get(i).getStudentNumber()));
                intent.putExtra("json", mRlvAdministratorAdapter.mList.get(i).getJson());

            }
        });
        mRlvAdministratorAdapter.setOnClickListener(new RlvAdministratorAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
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
