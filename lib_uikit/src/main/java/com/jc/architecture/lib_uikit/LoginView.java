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
 * <p>登陆view</p>
 * <p><b>注意：使用时需要在必要的生命周期（如onDestroy）中调用{@link #unsubscribe()}，
 * 取消Button的RxBinding注册</b></p>
 * @version 1.0
 */
public class LoginView extends LinearLayout {

    private Context context;

    private EditText etAccount;
    private EditText etPwd;
    private TextView btnDoLogin;

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
                btnDoLogin.setEnabled(true);
            } else {
                btnDoLogin.setEnabled(false);
            }
        }
    };

    private OnLoginClickListener listener;

    private Subscription subscription;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {

        setOrientation(VERTICAL);
        setGravity(Gravity.END);
        LayoutInflater.from(context).inflate(R.layout.view_login, this);

        etAccount = (EditText) findViewById(R.id.et_login_v_account);
        etAccount.addTextChangedListener(textWatcher);

        etPwd = (EditText) findViewById(R.id.et_login_v_pwd);
        etPwd.addTextChangedListener(textWatcher);

        btnDoLogin = (TextView) findViewById(R.id.btn_login_v_do_login);
        subscription = RxView.clicks(btnDoLogin)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if(listener != null)
                            listener.onLoginClick(etAccount.getText().toString(),
                                    etPwd.getText().toString());
                    }
                });
    }

    private boolean checkInput() {
        return !TextUtils.isEmpty(etAccount.getText().toString())
                || !TextUtils.isEmpty(etPwd.getText().toString());
    }

    public void setOnLoginClickListener (OnLoginClickListener listener) {
        this.listener = listener;
    }

    /**
     * 取消RxView注册的点击事件
     */
    public void unsubscribe() {
        subscription.unsubscribe();
    }

    public interface OnLoginClickListener {
        void onLoginClick(String account, String pwd);
    }
}
