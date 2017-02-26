package com.design.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.design.mvp.BaseApplication;
import com.design.mvp.R;
import com.design.mvp.view.base.BaseActivity;

/**
 * 过渡界面
 * Created by hp on 2017/2/26.
 */

public class FlashActivity extends BaseActivity {


    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            LoginActivity.startActivity(FlashActivity.this);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BaseApplication.CODE = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, 1000);
    }

    @Override
    protected Class getPresenterClass() {
        return null;
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(mRunnable);
        super.onStop();
    }
}
