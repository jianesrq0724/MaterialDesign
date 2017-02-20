package com.ruiqin.materialdesign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ruiqin.materialdesign.R;
import com.ruiqin.materialdesign.base.BaseActivity;
import com.ruiqin.materialdesign.fragment.PageFragment;

import butterknife.BindView;

import static com.ruiqin.materialdesign.R.id.title;
import static com.ruiqin.materialdesign.R.id.viewPager;

/**
 * TabLayout
 */
public class TableLayoutActivity extends BaseActivity {

    @BindView(R.id.sliding_tabs)
    TabLayout mSlidingTabs;
    @BindView(viewPager)
    ViewPager mViewPager;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context.getApplicationContext(), TableLayoutActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(adapter);

//        mSlidingTabs.setTabMode(TabLayout.MODE_FIXED);
        mSlidingTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSlidingTabs.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getChildView() {
        return R.layout.activity_table_layout;
    }
}

class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6", "Tab7", "Tab8", "Tab9", "Tab10"};
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}