package com.ruiqin.materialdesign.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.ruiqin.materialdesign.R;
import com.ruiqin.materialdesign.activity.CollapsingToolbarActivity;
import com.ruiqin.materialdesign.activity.NavigationActivity;
import com.ruiqin.materialdesign.activity.SwipeRefreshLayoutActivity;
import com.ruiqin.materialdesign.activity.TableLayoutActivity;
import com.ruiqin.materialdesign.activity.TextInputLayoutActivity;
import com.ruiqin.materialdesign.adapter.MainRecyclerViewAdapter;
import com.ruiqin.materialdesign.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainControl.View {

    long exitTimeMillis = 0;//时间

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private Context mContext;

    @Override
    protected int getChildView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mPresenter.setAdapter(mContext);
    }

    @Override
    public void setAdapterResult(MainRecyclerViewAdapter recyclerViewAdapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于
        mRecyclerView.setAdapter(recyclerViewAdapter);

        /**
         * 设置点击事件
         */
        recyclerViewAdapter.setItemClickLister(new MainRecyclerViewAdapter.OnItemClickLister() {
            @Override
            public void onClick(View view, int position) {
                mPresenter.setItemClick(position);
            }
        });
    }

    @Override
    public void setItemClickNavigationResult() {
        NavigationActivity.startActivity(mContext);
    }

    @Override
    public void setItemClickTextInputLayoutResult() {
        TextInputLayoutActivity.startActivity(mContext);
    }


    @Override
    public void setItemClickTableLayoutResult() {
        TableLayoutActivity.startActivity(mContext);
    }

    @Override
    public void setItemClickSwipeRefreshResult() {
        SwipeRefreshLayoutActivity.start(mContext);
    }

    @Override
    public void setItemClickCollapsingToolbarResult() {
        CollapsingToolbarActivity.start(mContext);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == event.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTimeMillis) > 2000) {
                exitTimeMillis = System.currentTimeMillis();
                Snackbar.make(mRecyclerView, "再按一次退出~~", Snackbar.LENGTH_SHORT).show();
            } else {
                finish();
            }
            return true;
        }
        return false;
    }
}
