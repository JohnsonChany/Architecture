package com.jc.architecture.domain.interactor;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Jc on 2016/1/29.
 * 用例
 */
public abstract class UseCase {

    private Subscription subscription = Subscriptions.empty();

    protected UseCase() {

    }

    /**
     * Builds an {@link Observable}
     * which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable buildUseCaseObservable(Map<String, String> params);

    /**
     * Executes the current use case.
     *
     * @param observer The guy who will be listen to
     *                   the observable build with {@link #buildUseCaseObservable(Map)}.
     */
    @SuppressWarnings("unchecked")
    public void execute(Map<String, String> params, Observer observer, Scheduler observeThread) {
        this.subscription = this.buildUseCaseObservable(params)
                .observeOn(observeThread)
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    /**
     * Unsubscribes from current {@link Subscription}.
     */
    public void unSubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
