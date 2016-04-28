package com.jc.architecture.di.components;


import com.jc.architecture.data.di.modules.UserApiModule;
import com.jc.architecture.di.modules.UserModule;
import com.jc.architecture.lib_di.di.PerActivity;
import com.jc.architecture.view.activity.login.LoginActivity;

import dagger.Component;

/**
 * Created by JC on 2016/4/14.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {UserModule.class, UserApiModule.class})
public interface UserComponent {

    void inject(LoginActivity loginActivity);

}
