package com.jc.architecture.data.model;

import com.jc.architecture.data.entity.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by JC on 2016/4/20.
 */
public class LoginDataModel implements Serializable {

    @SerializedName("UUID")
    private String UUID;

    @SerializedName("user")
    private User user;

    public String getUUID() {
        return UUID;
    }

    public User getUser() {
        return user;
    }
}
