package com.jc.architecture.data.di.modules;

import com.jc.architecture.data.rest.convert.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * 网络请求配置
 */
@Module
public class RestModule {

    private String buildType;
    private String apiHost;

    public RestModule(String buildType, String apiHost) {
        this.buildType = buildType;
        this.apiHost = apiHost;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClient() {
        // 实例化HttpLoggingInterceptor，根据buildType设置log级别
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if ("debug".equalsIgnoreCase(buildType)) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        // 构建OkHttpClient.Builder实例
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        builder.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        // 将实例化HttpLoggingInterceptor对象添加到OkHttpClient.Builder
        builder.addInterceptor(loggingInterceptor);
        return builder;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient.Builder builder, Gson gson) {
        return new Retrofit.Builder()
                // 配置baseUrl
                .baseUrl(apiHost)
                // 配置OkHttpClient
                .client(builder.build())
                // 配置Gson解析
                .addConverterFactory(GsonConverterFactory.create(gson))
                // 配置Rxjava请求
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
