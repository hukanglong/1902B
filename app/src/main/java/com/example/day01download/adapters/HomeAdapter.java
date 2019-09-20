package com.example.day01download.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day01download.R;
import com.example.day01download.beans.Bean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<Bean.DataBean.DatasBean> mList;

    public HomeAdapter(Context context, List<Bean.DataBean.DatasBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1){
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, null);
            return new MyHolder1(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item02, null);
            return new MyHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 1){
            holder = new MyHolder1()
        }else {

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position%3==0){
            return 1;
        }else {
            return 2;
        }
    }

    public class MyHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_it)
        ImageView mIvIt;
        @BindView(R.id.title_it)
        TextView mTitleIt;
        public MyHolder1(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class MyHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_it)
        ImageView mIvIt;
        @BindView(R.id.title_it)
        TextView mTitleIt;
        public MyHolder2(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
