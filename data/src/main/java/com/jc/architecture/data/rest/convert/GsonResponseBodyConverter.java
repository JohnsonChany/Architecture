package com.jc.architecture.data.rest.convert;

import com.jc.architecture.data.model.BaseResp;
import com.jc.architecture.lib_component.exception.ResultException;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 拷贝自{@link retrofit2.converter.gson.GsonResponseBodyConverter}，用于自定义处理约定的响应异常
 *
 * @param <T>
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            T t = adapter.fromJson(value.charStream());
            int code = ((BaseResp) t).getCode();
            if (200 == code) {
                // 此处不能再重新解析一遍，会报错EOF啥啥的错误
                return t;
            } else {
                throw new ResultException(code, ((BaseResp) t).getMsg());
            }
        } finally {
            value.close();
        }
    }
}
