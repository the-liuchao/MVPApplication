package com.design.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.design.mvp.R;
import com.design.mvp.presenter.LoginPresenter;
import com.design.mvp.view.base.BaseActivity;
import com.design.mvp.view.fragment.LoginFragment;
import com.design.mvp.viewinterface.ILoginView;

/**
 * 登录界面
 * Created by hp on 2017/2/26.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView, View.OnClickListener {

    public static void startActivity(BaseActivity act) {
        Intent intent = new Intent(act, LoginActivity.class);
        act.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //BaseActivity测试
//        setContentView(R.layout.activity_login);
//        findViewById(R.id.login).setOnClickListener(this);
        //BaseFragment测试
        setContentView(R.layout.fragment_login);
        initView();
    }

    private void initView() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.content_view, LoginFragment.newInstance())
                .commitAllowingStateLoss();
    }

    @Override
    protected Class<LoginPresenter> getPresenterClass() {
        return LoginPresenter.class;
    }


    @Override
    public void loginSuccess(String successMsg) {
        showToast(successMsg);
    }

    @Override
    public void onClick(View v) {
        getPresenter().login("the_liuchao");
    }
}
