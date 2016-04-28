package com.jc.architecture.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jc.architecture.App;
import com.jc.architecture.lib_component.tools.Log;
import com.jc.architecture.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by JC on 2016/4/14.
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Log log;

    @Inject
    protected Navigator navigator;

    public abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        App.getInstance().getAppComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
