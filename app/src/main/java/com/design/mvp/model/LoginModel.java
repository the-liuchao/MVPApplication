package com.design.mvp.model;

import com.design.mvp.modelinterface.ILoginModel;

/**
 * Created by hp on 2017/2/26.
 */

public class LoginModel extends BaseModel<ILoginModel> {


    public void login(String params) {
        //CODE 登录网络请求逻辑
        getPresenter().loginSuccess("用户 " + params + " 登录成功");
    }

    @Override
    public void cancel() {

    }
}
