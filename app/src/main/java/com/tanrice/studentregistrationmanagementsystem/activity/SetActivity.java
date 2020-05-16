package com.tanrice.studentregistrationmanagementsystem.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chumu.dt.v24.permissions.ChuMuSharedPreferences;
import com.tanrice.studentregistrationmanagementsystem.BaseDialog.CommonTipDialog;
import com.tanrice.studentregistrationmanagementsystem.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {
    @Nullable
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.set)
    TextView mSet;
    @BindView(R.id.set_content)
    EditText mSetContent;
    @BindView(R.id.set_btn)
    Button mSetBtn;
    private String mSetTimeContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.img_back, R.id.set_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.set_btn:
                mSetTimeContent = mSetContent.getText().toString().trim();
                if (mSetTimeContent.isEmpty()) {
                    showToast("请输入时间");
                    return;
                }
                String rate = "([2][0][2][0][0][0-9][0-2][0-9])|([2][0][2][0][1][0-2][0-3][0-1])|([2][0][2][0][0][0-9][0-3][0-1])|([2][0][2][0][1][0-2][0-2][0-9])";
                if (!mSetTimeContent.matches(rate)) {
                    showToast("时间格式不正确,");
                    return;
                }
                showDialog("否", "是", "是否保存修改?");

                break;
        }
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
                        ChuMuSharedPreferences time = new ChuMuSharedPreferences(SetActivity.this, "time");
                        time.putValue("times", mSetTimeContent);

                        finish();
                    }
                });
    }
}
