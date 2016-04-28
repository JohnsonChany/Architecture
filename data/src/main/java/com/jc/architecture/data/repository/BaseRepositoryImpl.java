package com.jc.architecture.data.repository;

import com.jc.architecture.lib_component.tools.Log;
import com.google.gson.Gson;

import okhttp3.MediaType;

/**
 * Created by JC on 2016/4/14.
 * domain用例接口实现类
 */
public class BaseRepositoryImpl {

    protected final MediaType STRING_TYPE = MediaType.parse("text/plain");
    protected final MediaType IMAGE_TYPE = MediaType.parse("image/*");

    protected Gson gson;
    protected Log log;

    public BaseRepositoryImpl(Gson gson, Log log) {
        this.gson = gson;
        this.log = log;
    }
}
