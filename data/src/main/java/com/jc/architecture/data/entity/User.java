package com.jc.architecture.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by JC on 2016/4/13.
 * 用户类
 */
public class User implements Serializable {

    @SerializedName("nickname")
    private String nickname;

    public String getNickname() {
        return nickname;
    }
}
