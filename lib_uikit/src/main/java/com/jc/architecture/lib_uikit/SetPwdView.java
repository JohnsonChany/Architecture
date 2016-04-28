package com.jc.architecture.lib_uikit;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by JC on 2016/4/15.
 * 登陆view
 * 注意使用时需要在onDestroy()中调用unsubscribe()，取消Button的RxBinding注册
 */
public class SetPwdView extends LinearLayout {

    private Context context;

    private EditText etPwdOne;
    private EditText etPwdTwo;
    private TextView btnComplete;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(checkInput()) {
                btnComplete.setEnabled(true);
            } else {
                btnComplete.setEnabled(false);
            }
        }
    };

    private OnCompleteClickListener listener;

    private Subscription subscription;

    public SetPwdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

        this.context = context;
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_set_pwd, this);

        etPwdOne = (EditText) findViewById(R.id.et_set_pwd_one);
        etPwdOne.addTextChangedListener(textWatcher);

        etPwdTwo = (EditText) findViewById(R.id.et_set_pwd_two);
        etPwdTwo.addTextChangedListener(textWatcher);

        btnComplete = (TextView) findViewById(R.id.btn_set_pwd_complete);
        subscription = RxView.clicks(btnComplete)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if(listener != null)
                            listener.onCompleteClick(etPwdOne.getText().toString(),
                                    etPwdTwo.getText().toString());
                    }
                });
    }

    private boolean checkInput() {
        return !TextUtils.isEmpty(etPwdOne.getText().toString())
                || !TextUtils.isEmpty(etPwdTwo.getText().toString());
    }

    public void setOnCompleteClickListener (OnCompleteClickListener listener) {
        this.listener = listener;
    }

    public void unsubscribe() {
        subscription.unsubscribe();
    }

    public interface OnCompleteClickListener {
        void onCompleteClick(String pwdOne, String pwdTwo);
    }
}
