package com.jc.architecture.lib_uikit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by JC on 2016/4/20.
 */
public class TitleView extends RelativeLayout implements View.OnClickListener {

    private Context context;
    private TextView tvLeft;
    private TextView tvName;
    private TextView tvRight;

    private OnChildClickListener onChildClickListener;
    private CustomChildCallback customChildCallback;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int)(context.getResources().getDimension(R.dimen.title_height))));
        setBackgroundResource(R.color.main_bg_title);
        LayoutInflater.from(context).inflate(R.layout.view_title, this);

        tvLeft = (TextView) findViewById(R.id.tv_title_v_left);
        tvName = (TextView) findViewById(R.id.tv_title_v_name);
        tvRight = (TextView) findViewById(R.id.tv_title_v_right);
        tvLeft.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        if(customChildCallback != null) {
            customChildCallback.custemChild(tvLeft, tvName, tvRight);
        }
    }

    @Override
    public void onClick(View v) {
        if(onChildClickListener != null) {
            int id = v.getId();
            if (id == R.id.tv_title_v_left) {
                onChildClickListener.onLeftClick();
            } else if (id == R.id.tv_title_v_name) {
                onChildClickListener.onNameClick();
            } else if (id == R.id.tv_title_v_right) {
                onChildClickListener.onRightClick();
            }
        }
    }

    /**
     * set left button text
     * @param textRes null means ""
     */
    public TitleView setLeftText(Integer textRes) {
        if(textRes != null) {
            tvLeft.setText(textRes);
        } else {
            tvLeft.setText(null);
        }
        return this;
    }
    /**
     * set title name
     * @param textRes null means ""
     */
    public TitleView setTitleNameText(Integer textRes) {
        if(textRes != null) {
            tvName.setText(textRes);
        } else {
            tvName.setText(null);
        }
        return this;
    }
    /**
     * set right button text
     * @param textRes null means ""
     */
    public TitleView setRightText(Integer textRes) {
        if(textRes != null) {
            tvRight.setText(textRes);
        } else {
            tvRight.setText(null);
        }
        return this;
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }

    public interface OnChildClickListener {
        void onLeftClick();
        void onNameClick();
        void onRightClick();
    }

    public void setCustomChildCallback(CustomChildCallback customChildCallback) {
        this.customChildCallback = customChildCallback;
    }

    public interface CustomChildCallback {
        void custemChild(TextView tvLeft, TextView tvTitleName, TextView tvRight);
    }
}
