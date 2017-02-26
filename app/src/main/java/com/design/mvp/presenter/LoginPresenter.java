package com.design.mvp.presenter;

import com.design.mvp.model.LoginModel;
import com.design.mvp.modelinterface.ILoginModel;
import com.design.mvp.viewinterface.ILoginView;

/**
 * Created by hp on 2017/2/26.
 */

public class LoginPresenter extends BasePresenter<ILoginView, LoginModel> implements ILoginModel {

    @Override
    protected Class<LoginModel> getModelClass() {
        return LoginModel.class;
    }

    public void login(String params) {
        mModel.login(params);
    }

    @Override
    public void loginSuccess(String result) {
        mView.loginSuccess(result);
    }

    @Override
    public void loginFail(int errorCode, String msg) {

    }

}
