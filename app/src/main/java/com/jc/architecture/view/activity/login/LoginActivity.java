package com.jc.architecture.view.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jc.architecture.App;
import com.jc.architecture.R;
import com.jc.architecture.di.components.DaggerUserComponent;
import com.jc.architecture.di.components.UserComponent;
import com.jc.architecture.domain.interactor.UseCase;
import com.jc.architecture.lib_component.exception.ApiException;
import com.jc.architecture.lib_component.factory.UserRestParamFactory;
import com.jc.architecture.lib_component.rest.BaseRestSubscriber;
import com.jc.architecture.lib_component.utils.ToastUtil;
import com.jc.architecture.lib_uikit.LoginView;
import com.jc.architecture.view.activity.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by JC on 2016/4/14.
 * 登陆页面
 */
public class LoginActivity extends BaseActivity implements LoginView.OnLoginClickListener {

    @Inject
    @Named("login")
    UseCase doLoginUseCase;

    @Inject
    UserRestParamFactory userRestParamFactory;

    @Bind(R.id.loginView)
    LoginView loginView;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
        initView();
    }

    private void initInject() {
        UserComponent userComponent = DaggerUserComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .build();
        userComponent.inject(this);
    }

    private void initView() {
        loginView.setOnLoginClickListener(this);
    }

    @Override
    public void onLoginClick(String account, String pwd) {
        Map<String, String> params = new HashMap<>();
        userRestParamFactory.processLoginParam(params, account, pwd);
        this.doLoginUseCase.execute(params, new BaseRestSubscriber() {
            @Override
            public void onNext(Object o) {
                ToastUtil.getToast(LoginActivity.this, R.string.login_success).show();
            }

            @Override
            protected void onError(ApiException ex) {
                ToastUtil.getToast(LoginActivity.this, ex.getDisplayMessage()).show();
            }

            @Override
            protected void onPermissionError(ApiException ex) {
                ToastUtil.getToast(LoginActivity.this, ex.getDisplayMessage()).show();
            }
        }, AndroidSchedulers.mainThread());
    }

    @Override
    protected void onDestroy() {
        loginView.unsubscribe();
        doLoginUseCase.unSubscribe();
        super.onDestroy();
    }
}
