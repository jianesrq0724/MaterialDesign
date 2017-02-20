package com.ruiqin.materialdesign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import com.ruiqin.materialdesign.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Navigation View 抽屉导航
 */
public class NavigationActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private long exitTimeMillis = 0;

    private Fruit[] mFruits = {new Fruit("test1", R.mipmap.test1), new Fruit("test2", R.mipmap.test2),
            new Fruit("test3", R.mipmap.test3), new Fruit("test4", R.mipmap.test4),
            new Fruit("test5", R.mipmap.test5), new Fruit("test6", R.mipmap.test6)};

    private List<Fruit> mFruitList = new ArrayList<Fruit>();

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context.getApplicationContext(), NavigationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setTitle("Title");
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.mipmap.navation);
        }

//        mNavView.setCheckedItem(R.id.navigation_library);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerlayout.closeDrawers();
                return true;
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(NavigationActivity.this, "哈哈");
            }
        });


        initFruits();
        FruitAdapter fruitAdapter = new FruitAdapter(mFruitList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(fruitAdapter);

    }

    private void initFruits() {
        mFruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(mFruits.length);
            mFruitList.add(mFruits[index]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerlayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    protected int getChildView() {
        return R.layout.activity_navigation;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerlayout.closeDrawers();
        } else if ((System.currentTimeMillis() - exitTimeMillis) > 2000) {
            exitTimeMillis = System.currentTimeMillis();
            Snackbar.make(mFab, "再按一次退出", Snackbar.LENGTH_LONG)
                    .setAction("点击退出", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NavigationActivity.super.onBackPressed();
                        }
                    }).show();
        } else {
            super.onBackPressed();
        }

    }
}
