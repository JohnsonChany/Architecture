package com.jc.architecture.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.jc.architecture.R;
import com.jc.architecture.view.activity.BaseActivity;


public class MainActivity extends BaseActivity {

    private static final int REQ_CODE_ASK_STORAGE = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQ_CODE_ASK_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                        @NonNull String permissions[],
                                        @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQ_CODE_ASK_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                } else {
                    finish();
                }
                break;
            }
        }
    }

    public void toLogin(View view) {
        navigator.navigateToLogin(this);
    }

    public void toRegister(View view) {
        navigator.navigateToRegister(this);
    }

}
