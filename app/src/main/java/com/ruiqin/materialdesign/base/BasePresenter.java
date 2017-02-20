package com.ruiqin.materialdesign.base;

import android.content.Context;

/**
 * Created by Ruiqin.shen.
 * 类说明:
 */

public abstract class BasePresenter<T, M> {
    public T mView;
    public M mModel;
    public Context mContext;

    public void setVM(T v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public abstract void onStart();
}
