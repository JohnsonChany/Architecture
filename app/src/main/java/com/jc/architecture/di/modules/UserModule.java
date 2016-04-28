package com.jc.architecture.di.modules;

import com.jc.architecture.data.repository.UserRepositoryImpl;
import com.jc.architecture.domain.interactor.DoLoginUseCase;
import com.jc.architecture.domain.interactor.UseCase;
import com.jc.architecture.domain.repository.UserRepository;
import com.jc.architecture.lib_component.cache.UserCache;
import com.jc.architecture.lib_component.factory.UserRestParamFactory;
import com.jc.architecture.lib_di.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JC on 2016/4/13.
 * 用户Module
 */

@Module
public class UserModule {

    @Provides
    @PerActivity
    UserRepository provideUserRepository(UserRepositoryImpl userRepositoryImpl) {
        return userRepositoryImpl;
    }

    @Provides
    @PerActivity
    UserRestParamFactory provideUserRestParamFactory(UserCache userCache) {
        return new UserRestParamFactory(userCache);
    }

    @Provides
    @PerActivity
    @Named("login")
    UseCase provideDoLoginUseCase(UserRepository userRepository) {
        return new DoLoginUseCase(userRepository);
    }
}
