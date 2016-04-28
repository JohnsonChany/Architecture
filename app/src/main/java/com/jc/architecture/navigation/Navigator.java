package com.jc.architecture.navigation;

import android.content.Context;
import android.content.Intent;

import com.jc.architecture.view.activity.login.LoginActivity;
import com.jc.architecture.view.activity.login.RegisterActivity;
import com.jc.architecture.view.activity.login.SetPwdActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 导航跳转管理类
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    /**
     * skip to the LoginActivity.
     */
    public void navigateToLogin(Context context) {
        if (context != null) {
            Intent intentToLaunch = LoginActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * skip to the RegisterActivity.
     */
    public void navigateToRegister(Context context) {
        if (context != null) {
            Intent intentToRegister = RegisterActivity.getCallingIntent(context);
            context.startActivity(intentToRegister);
        }
    }

    /**
     * skip to the SetPwdActivity.
     */
    public void navigateToSetPwd(Context context) {
        if (context != null) {
            Intent intentToRegister = SetPwdActivity.getCallingIntent(context);
            context.startActivity(intentToRegister);
        }
    }
}
