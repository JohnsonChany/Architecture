package com.jc.architecture.data.di.modules;

import com.jc.architecture.data.rest.UserApiService;
import com.jc.architecture.lib_di.di.PerActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by JC on 2016/4/13.
 * 用户类api
 */
@Module
public class UserApiModule {

    @Provides
    @PerActivity
    public UserApiService provideRequestApiService(Retrofit retrofit) {
        return retrofit.create(UserApiService.class);
    }

}
