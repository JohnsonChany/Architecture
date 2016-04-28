package com.jc.architecture.di.components;

import android.content.Context;

import com.google.gson.Gson;
import com.jc.architecture.data.di.modules.RestModule;
import com.jc.architecture.di.modules.AppModule;
import com.jc.architecture.lib_component.cache.UserCache;
import com.jc.architecture.lib_component.tools.Log;
import com.jc.architecture.navigation.Navigator;
import com.jc.architecture.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by JC on 2016/4/13.
 */

@Singleton
@Component(modules = {AppModule.class, RestModule.class})
public interface AppComponent {

    void inject(BaseActivity baseActivity);

    // 供其它component依赖时获取
    Context context();
    Gson gson();
    Retrofit retrofit();
    Log log();
    UserCache userCache();
    Navigator navigator();

}
