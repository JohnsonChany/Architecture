package com.jc.architecture.di.modules;

import android.content.Context;


import com.jc.architecture.App;
import com.jc.architecture.BuildConfig;
import com.jc.architecture.lib_component.tools.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JC on 2016/4/13.
 */

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return this.app.getApplicationContext();
    }

    @Provides
    @Singleton
    Log provideLog() {
        return new Log(BuildConfig.DEBUG);
    }

}
