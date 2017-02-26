package com.design.mvp.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.design.mvp.BaseApplication;
import com.design.mvp.presenter.BasePresenter;
import com.design.mvp.viewinterface.IBaseView;

import java.lang.ref.WeakReference;

/**
 * Activity基类
 * Created by hp on 2017/2/26.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    private P mPresenter;
    private Toast mToast;
    protected Handler mHandler;

    /**
     * 创建静态Handler，防止非静态Handler内部类持有Activity引用，在Handler里消息未处理完，内存泄漏
     */
    protected final class XHandler extends Handler {
        WeakReference<BaseActivity> weakReference;

        public XHandler(BaseActivity act) {
            weakReference = new WeakReference<>(act);
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakReference.get() != null && !weakReference.get().isFinishing()) {
                weakReference.get().handleMessage(msg);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BaseApplication.CODE == -1) {
            BaseApplication.getInstance().resetApplication();
            return;
        }
        this.mHandler = new XHandler(this);
        Class<P> clz = getPresenterClass();
        try {
            if (null != clz) {
                mPresenter = clz.newInstance();
                mPresenter.onCreate(this, savedInstanceState);
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(clz.getSimpleName() + " Class has empty Constructor?"
                    + " 异常：确定" + clz.getSimpleName() + "有无参公有的构造器?");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(clz.getSimpleName() + " Class can't access?"
                    + " 异常：确定" + clz.getSimpleName() + "公有的构造器?");
        }
    }

    protected abstract Class<P> getPresenterClass();

    /**
     * 主线程消息处理
     */
    public void handleMessage(Message msg) {
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
            mToast = Toast.makeText(getApplication(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        if (null != mPresenter) {
            mPresenter.onDestroy();
        }
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    /**
     * 页面刷新
     */
    public void refresh() {
        Intent intent = new Intent(this, this.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
