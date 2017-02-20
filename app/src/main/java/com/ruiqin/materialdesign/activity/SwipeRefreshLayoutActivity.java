package com.ruiqin.materialdesign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ruiqin.materialdesign.R;
import com.ruiqin.materialdesign.adapter.FruitAdapter;
import com.ruiqin.materialdesign.base.BaseActivity;
import com.ruiqin.materialdesign.entity.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class SwipeRefreshLayoutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.swipe_recycler_view)
    RecyclerView mSwipeRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    FruitAdapter fruiiAdapter;

    private List<Fruit> mFruitList = new ArrayList<Fruit>();

    private Fruit[] mFruits = {new Fruit("test1", "http://img15.3lian.com/2015/f2/50/d/71.jpg"),
            new Fruit("test2", "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1307/22/c3/23612952_1374475936630.jpg"),
            new Fruit("test3", "http://img.taopic.com/uploads/allimg/110123/292-11012314253480.jpg"),
            new Fruit("test4", "http://pic72.nipic.com/file/20150719/21422793_093948870000_2.jpg"),
            new Fruit("test5", "http://img4.imgtn.bdimg.com/it/u=3818654092,2744334664&fm=214&gp=0.jpg"),
            new Fruit("test6", "http://sc.jb51.net/uploads/allimg/130831/2-130S1003525459.jpg")};


    public static void start(Context context) {
        context.startActivity(new Intent(context, SwipeRefreshLayoutActivity.class));
    }

    @Override
    protected int getChildView() {
        return R.layout.activity_swipe_refresh_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBar();
        setClickOpenNavigationView();
        setNavigationClickLister();
        initFruitDate();
        setRecyclerViewAdapter();
        setSwipeRefresh();
    }

    private void setSwipeRefresh() {
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                SystemClock.sleep(1000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruitDate();
                        fruiiAdapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruitDate() {
        mFruitList.clear();
        for (int i = 0; i < 5000; i++) {
            Random random = new Random();
            int index = random.nextInt(mFruits.length);
            mFruitList.add(mFruits[index]);
        }
    }

    private void setRecyclerViewAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mSwipeRecyclerView.setLayoutManager(gridLayoutManager);
        fruiiAdapter = new FruitAdapter(mFruitList);
        mSwipeRecyclerView.setAdapter(fruiiAdapter);
    }

    private void setNavigationClickLister() {
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerlayout.closeDrawers();
                return true;
            }
        });
    }

    private void setClickOpenNavigationView() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }

    /**
     * 设置toolbar
     */
    private void setToolBar() {
        mToolbar.setTitle("swipeRefreshLayout");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }
}
