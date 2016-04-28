package com.jc.architecture.view.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jc.architecture.R;
import com.jc.architecture.lib_component.utils.ToastUtil;
import com.jc.architecture.lib_uikit.TitleView;
import com.jc.architecture.lib_uikit.VerifyView;
import com.jc.architecture.view.activity.BaseActivity;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by JC on 2016/4/25.
 */
public class RegisterActivity extends BaseActivity implements VerifyView.OnSendVerifyClickListener,
        CompoundButton.OnCheckedChangeListener, TextWatcher{

    @Bind(R.id.title_register)
    TitleView titleView;
    @Bind(R.id.v_register_verify)
    VerifyView verifyView;
    @Bind(R.id.rg_register_identity)
    RadioGroup rgIdentity;
    @Bind(R.id.cb_read_TOS)
    CheckBox cbTOS;
    @Bind(R.id.tv_TOS)
    TextView tvTOS;
    @Bind(R.id.btn_register_next)
    TextView btnNext;

    private Subscription clickSubscription;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        verifyView.setOnSendVerifyClickListener(this);
        verifyView.addAccountTextChangedListener(this);
        verifyView.addVerifyTextChangedListener(this);
        cbTOS.setOnCheckedChangeListener(this);
        clickSubscription = RxView.clicks(btnNext)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        navigator.navigateToSetPwd(RegisterActivity.this);
                    }
                });
    }

    @Override
    public void OnSendVerifyClick() {
        ToastUtil.getToast(this, "发送验证码123456").show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        checkBtnEnable();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        checkBtnEnable();
    }

    private void checkBtnEnable() {
        if(!TextUtils.isEmpty(verifyView.getAccount())
                && !TextUtils.isEmpty(verifyView.getVerify())
                && cbTOS.isChecked()) {
            btnNext.setEnabled(true);
        } else {
            btnNext.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        clickSubscription.unsubscribe();
        super.onDestroy();
    }
}
