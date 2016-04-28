package com.jc.architecture.lib_component.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences读取
 */
public class SPUtil {
    /**
     * @param context       context
     * @param fileName      文件名
     * @param key           键
     * @param defaultObject 默认值，取不到时返回
     * @return 正常取到则返回，取不到返回defaultObject
     */
    public static Object get(Context context, String fileName, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_APPEND);
        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return defaultObject;
    }

    /**
     * @param context  context
     * @param fileName 文件名
     * @param key      键
     * @param object   要存的值
     */
    public static boolean set(Context context, String fileName, String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(fileName, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }
        return editor.commit();
    }
}
