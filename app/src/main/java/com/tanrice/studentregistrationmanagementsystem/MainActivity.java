package com.tanrice.studentregistrationmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.tanrice.studentregistrationmanagementsystem.dao.BeanSelected;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.school)
    TextView mSchool;
    @BindView(R.id.spinner_school)
    Spinner mSpinnerDepartment;
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
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.spinner_department)
    Spinner mSpinnerSchool;
    @BindView(R.id.register)
    TextView mRegister;
    private List<BeanList> mData;
    private BeanSelected mBeanSelected;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        steStatusBar(true);
        mImgBack.setVisibility(View.GONE);
        mData = BeanList.getData();
        mBeanSelected = new BeanSelected();
    }

    @Override
    public void initData() {
        List<String> department = mData.get(0).getDepartment();
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, department);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerSchool.setAdapter(departmentAdapter);
        ArrayAdapter<String> mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"请选择--", mData.get(0).getSchool()});
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerDepartment.setAdapter(mSpinnerAdapter);
        mSpinnerSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mBeanSelected.setSchool(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mSpinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mBeanSelected.setDepartment(adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    default:
                        break;
                    case R.id.rd_student:
                        mBeanSelected.setStudent(true);
                        break;

                    case R.id.rd_teacher:
                        mBeanSelected.setTeacher(true);
                        break;
                }
            }
        });
    }


    @OnClick({R.id.img_back, R.id.login, R.id.register})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_back:
                break;
            case R.id.login:
                showToast("登陆");
                break;
            case R.id.register:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
