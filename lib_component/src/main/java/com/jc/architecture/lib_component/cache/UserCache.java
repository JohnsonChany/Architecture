package com.jc.architecture.lib_component.cache;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jc.architecture.lib_component.utils.SPUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by JC on 2016/4/19.
 * 用户信息缓存
 */
@Singleton
public class UserCache {

    private static final String FILE_NAME = "user";
    private static final String K_UUID = "K_UUID";
    private static final String K_NICKNAME = "K_NICKNAME";

    private Context context;

    private String uuid;
    private String nickname;

    @Inject
    public UserCache(Context context) {
        this.context = context;
    }

    // TODO: 2016/4/19 用于登陆后初始化，参数待完善
    /**
     * 用于登陆成功后初始化用户缓存，同时存入SP文件，避免后续使用重复读取文件
     */
    public void initByLogin(String uuid, String nickname) {
        this.uuid = uuid;
        setUUID(uuid);
        this.nickname = nickname;
        setNickname(nickname);
    }

    // TODO: 2016/4/19 参数待完善
    /**
     * 用于引导页从SP文件加载用户缓存，避免后续使用重复读取文件
     */
    public void initBySP() {
        this.uuid = (String) SPUtil.get(context, FILE_NAME, K_UUID, "");
        this.nickname = (String) SPUtil.get(context, FILE_NAME, K_NICKNAME, "");
    }

    public void setUUID(@NonNull String uuid) {
        this.uuid = uuid;
        SPUtil.set(context, FILE_NAME, K_UUID, uuid);
    }

    public String getUUID() {
        // 如果内存中为null，则尝试从SP文件加载
        if(this.uuid == null) {
            initBySP();
        }
        return this.uuid;
    }

    public void setNickname(@NonNull String nickname) {
        this.nickname = nickname;
        SPUtil.set(context, FILE_NAME, K_NICKNAME, uuid);
    }

    public String getNickname() {
        if(this.nickname == null) {
            initBySP();
        }
        return this.nickname;
    }
}
