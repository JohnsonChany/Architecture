package com.jc.architecture.lib_component.rest;

import android.net.ParseException;

import com.jc.architecture.lib_component.exception.ApiException;
import com.jc.architecture.lib_component.exception.ResultException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 请求结果
 */
public abstract class BaseRestSubscriber<T> extends Subscriber<T> {

    //出错提示
    private final String networkMsg;
    private final String parseMsg;
    private final String unknownMsg;

    public BaseRestSubscriber() {
        this.networkMsg = "连接异常，请检查网络";
        this.parseMsg = "数据解析异常";
        this.unknownMsg = "未知异常";
    }

    @Override
    public void onError(Throwable e) {
        Throwable throwable = e;
        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }

        ApiException ex;
        if(e instanceof ConnectException
                || e instanceof SocketException) {
            ex = new ApiException(e, ApiException.CONNECT_ERROR);
            ex.setDisplayMessage(networkMsg);
            onError(ex);
        }else if (e instanceof HttpException){             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code());
            switch(httpException.code()){
                case ApiException.UNAUTHORIZED:
                case ApiException.FORBIDDEN:
//                    onPermissionError(ex);          //权限错误，需要实现
//                    break;
                case ApiException.NOT_FOUND:
                case ApiException.REQUEST_TIMEOUT:
                case ApiException.GATEWAY_TIMEOUT:
                case ApiException.INTERNAL_SERVER_ERROR:
                case ApiException.BAD_GATEWAY:
                case ApiException.SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage(networkMsg);  //均视为网络错误
                    onError(ex);
                    break;
            }
        } else if (e instanceof ResultException){    //服务器返回的错误
            switch (((ResultException) e).getErrCode()) {
                case ApiException.EMPTY_DATA:
                    onEmpty();
                    break;
                default:
                    ResultException resultException = (ResultException) e;
                    ex = new ApiException(resultException, resultException.getErrCode());
                    ex.setDisplayMessage(resultException.getMessage());
                    onError(ex);
                    break;
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new ApiException(e, ApiException.PARSE_ERROR);
            ex.setDisplayMessage(parseMsg);            //均视为解析错误
            onError(ex);
        } else {
            ex = new ApiException(e, ApiException.UNKNOWN);
            ex.setDisplayMessage(unknownMsg);          //未知错误
            onError(ex);
        }
    }

    @Override
    public abstract void onNext(T t);
    /**
     * 空数据回调
     */
    protected void onEmpty() {}
    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
    /**
     * 权限错误，需要实现提示打开权限
     */
    protected abstract void onPermissionError(ApiException ex);

    @Override
    public void onCompleted() {
        if (!isUnsubscribed()) {
            unsubscribe();
        }
    }
}