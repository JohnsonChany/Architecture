package com.jc.architecture.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jc.architecture.lib_di.di.HasComponent;

import butterknife.ButterKnife;

/**
 * Created by on 2016/3/17
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * 获取布局id
     */
    public abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    /**
     * 获取一个本类型的依赖注入组件
     */
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 解除绑定
        ButterKnife.unbind(this);
    }
}
