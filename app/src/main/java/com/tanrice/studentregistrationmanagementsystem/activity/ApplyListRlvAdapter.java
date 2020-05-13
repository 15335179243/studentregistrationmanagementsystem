package com.tanrice.studentregistrationmanagementsystem.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanProjectSelect;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyListRlvAdapter extends RecyclerView.Adapter {
    private String mStudentNumber;
    private List<BeanProjectSelect.ProjectSelect> mList;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;
    private int type = -1;

    public ApplyListRlvAdapter(int type, String studentNumber) {
        this.type = type;
        mStudentNumber = studentNumber;
    }


    public ApplyListRlvAdapter(String studentNumber) {

        mStudentNumber = studentNumber;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == 1) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_apply_list, viewGroup, false);
            return new ViewHolderOne(inflate);
        } else {
            View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_apply_item1, viewGroup, false);
            return new ViewHolder(inflate2);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onClick(v, i);
                    }
                }
            });
        } else {
            ViewHolderOne holder1 = (ViewHolderOne) viewHolder;
            holder1.mNoeSpinnerContent.setText(mList.get(i).getUserPrjectstr());
            holder1.mTvStudentNumberContent.setText(mStudentNumber.toString());
            holder1.mTowSpinnerContent.setText(mList.get(i).getUserConetentstr());
            holder1.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnLongClickListener != null) {
                        mOnLongClickListener.onLongClick(v, i);
                    }
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (type == 1) return mList.size();
        if (mList.size() == 3) {
            return mList.size();
        } else {
            return mList.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (type == 1) return 1;
        if (position == mList.size()) {
            return 2;
        } else {
            return 1;
        }


    }

    public void setnewList(List<BeanProjectSelect.ProjectSelect> list) {
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
    class ViewHolderOne extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_studentNumber_content)
        TextView mTvStudentNumberContent;
        @BindView(R.id.noeSpinner_content)
        TextView mNoeSpinnerContent;
        @BindView(R.id.towSpinner_content)
        TextView mTowSpinnerContent;

        ViewHolderOne(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView5)
        ImageView mImageView5;
        @BindView(R.id.textView)
        TextView mTextView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
