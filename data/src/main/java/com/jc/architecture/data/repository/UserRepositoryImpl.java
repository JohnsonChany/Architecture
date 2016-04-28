package com.jc.architecture.data.repository;

import com.jc.architecture.data.model.BaseResp;
import com.jc.architecture.data.model.LoginDataModel;
import com.jc.architecture.data.rest.UserApiService;
import com.jc.architecture.domain.repository.UserRepository;
import com.jc.architecture.lib_component.cache.UserCache;
import com.jc.architecture.lib_component.tools.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by JC on 2016/4/14.
 *
 */
public class UserRepositoryImpl extends BaseRepositoryImpl implements UserRepository {

    private UserApiService userApiService;
    private final UserCache userCache;

    @Inject
    public UserRepositoryImpl(Gson gson, Log log, UserApiService userApiService,
                              UserCache userCache) {
        super(gson, log);
        this.userApiService = userApiService;
        this.userCache = userCache;
    }

    @Override
    public Observable<BaseResp> doLogin(Map<String, String> params) {
        return userApiService.requestLogin(params)
                .map(new Func1<BaseResp, BaseResp>() {
                    @Override
                    public BaseResp call(BaseResp baseResp) {
                        LoginDataModel loginDataModel = (LoginDataModel) baseResp
                                .parseData(gson, new TypeToken<LoginDataModel>(){});
                        userCache.initByLogin(loginDataModel.getUUID(),
                                loginDataModel.getUser().getNickname());
                        return baseResp;
                    }
                });
    }
}
