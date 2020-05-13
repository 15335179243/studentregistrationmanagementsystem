package com.tanrice.studentregistrationmanagementsystem.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.UserProject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvAdministratorAdapter extends RecyclerView.Adapter {


    public List<UserProject> mList;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_administrator, viewGroup, false);
        return new ViewHolder(inflate);

    }

    //    账号 院系 学号 姓名 身份
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.mAccountNumberContent.setText(mList.get(i).getUserName());
        holder.mDepartmentContent.setText(mList.get(i).getDepartment());
        holder.mStudentNumberContent.setText(mList.get(i).getStudentNumber().toString());
        holder.mNameContent.setText(mList.get(i).getName());
        if (mList.get(i).getStudent()) {
            holder.mIdentityContent.setText("学生");
        }
        if (mList.get(i).getTeacher()) {
            holder.mIdentityContent.setText("教师");
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongClickListener!=null){
                    mOnLongClickListener.onLongClick(v,i);
                }
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener!=null){
                    mOnClickListener.onClick(v,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setNewlist(List<UserProject> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener OnClickListener) {
        mOnClickListener = OnClickListener;
    }

    public interface OnClickListener {
        void onClick(View v, int i);
    }

    public void setOnLongClickListener(OnLongClickListener OnLongClickListener) {
        mOnLongClickListener = OnLongClickListener;
    }

    public interface OnLongClickListener {
        void onLongClick(View v, int i);
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.identity_content)
        TextView mIdentityContent;
        @BindView(R.id.identity)
        TextView mIdentity;
        @BindView(R.id.department_content)
        TextView mDepartmentContent;
        @BindView(R.id.department)
        TextView mDepartment;
        @BindView(R.id.account_number)
        TextView mAccountNumber;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.student_number)
        TextView mStudentNumber;
        @BindView(R.id.account_number_content)
        TextView mAccountNumberContent;
        @BindView(R.id.name_content)
        TextView mNameContent;
        @BindView(R.id.student_number_content)
        TextView mStudentNumberContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
