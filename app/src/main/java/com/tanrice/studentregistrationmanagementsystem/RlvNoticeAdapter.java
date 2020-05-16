package com.tanrice.studentregistrationmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvNoticeAdapter extends RecyclerView.Adapter {
    private ArrayList<String> mStrings;

    public RlvNoticeAdapter(ArrayList<String> strings) {
        mStrings = strings;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notice_rlv_pic, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
               ViewHolder holder = (ViewHolder) viewHolder;
        Glide.with(viewHolder.itemView.getContext()).load(mStrings.get(i)).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView mImageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
