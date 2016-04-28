package com.jc.architecture.lib_component.exception;

/**
 * Created by Jc on 2016/2/19.
 * 包装与服务器交互的所有异常
 */
public class ApiException extends Exception {
    // 对应HTTP的状态码
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;
    // data empty
    public static final int EMPTY_DATA = 203;
    // not login
    public static final int NOT_LOGIN = 404;
    // 连接异常
    public static final int CONNECT_ERROR = 1000;
    // 解析异常
    public static final int PARSE_ERROR = 1001;
    // 未知异常
    public static final int UNKNOWN = 1002;

    private final int code;
    private String displayMessage;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    public String getDisplayMessage() {
        return displayMessage;
    }
    public void setDisplayMessage(String msg) {
//        this.displayMessage = msg + "(code:" + code + ")";
        this.displayMessage = msg;
    }
}
