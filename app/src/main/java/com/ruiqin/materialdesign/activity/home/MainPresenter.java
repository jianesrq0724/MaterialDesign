package com.ruiqin.materialdesign.activity.home;

import android.content.Context;

import com.ruiqin.materialdesign.adapter.MainRecyclerViewAdapter;

/**
 * Created by ShenRuiqin on 2016/10/21.
 */

public class MainPresenter extends MainControl.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void setAdapter(Context context) {
        MainRecyclerViewAdapter recyclerViewAdapter = new MainRecyclerViewAdapter(context, mModel.initData());
        mView.setAdapterResult(recyclerViewAdapter);
    }

    @Override
    public void setItemClick(int position) {
        switch (position) {
            case 0:
                mView.setItemClickNavigationResult();
                break;
            case 1:
                mView.setItemClickTextInputLayoutResult();
                break;
            case 2:
                mView.setItemClickTableLayoutResult();
                break;
            case 3:
                mView.setItemClickSwipeRefreshResult();
                break;
            case 4:
                mView.setItemClickCollapsingToolbarResult();
                break;
        }
    }
}
