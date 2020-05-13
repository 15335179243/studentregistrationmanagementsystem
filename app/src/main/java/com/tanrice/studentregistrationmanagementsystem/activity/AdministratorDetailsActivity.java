package com.tanrice.studentregistrationmanagementsystem.activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanProjectSelect;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class AdministratorDetailsActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private String mJson;
    private String mStudentNumber;
    private ApplyListRlvAdapter mRlvAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_administrator_details;
    }

    @Override
    public void initView() {
        mJson = getIntent().getStringExtra("json");
        mStudentNumber = getIntent().getStringExtra("studentNumber");
    }

    @Override
    public void initData() {
        BeanProjectSelect beanProjectSelect = null;
        if (mJson!=null) {
            beanProjectSelect = new Gson().fromJson(mJson, BeanProjectSelect.class);
        }
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRlvAdapter = new ApplyListRlvAdapter(1,mStudentNumber);
        mRlv.setAdapter(mRlvAdapter);
        mRlvAdapter.setnewList(beanProjectSelect.getList());
    }


    @OnClick({R.id.img_back})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_back:
                finish();
             break;
        }
    }
}
