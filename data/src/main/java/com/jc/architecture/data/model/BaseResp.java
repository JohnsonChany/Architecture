package com.jc.architecture.data.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;

/**
 * Created by JC on 2016/4/13.
 * 返回Json基本解析类
 */
public class BaseResp implements Serializable {

    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private JsonElement data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object parseData(Gson gson, TypeToken typeToken) {
        return gson.fromJson(this.data, typeToken.getType());
    }
}
