package com.jc.architecture.data.rest;

import com.jc.architecture.data.model.BaseResp;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * user request interface
 */
public interface UserApiService {

    @POST(RequestApiPath.LOGIN)
    Observable<BaseResp> requestLogin(@QueryMap Map<String, String> params);

    @POST(RequestApiPath.REGISTER)
    Observable<BaseResp> requestUlRegisterBean(
            @QueryMap Map<String, String> params
    );

}
