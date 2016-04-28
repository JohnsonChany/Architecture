package com.jc.architecture.lib_uikit;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by JC on 2016/4/21.
 * 发送验证码view
 */
public class VerifyView extends LinearLayout implements View.OnClickListener {

    private Context mContext;

    private EditText etAccount;
    private EditText etVerify;
    private TextView tvSendVerify;

    private AuthCodeTimer authCodeTimer;

    private OnSendVerifyClickListener mOnSendVerifyClickListener;

    public VerifyView(Context context) {
        super(context);
        this.mContext = context;
    }

    public VerifyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VerifyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VerifyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {

        // init parent view
        this.mContext = context;
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_verify, this);

        etAccount = (EditText) findViewById(R.id.et_find_pwd_v_account);
        etVerify = (EditText) findViewById(R.id.et_find_pwd_v_pwd);

        tvSendVerify = (TextView) findViewById(R.id.tv_find_pwd_v_send_verify);
        tvSendVerify.setOnClickListener(this);
        authCodeTimer = new AuthCodeTimer(tvSendVerify, "");
    }

    public void addAccountTextChangedListener(TextWatcher textWatcher) {
        etAccount.addTextChangedListener(textWatcher);
    }

    public void addVerifyTextChangedListener(TextWatcher textWatcher) {
        etVerify.addTextChangedListener(textWatcher);
    }

    public void setOnSendVerifyClickListener(OnSendVerifyClickListener onSendVerifyClickListener) {
        mOnSendVerifyClickListener = onSendVerifyClickListener;
    }

    public String getAccount() {
        return etAccount.getText().toString();
    }

    public String getVerify() {
        return etVerify.getText().toString();
    }

    @Override
    public void onClick(View v) {
        authCodeTimer.start();
        if(mOnSendVerifyClickListener != null) mOnSendVerifyClickListener.OnSendVerifyClick();
    }

    public interface OnSendVerifyClickListener {
        void OnSendVerifyClick();
    }

}
