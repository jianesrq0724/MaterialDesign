package com.ruiqin.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruiqin.materialdesign.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ShenRuiqin on 2016/10/21.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder> {
    private List<String> mListData;
    private Context mContext;
    private OnItemClickLister mOnItemClickLister;


    public MainRecyclerViewAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mListData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mListData.get(position));
    }

    @Override
    public int getItemCount() {
        return null == mListData ? 0 : mListData.size();
    }

    /**
     * 设置点击事件
     *
     * @param onItemClickLister
     */
    public void setItemClickLister(OnItemClickLister onItemClickLister) {
        this.mOnItemClickLister = onItemClickLister;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        TextView textView;
        @BindView(R.id.textView)
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

//            textView = (TextView) itemView.findViewById(R.id.textView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
//            ToastUtils.show(mContext, getAdapterPosition() + "");
            mOnItemClickLister.onClick(v, getAdapterPosition());
        }
    }

    /**
     * 自定义点击事件，由子类来实现
     */
    public interface OnItemClickLister {
        void onClick(View view, int position);
    }
}
