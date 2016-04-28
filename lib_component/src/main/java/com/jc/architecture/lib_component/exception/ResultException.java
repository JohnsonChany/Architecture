package com.jc.architecture.lib_component.exception;

/**
 * Created by Jc on 2016/2/19.
 * 包装与服务器约定的响应错误
 */
public class ResultException extends RuntimeException  {

    private int errCode;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}
