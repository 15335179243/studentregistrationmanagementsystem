package com.tanrice.studentregistrationmanagementsystem.activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.tanrice.studentregistrationmanagementsystem.R;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanList;
import com.tanrice.studentregistrationmanagementsystem.basedata.BeanProjectSelect;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyRlvAdapter extends RecyclerView.Adapter {
    //type等于几就代表可以报名几个项目
    private int type = 0;
    //是否是学生,1是学生,2是老师
    private int isStudent = 0;
    private OnClickListener mOnClickListener;
    public final BeanProjectSelect mData1;

    public int getIsStudent() {
        return isStudent;
    }

    public ApplyRlvAdapter() {
        mData1 = BeanProjectSelect.getData();
    }

    public void setIsStudent(int isStudent) {
        this.isStudent = isStudent;
    }

    private ArrayList<ArrayList<BeanList>> mData;

    @Override
    public int getItemViewType(int position) {

            if (position == mData.size()) {
                return 2;
            } else {
                return 1;
            }



    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == 1) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_apply, viewGroup, false);
            return new ViewHolderOne(inflate);
        } else {
            View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_apply_item1, viewGroup, false);
            return new ViewHolder(inflate2);
        }
//        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_apply_list, viewGroup, false);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int post) {

        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener!=null) {
                        mOnClickListener.onClick(v,post);
                    }
                }
            });

        } else {
            BeanProjectSelect.ProjectSelect projectSelect = mData1.getList().get(post);
            ViewHolderOne holder1 = (ViewHolderOne) viewHolder;
            if (post==0) {
                holder1.mTvTitle.setText("项目一");
            }
            if (post == 1) {
                holder1.mTvTitle.setText("项目二");
            }
            if (post == 2) {
                holder1.mTvTitle.setText("项目三");
            }

            ArrayAdapter<String> departmentAdapter = new ArrayAdapter<String>(viewHolder.itemView.getContext(), android.R.layout.simple_spinner_item, BeanList.getDataTypetitle());
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            holder1.mNoeSpinner.setAdapter(departmentAdapter);
            holder1.mNoeSpinner.setSelection(projectSelect.getUserPrject(),false);


            ArrayAdapter<String> TowAdapter = new ArrayAdapter<String>(viewHolder.itemView.getContext(), android.R.layout.simple_spinner_item);
            TowAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            holder1.mTowSpinner.setAdapter(TowAdapter);
            holder1.mNoeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    projectSelect.setUserPrjectstr(adapterView.getItemAtPosition(i).toString());
                    projectSelect.setUserPrject(i);
                    List<String> list = null;
                    if (adapterView.getItemAtPosition(i).toString().equals("请选择--")) {
                        list = new ArrayList<>();
                        list.add("请先选择左侧按钮");
                    }

                    if (adapterView.getItemAtPosition(i).toString().equals("田赛")) {
                        if (getIsStudent() == 1) {
                            list = mData.get(post).get(0).getStudentProject().getFieldEvents();
                        } else {
                            list = mData.get(post).get(0).getTeacherProject().getFieldEvents();
                        }
                    }
                    if (adapterView.getItemAtPosition(i).toString().equals("径赛")) {
                        if (getIsStudent() == 1) {
                            list = mData.get(post).get(0).getStudentProject().getTrack();
                        } else {
                            list = mData.get(post).get(0).getTeacherProject().getTrack();
                        }

                    }
                    TowAdapter.clear();
                    TowAdapter.addAll(list);
                    TowAdapter.notifyDataSetChanged();
                    holder1.mTowSpinner.setSelection(projectSelect.getUserConetent(),false);


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            });

            holder1.mTowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    projectSelect.setUserConetentstr(parent.getItemAtPosition(position).toString());
                    projectSelect.setUserConetent(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

    }

    public void setOnClickListener(OnClickListener OnClickListener) {
        mOnClickListener = OnClickListener;
    }

    public interface OnClickListener {
        void onClick(View v, int i);
    }


    @Override
    public int getItemCount() {
        if (type == 3) {
            return mData.size();
        } else {
            return mData.size() + 1;
        }

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setnewList(ArrayList<ArrayList<BeanList>> data) {
        mData = data;
        notifyDataSetChanged();
    }

    static
    class ViewHolderOne extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.noeSpinner)
        Spinner mNoeSpinner;
        @BindView(R.id.towSpinner)
        Spinner mTowSpinner;

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
