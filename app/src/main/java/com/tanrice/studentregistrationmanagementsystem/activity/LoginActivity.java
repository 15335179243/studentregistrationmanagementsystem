package com.tanrice.studentregistrationmanagementsystem.activity;

import android.content.Intent;
import android.text.TextUtils;
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

import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanList;
import com.tanrice.studentregistrationmanagementsystem.basedata.SQLHelper;
import com.tanrice.studentregistrationmanagementsystem.basedata.User;
import com.tanrice.studentregistrationmanagementsystem.dao.BeanSelected;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.school)
    TextView mSchool;
    @BindView(R.id.spinner_department)
    Spinner mSpinnerDepartment;
    @BindView(R.id.department)
    TextView mDepartment;
    @BindView(R.id.verification_code)
    EditText mVerificationCode;
    @BindView(R.id.account_number)
    TextView mAccountNumber;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.student_number)
    TextView mStudentNumber;
    @BindView(R.id.account_number_content)
    EditText mAccountNumberContent;
    @BindView(R.id.name_content)
    EditText mNameContent;
    @BindView(R.id.pwd)
    TextView mPwd;
    @BindView(R.id.student_number_content)
    EditText mStudentNumberContent;
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
    @BindView(R.id.spinner_school)
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
        mSpinnerDepartment.setAdapter(departmentAdapter);

        ArrayAdapter<String> mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"请选择--", mData.get(0).getSchool()});
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerSchool.setAdapter(mSpinnerAdapter);
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
                        mBeanSelected.setTeacher(false);
                        break;

                    case R.id.rd_teacher:
                        mBeanSelected.setTeacher(true);
                        mBeanSelected.setStudent(false);
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
                mBeanSelected.setName(mNameContent.getText().toString().trim());
                mBeanSelected.setAccountNumber(mAccountNumberContent.getText().toString().trim());
                mBeanSelected.setPassword(mPwdContent.getText().toString().trim());
                mBeanSelected.setVerificationCode(mVerificationCode.getText().toString().trim());
                mBeanSelected.setStudentNumber(mStudentNumberContent.getText().toString().trim());
                if (TextUtils.isEmpty(mBeanSelected.getSchool())) {
                    showToast("请选择学校");
                    return;
                }
                if (TextUtils.isEmpty(mBeanSelected.getDepartment())) {
                    showToast("请选择院系");
                    return;
                }
                if (TextUtils.isEmpty(mBeanSelected.getAccountNumber())) {
                    showToast("请输入账号");
                    return;
                }
                if (TextUtils.isEmpty(mBeanSelected.getName())) {
                    showToast("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(mBeanSelected.getVerificationCode())) {
                    showToast("请输入验证码");
                    return;
                } else {
                    if (!mBeanSelected.getVerificationCode().equals("00111")) {
                        showToast("验证码不正确,请联系校方获取验证码");
                        return;
                    }

                }
                if (TextUtils.isEmpty(mBeanSelected.getStudentNumber())) {
                    showToast("请输入学号");
                    return;
                } else {
                    String rate = "([1][6-9][0-9]{6})|([2][0][0-9]{6})";
                    if (!mBeanSelected.getStudentNumber().matches(rate)) {
                        showToast("请正确输入学号");
                        return;
                    }

                }
                if (TextUtils.isEmpty(mBeanSelected.getPassword())) {
                    showToast("请输入密码");
                    return;
                }
                if (!mBeanSelected.isStudent() && !mBeanSelected.isTeacher()) {
                    showToast("请选择是学生还是教师");
                    return;
                }
                User user = SQLHelper.queryItem(new User(null, mBeanSelected.getAccountNumber(), mBeanSelected.getPassword(), 0, "", mBeanSelected.getName(), mBeanSelected.getSchool(),
                        mBeanSelected.getStudentNumber(), mBeanSelected.getDepartment(), mBeanSelected.isStudent(), mBeanSelected.isTeacher(), 0));


                if (user != null && !mBeanSelected.getSchool().equals(user.getSchool()) ||
                        !mBeanSelected.getStudentNumber().equals(user.getStudentNumber()) ||
                        !mBeanSelected.getAccountNumber().equals(user.getUserName()) ||
                        !mBeanSelected.getDepartment().equals(user.getDepartment()) ||
                        !mBeanSelected.getName().equals(user.getName())
                ) {
                    showToast("请正确填写信息");
                    return;
                }
                if (!mBeanSelected.getPassword().equals(user.getUserPwd())) {
                    showToast("密码错误");
                }
                if (!mBeanSelected.isTeacher().toString().equals(user.getTeacher().toString()) || !mBeanSelected.isStudent().toString().equals(user.getStudent().toString())) {
                    showToast("请正确选老师或学生");
                    return;
                }
                showToast("登陆成功");
                Intent intent1 = new Intent(LoginActivity.this, ApplyActivity.class);
                startActivity(intent1);
                finish();

                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }


}
