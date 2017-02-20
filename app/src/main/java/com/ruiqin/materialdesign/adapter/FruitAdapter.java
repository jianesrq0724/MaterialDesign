package com.ruiqin.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiqin.materialdesign.R;
import com.ruiqin.materialdesign.activity.CollapsingToolbarActivity;
import com.ruiqin.materialdesign.entity.Fruit;
import com.ruiqin.materialdesign.utils.ImageLoadUtils;

import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                CollapsingToolbarActivity.start(mContext, fruit.getName(), fruit.getImageUrl());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());

        //判断是网络地址还是本地资源
        if (fruit.getImageUrl() != null) {
            ImageLoadUtils.load(mContext, fruit.getImageUrl(), holder.fruitImage);
        } else {
            ImageLoadUtils.load(mContext, fruit.getImageId(), holder.fruitImage);
        }
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}
