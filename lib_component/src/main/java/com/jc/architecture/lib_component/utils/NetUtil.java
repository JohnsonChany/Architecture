package com.jc.architecture.lib_component.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/**
 * 网络工具类，检查手机网络状态
 */
public class NetUtil {

    public static final int NET_WORN_NONE = 0;
    public static final int NET_WORN_WIFI = 1;
    public static final int NET_WORN_MOBILE = 2;

    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            // Wifi
            State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .getState();
            if (state == State.CONNECTED || state == State.CONNECTING) {
                return NET_WORN_WIFI;
            }
            // 3G
            state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .getState();
            if (state == State.CONNECTED || state == State.CONNECTING) {
                return NET_WORN_MOBILE;
            }
        } else {
            return NET_WORN_NONE;
        }
        return NET_WORN_NONE;
    }
}
