package com.jc.architecture.data.rest;

/**
 * 请求地址
 */
public interface RequestApiPath {
    /**
     * 登录
     */
    String LOGIN= "login/dologin.do";

    /**
     * 注册
     */
    String REGISTER = "index/register.do";

    /**
     * 忘记密码
     */
    String FORGET_PWD = "login/updatePassword.do";

    /**
     * 发送验证码
     */
    String SEND_VERIFY_CODE = "common/sendVerifyCode.do";

    /**
     * 退出登录
     */
    String LOGOUT = "login/doquit.do";


}
