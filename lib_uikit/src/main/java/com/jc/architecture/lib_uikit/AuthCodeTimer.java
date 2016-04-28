package com.jc.architecture.lib_uikit;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 改变发送验证码的时间
 */
public class AuthCodeTimer extends CountDownTimer {
    // 时间从60s开始显示（以倒计时60s为例子）
    public static final int TIME_COUNT = 60000;
    // 点击发送的按钮
    private TextView btn;
    // 倒计时结束后，按钮对应显示的文字
    private int endStrRid;
    // 未计时的文字颜色，计时期间的文字颜色
    private int normalColor, timingColor;
    // 计时期间显示的文字
    private String strText;

    /**
     * @param millisInFuture    倒计时总时间（如60S，120s等）
     * @param countDownInterval 渐变时间（每次倒计1s）
     * @param btn
     * @param endStrRid
     * @param strText
     */
    public AuthCodeTimer(long millisInFuture, long countDownInterval, TextView btn, int endStrRid, String strText) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
        this.endStrRid = endStrRid;
        this.strText = strText;
    }

    public AuthCodeTimer(TextView btn, int endStrRid, String strText) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = endStrRid;
        this.strText = strText;
    }

    public AuthCodeTimer(TextView btn, String strText) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = R.string.send_verify;
        this.strText = strText;
    }

    public AuthCodeTimer(TextView btn, int normalColor, int timingColor, String strText) {
        this(btn, strText);
        this.normalColor = normalColor;
        this.timingColor = timingColor;
    }

    /**
     * 计时完毕时触发
     */
    @Override
    public void onFinish() {
        if (normalColor > 0) {
            btn.setTextColor(normalColor);
        }
        btn.setText(endStrRid);
        btn.setEnabled(true);
    }

    /**
     * 计时过程显示
     *
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        if (timingColor > 0) {
            btn.setTextColor(timingColor);
        }
        btn.setEnabled(false);
        btn.setText(millisUntilFinished / 1000 + "s" + strText);
    }
}

