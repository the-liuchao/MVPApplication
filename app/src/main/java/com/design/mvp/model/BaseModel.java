package com.design.mvp.model;

import com.design.mvp.modelinterface.IBaseModel;

/**
 * Created by hp on 2017/2/26.
 */

public abstract class BaseModel<P extends IBaseModel> {

    private P mPresenter;

    public void bind(P presenter) {
        this.mPresenter = presenter;
    }

    public P getPresenter() {
        if (null == mPresenter) {
            throw new RuntimeException("Are you Overloading "
                    + mPresenter.getClass().getSimpleName()
                    + " getModelClass method return Presenter Class?"
                    + "异常：确定" + mPresenter.getClass().getSimpleName()
                    + "中 getModelClass返回的Presenter类字节码不是null或可用?");
        }

        return mPresenter;
    }

    /**
     * Presenter对象销毁
     * 1. 取消网络数据Request；
     * 2. 获取的本地数据回调；
     * 3. 回收大数据
     */
    public abstract void cancel();
}
