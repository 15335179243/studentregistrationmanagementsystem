package com.tanrice.studentregistrationmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class NoticeAdapter extends RecyclerView.Adapter {
    private ArrayList<ArrayList<String>> mStrings;
    private OnClickListener mOnClickListener;

    public NoticeAdapter(ArrayList<ArrayList<String>> strings) {
        mStrings = strings;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notice, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.mRecyclerView.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 2));

        RlvNoticeAdapter rlvNoticeAdapter = new RlvNoticeAdapter(mStrings.get(i));
        holder.mRecyclerView.setAdapter(rlvNoticeAdapter);
        if (mOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onClick(v, i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    public void setOnClickListener(OnClickListener OnClickListener) {
        mOnClickListener = OnClickListener;
    }

    public interface OnClickListener {
        void onClick(View v, int i);
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_text)
        TextView mTvText;
        @BindView(R.id.rlv_list)
        RecyclerView mRecyclerView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
