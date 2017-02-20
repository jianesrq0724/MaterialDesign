package com.ruiqin.materialdesign.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ruiqin.materialdesign.utils.TUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ruiqin.shen.
 * 类说明:
 */

public abstract class BaseActivity<T extends BasePresenter, M extends BaseModel> extends AppCompatActivity {
    public T mPresenter;
    public M mModel;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getChildView());
        mUnbinder = ButterKnife.bind(this);
        if (this instanceof BaseView) {
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    protected abstract int getChildView();

}
