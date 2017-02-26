package com.design.mvp;

import android.app.Application;
import android.content.Intent;

import com.design.mvp.view.activity.FlashActivity;

/**
 * Created by hp on 2017/2/26.
 */

public class BaseApplication extends Application {

    public static int CODE = -1;                        //应用被重启标记,-1 表示应用启动, 1表示启动完成

    private static BaseApplication INSTANCE;            //应用实例对象

    public static BaseApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    /**如果应用被销毁重启, 是否重新初始化应用(重新走一遍FlashActivity)*/
    public void resetApplication() {
        Intent intent = new Intent(this, FlashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
