package com.ruiqin.materialdesign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.RadioGroup;

import com.ruiqin.materialdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by ruiqin.shen
 * 类说明：TextInputLayout 输入框控件的悬浮标签
 */
public class TextInputLayoutActivity extends SwipeBackActivity {

    @BindView(R.id.til_test)
    public TextInputLayout mTilTest;


    private RadioGroup mTrackingModeGroup;

    private SwipeBackLayout mSwipeBackLayout;
    private Unbinder mBind;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context.getApplicationContext(), TextInputLayoutActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        mBind = ButterKnife.bind(this);

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);


        mTilTest.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTilTest.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @OnClick(R.id.btn_click)
    public void BtnClick() {
        mTilTest.setError("手机号码有误");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
