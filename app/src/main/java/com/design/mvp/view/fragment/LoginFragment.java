package com.design.mvp.view.fragment;

import android.view.View;

import com.design.mvp.R;
import com.design.mvp.presenter.LoginPresenter;
import com.design.mvp.view.base.BaseFragment;
import com.design.mvp.viewinterface.ILoginView;

/**
 * 登录界面
 * Created by hp on 2017/2/26.
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements ILoginView, View.OnClickListener {

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mContentView.findViewById(R.id.login).setOnClickListener(this);
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
        getPresenter().login("liuchao");
    }
}
