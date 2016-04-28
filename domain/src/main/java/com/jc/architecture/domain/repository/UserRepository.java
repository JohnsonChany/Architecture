package com.jc.architecture.domain.repository;

import java.util.Map;

import rx.Observable;

/**
 * Created by JC on 2016/4/13.
 *
 */
public interface UserRepository {

    Observable doLogin(Map<String, String> params);
}
