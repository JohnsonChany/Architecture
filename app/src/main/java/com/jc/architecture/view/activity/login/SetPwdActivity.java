package com.jc.architecture.view.activity.login;

import android.content.Context;
import android.content.Intent;

import com.jc.architecture.R;
import com.jc.architecture.view.activity.BaseActivity;

/**
 * Created by JC on 2016/4/26.
 * 设置密码页面
 */
public class SetPwdActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SetPwdActivity.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_pwd;
    }
}
