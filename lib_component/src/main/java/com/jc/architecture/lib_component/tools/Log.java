package com.jc.architecture.lib_component.tools;

/**
 * Created by Jc on 2016/3/1.
 */
public class Log {

    private boolean debug;

    public Log(boolean debug) {
        this.debug = debug;
    }

    public void i(String tag, String msg) {
        if (debug)
            android.util.Log.i(tag, msg);
    }

    public void e(String tag, String msg) {
        if (debug)
            android.util.Log.e(tag, msg);
    }

    public void d(String tag, String msg) {
        if (debug)
            android.util.Log.d(tag, msg);
    }

    public void v(String tag, String msg) {
        if (debug)
            android.util.Log.v(tag, msg);
    }

    public void w(String tag, String msg) {
        if (debug)
            android.util.Log.w(tag, msg);
    }
}
