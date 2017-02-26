package com.design.mvp.view.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.design.mvp.BaseApplication;
import com.design.mvp.presenter.BasePresenter;
import com.design.mvp.viewinterface.IBaseView;

import java.lang.ref.WeakReference;

/**
 * Created by hp on 2017/2/26.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    private P mPresenter;
    private Toast mToast;
    protected Handler mHandler;
    protected boolean mViewCreated;
    protected View mContentView;

    /**
     * 创建静态Handler，防止非静态Handler内部类持有Activity引用，在Handler里消息未处理完，内存泄漏
     */
    protected final class XHandler extends Handler {
        WeakReference<BaseFragment> weakReference;

        public XHandler(BaseFragment fragment) {
            weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakReference.get() != null && !weakReference.get().isVisible()) {
                weakReference.get().handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mHandler = new XHandler(this);
        Class<P> clz = getPresenterClass();
        try {
            if (null != clz) {
                mPresenter = clz.newInstance();
                mPresenter.onCreate(this, savedInstanceState);
            }
        } catch (java.lang.InstantiationException e) {
            throw new RuntimeException(clz.getSimpleName() + " Class has empty Constructor?"
                    + " 异常：确定" + clz.getSimpleName() + "有无参公有的构造器?");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(clz.getSimpleName() + " Class can't access?"
                    + " 异常：确定" + clz.getSimpleName() + "公有的构造器?");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null==mContentView) {
            mContentView = inflater.inflate(layoutId(),null);
            initView();
            initData();
        }else{
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            parent.removeView(mContentView);
        }
        return mContentView;
    }

    protected abstract int layoutId();

    protected abstract void initView();

    protected void initData(){}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewCreated = true;
    }

    protected abstract Class<P> getPresenterClass();

    /**
     * 主线程消息处理
     */
    public void handleMessage(Message msg) {
        if (!mViewCreated) {
            throw new RuntimeException("Are you sure sendMessage after onCreatedView?");
        }
    }

    /**
     * 获取Presenter对象
     */
    public P getPresenter() {
        if (null == mPresenter) {
            throw new RuntimeException("Are you sure " + getClass().getSimpleName() + " getPresenterClass return value not null?");
        }
        return mPresenter;
    }

    /**
     * Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        if (null == mToast) {
            mToast = Toast.makeText(BaseApplication.getInstance(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    @Override
    public void onDestroy() {
        if (null != mPresenter) {
            mPresenter.onDestroy();
        }
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
