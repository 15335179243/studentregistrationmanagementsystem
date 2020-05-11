package com.tanrice.studentregistrationmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

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
        Glide.with(holder.itemView.getContext()).load(mStrings.get(0).get(i)).into(holder.mImageView);
        Glide.with(holder.itemView.getContext()).load(mStrings.get(0).get(i)).into(holder.mImageView2);
        Glide.with(holder.itemView.getContext()).load(mStrings.get(0).get(i)).into(holder.mImageView3);
        Glide.with(holder.itemView.getContext()).load(mStrings.get(0).get(i)).into(holder.mImageView4);
        if (mOnClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onClick(v,i);
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
        @BindView(R.id.imageView3)
        ImageView mImageView3;
        @BindView(R.id.imageView4)
        ImageView mImageView4;
        @BindView(R.id.imageView2)
        ImageView mImageView2;
        @BindView(R.id.imageView)
        ImageView mImageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
