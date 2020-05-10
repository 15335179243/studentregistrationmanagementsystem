package com.tanrice.studentregistrationmanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.school)
    TextView mSchool;
    @BindView(R.id.spinner_school)
    Spinner mSpinnerSchool;
    @BindView(R.id.department)
    TextView mDepartment;
    @BindView(R.id.verification_code)
    EditText mVerificationCode;
    @BindView(R.id.account_number)
    TextView mAccountNumber;
    @BindView(R.id.account_number_content)
    TextView mAccountNumberContent;
    @BindView(R.id.student_number)
    TextView mStudentNumber;
    @BindView(R.id.student_number_content)
    EditText mStudentNumberContent;
    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.pwd)
    TextView mPwd;
    @BindView(R.id.name_content)
    EditText mNameContent;
    @BindView(R.id.pwd_content)
    EditText mPwdContent;
    @BindView(R.id.rd_student)
    RadioButton mRdStudent;
    @BindView(R.id.rd_teacher)
    RadioButton mRdTeacher;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.spinner_department)
    Spinner mSpinnerDepartment;
    @BindView(R.id.login)
    TextView mLogin;

    @Override
    public int getLayoutId() {
        return (R.layout.activity_register);
    }

    @Override
    public void initView() {
        steStatusBar(true);
        mTvTitle.setText("注册");
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.register, R.id.login, R.id.img_back})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.register:
                break;
            case R.id.login:
                finish();
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }


}
