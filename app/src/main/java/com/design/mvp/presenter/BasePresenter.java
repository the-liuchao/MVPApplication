package com.design.mvp.presenter;

import android.os.Bundle;

import com.design.mvp.model.BaseModel;
import com.design.mvp.modelinterface.IBaseModel;
import com.design.mvp.viewinterface.IBaseView;

/**
 * Presenter基类
 * Created by hp on 2017/2/26.
 */

public abstract class BasePresenter<V extends IBaseView, M extends BaseModel> implements IBaseModel {

    protected V mView;

    protected M mModel;


    /**
     * 创建Presenter对象回调方法
     *
     * @param savedInstanceState
     */
    public void onCreate(V mView, Bundle savedInstanceState) {
        //初始化
        this.mView = mView;
        Class<M> clz = getModelClass();
        try {
            if (null != clz) {
                mModel = clz.newInstance();
                mModel.bind(this);
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(clz.getSimpleName() + " Class has empty Constructor?"
                    + " 异常：确定" + clz.getSimpleName() + "有无参公有的构造器?");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(clz.getSimpleName() + " Class can't access?"
                    + " 异常：确定" + clz.getSimpleName() + "公有的构造器?");
        }
    }


    /**
     * View销毁,Preseter对象回调方法
     */
    public void onDestroy() {
        if (null != mModel) {
            mModel.cancel();
        }
        //销毁
        mView = null;
        mModel = null;
    }

    /**
     * 为创建Model, 实现此方法获取Model字节码对象
     *
     * @return
     */
    protected abstract Class<M> getModelClass();
}
