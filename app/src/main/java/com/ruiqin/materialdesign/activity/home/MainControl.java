package com.ruiqin.materialdesign.activity.home;

import android.content.Context;

import com.ruiqin.materialdesign.adapter.MainRecyclerViewAdapter;
import com.ruiqin.materialdesign.base.BaseModel;
import com.ruiqin.materialdesign.base.BasePresenter;
import com.ruiqin.materialdesign.base.BaseView;

import java.util.List;

/**
 * Created by ShenRuiqin on 2016/10/21.
 */

public interface MainControl {
    interface Model extends BaseModel {
        List<String> initData();//初始化数据
    }

    interface View extends BaseView {
        void setAdapterResult(MainRecyclerViewAdapter recyclerViewAdapter);//设置RecyclerView的适配器返回结果

        void setItemClickNavigationResult();

        void setItemClickTextInputLayoutResult();

        void setItemClickTableLayoutResult();

        void setItemClickSwipeRefreshResult();

        void setItemClickCollapsingToolbarResult();

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void setAdapter(Context context);//设置RecyclerView的适配器

        public abstract void setItemClick(int position);
    }
}
