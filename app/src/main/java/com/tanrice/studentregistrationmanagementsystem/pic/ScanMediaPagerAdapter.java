package com.tanrice.studentregistrationmanagementsystem.pic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.tanrice.studentregistrationmanagementsystem.R;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

public class ScanMediaPagerAdapter  extends PagerAdapter {

    private List<String> images;
    private OnScanAdapterListener mListener;

    public ScanMediaPagerAdapter(List<String> images, OnScanAdapterListener listener) {
        super();
        this.images = images;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        if (images != null) {
            return images.size();
        }
        return 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View contentView = LayoutInflater.from(container.getContext())
                .inflate(R.layout.tem_media_scan, container, false);
        String item = images.get(position);
        final PhotoView imageView = contentView.findViewById(R.id.image_photo_view);
        RelativeLayout playContainer = contentView.findViewById(R.id.item_media_scan_play_container_rl);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mListener!=null)
                    mListener.onLongClick(item);
                return true;
            }
        });
            playContainer.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            Glide.with(container.getContext()).load(item).into(imageView);
        (container).addView(contentView, 0);
        return contentView;
    }

    public interface OnScanAdapterListener{
        void onLongClick(String item);
    }
}
