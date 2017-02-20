package com.ruiqin.materialdesign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.ruiqin.materialdesign.R;
import com.ruiqin.materialdesign.adapter.FruitAdapter;
import com.ruiqin.materialdesign.base.BaseActivity;
import com.ruiqin.materialdesign.entity.Fruit;
import com.ruiqin.materialdesign.utils.ImageLoadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class CollapsingToolbarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    @BindView(R.id.collap_navi_view)
    NavigationView mCollapNaviView;
    @BindView(R.id.collap_recycler_view)
    RecyclerView mCollapRecyclerView;
    @BindView(R.id.collap_image_view)
    ImageView mCollapImageView;

    private Fruit[] mFruits = {new Fruit("test1", R.mipmap.test1), new Fruit("test2", R.mipmap.test2),
            new Fruit("test3", R.mipmap.test3), new Fruit("test4", R.mipmap.test4),
            new Fruit("test5", R.mipmap.test5), new Fruit("test6", R.mipmap.test6)};

    private List<Fruit> mFruitList = new ArrayList<Fruit>();

    private final static String IMAGE_NAME = "iamgeName";
    private final static String IMAGE_URL = "iamgeUrl";


    public static void start(Context context) {
        context.startActivity(new Intent(context, CollapsingToolbarActivity.class));
    }

    public static void start(Context context, String imageName, String imageUrl) {
        Intent intent = new Intent(context, CollapsingToolbarActivity.class);
        intent.putExtra(IMAGE_NAME, imageName);
        intent.putExtra(IMAGE_URL, imageUrl);
        context.startActivity(intent);
    }


    @Override
    protected int getChildView() {
        return R.layout.activity_collapsing_toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
        setClickNavigationLister();
        initFruitData();
        setRecyclerViewAdapter();

    }

    private void setRecyclerViewAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mCollapRecyclerView.setLayoutManager(gridLayoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(mFruitList);
        mCollapRecyclerView.setAdapter(fruitAdapter);
    }

    private void initFruitData() {
        mFruitList.clear();
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int index = random.nextInt(mFruits.length);
            mFruitList.add(mFruits[index]);
        }
    }

    private void setClickNavigationLister() {
        mCollapNaviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerlayout.closeDrawers();
                return true;
            }
        });

    }

    private void setToolBar() {
        mToolbar.setTitle("折叠标题栏");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if (intent != null) {
            String imageUrl = intent.getStringExtra(IMAGE_URL);
            String imageName = intent.getStringExtra(IMAGE_NAME);

            if (imageUrl != null) {
                ImageLoadUtils.load(this, imageUrl, mCollapImageView);
                mToolbar.setTitle(imageName);
            } else {
                int index = new Random().nextInt(mFruits.length);
                ImageLoadUtils.load(this, mFruits[index].getImageId(), mCollapImageView);
                mToolbar.setTitle(mFruits[index].getName());
            }

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerlayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
