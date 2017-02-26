package com.design.mvp.modelinterface;

/**
 * Created by hp on 2017/2/26.
 */

public interface ILoginModel extends IBaseModel {

    void loginSuccess(String result);

    void loginFail(int errorCode,String msg);
}
