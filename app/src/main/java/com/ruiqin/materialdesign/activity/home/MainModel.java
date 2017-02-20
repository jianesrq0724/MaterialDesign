package com.ruiqin.materialdesign.activity.home;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShenRuiqin on 2016/10/21.
 */

public class MainModel implements MainControl.Model {
    private List<String> mRecyData = new ArrayList<String>();

    @Override
    public List<String> initData() {
        mRecyData.add("Navigation View 抽屉导航");
        mRecyData.add("TextInputLayout 输入框控件的悬浮标签");
        mRecyData.add("TableLayout");
        mRecyData.add("SwipeRefreshLayout");
        mRecyData.add("CollapsingToolbarLayout折叠式");
        return mRecyData;
    }
}
