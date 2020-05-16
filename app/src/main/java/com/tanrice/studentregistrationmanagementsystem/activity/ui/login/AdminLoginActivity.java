package com.tanrice.studentregistrationmanagementsystem.activity.ui.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.activity.AdministratorActivity;
import com.tanrice.studentregistrationmanagementsystem.activity.BaseActivity;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanList;


public class AdminLoginActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mImgBack;
    /**
     * 报名管理系统
     */
    private TextView mTvTitle;
    /**
     * 输入管理员账号
     */
    private EditText mUsername;
    /**
     * 输入管理员密码
     */
    private EditText mPassword;
    /**
     * 登陆
     */
    private Button mLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_admin_login;
    }

    @Override
    public void initView() {

        mImgBack = (ImageView) findViewById(R.id.img_back);
        mImgBack.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mTvTitle.setText("管理员登陆");
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.login:
                if (mUsername.getText().toString().trim().isEmpty() || !mUsername.getText().toString().trim().equals(BeanList.getAdminUserName())) {
                    showToast("账号输入错误");
                    return;
                }
                if (mPassword.getText().toString().trim().isEmpty() || !mPassword.getText().toString().trim().equals(BeanList.getAdminPassword())) {
                    showToast("账号输入错误");
                    return;
                }
                showToast("登陆成功");
                Intent intent = new Intent(AdminLoginActivity.this, AdministratorActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
