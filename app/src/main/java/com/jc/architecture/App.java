package com.jc.architecture;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import com.jc.architecture.data.di.modules.RestModule;
import com.jc.architecture.di.components.AppComponent;
import com.jc.architecture.di.components.DaggerAppComponent;
import com.jc.architecture.di.modules.AppModule;
import com.jc.architecture.lib_component.utils.BackgroundUtil;

/**
 * Created by JC on 2016/4/13.
 * App
 */
public class App extends Application implements Application.ActivityLifecycleCallbacks {

    private static App instance;

    private int activityCount;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 注册ActivityLifecycleCallbacks
        registerActivityLifecycleCallbacks(this);

        initInject();
    }

    private void initInject() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .restModule(new RestModule(BuildConfig.BUILD_TYPE, BuildConfig.API_HOST))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        activityCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityCount--;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public int getActivityCount() {
        return activityCount;
    }

    public boolean isForeground() {
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return BackgroundUtil.getRunningTask(this, getPackageName());
        } else {
            return activityCount > 0;
        }
    }
}
