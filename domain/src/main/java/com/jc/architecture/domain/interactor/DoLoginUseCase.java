package com.jc.architecture.domain.interactor;

import com.jc.architecture.domain.repository.UserRepository;

import java.util.Map;

import rx.Observable;

/**
 * Created by JC on 2016/4/14.
 * 登陆用例
 */
public class DoLoginUseCase extends UseCase {

    private final UserRepository userRepository;

    public DoLoginUseCase(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Map<String, String> params) {
        return userRepository.doLogin(params);
    }
}
