package com.jc.architecture.lib_component.factory;

import android.support.annotation.NonNull;

import com.jc.architecture.lib_component.cache.UserCache;
import com.jc.architecture.lib_component.utils.VerifyUtil;

import java.util.Map;

/**
 * Created by JC on 2016/4/19.
 * 用户模块rest参数工厂
 */
public class UserRestParamFactory {

    private UserCache userCache;

    public UserRestParamFactory(UserCache userCache) {
        this.userCache = userCache;
    }

    public void processLoginParam(@NonNull Map<String, String> params,
                                  @NonNull String account,
                                  @NonNull String pwd) {
        params.put("contactsPhone", account);
        params.put("password", VerifyUtil.md5(pwd));
        params.put("type", "1");
    }

    public void processGetUserInfoParam(@NonNull Map<String, String> params) {
        params.put("uuid", userCache.getUUID());
    }

}
