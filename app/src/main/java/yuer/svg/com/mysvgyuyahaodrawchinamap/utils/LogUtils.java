package yuer.svg.com.mysvgyuyahaodrawchinamap.utils;

import android.util.Log;

import yuer.svg.com.mysvgyuyahaodrawchinamap.MyApplication;


/**
 * 类名LogUtils
 * 类描述： 【打印Log工具】
 * 创建人：yuyahao
 * 创建时间：2017/3/13
 * 修改人：yang
 * 修改时间：2017/3/13
 * 修改备注：
 */
public class LogUtils {

    public static final boolean DEBUG = MyApplication.DEBUG;

    public static void d(String tag, String desc) {
        if (DEBUG)
            Log.d(tag, desc);
    }

    public static void d(String tag, String desc, Throwable tr) {
        if (DEBUG)
            Log.d(tag, desc, tr);
    }

    public static void v(String tag, String desc) {
        if (DEBUG)
            Log.v(tag, desc);
    }

    public static void v(String tag, String desc, Throwable tr) {
        if (DEBUG)
            Log.v(tag, desc);
    }

    public static void w(String tag, String desc) {
        if (DEBUG)
            Log.w(tag, desc);
    }

    public static void w(String tag, Throwable ioe) {
        if (DEBUG)
            Log.w(tag, ioe);
    }

    public static void w(String tag, String desc, Throwable e) {
        if (DEBUG)
            Log.w(tag, desc, e);
    }

    public static void i(String tag, String desc) {
        if (DEBUG)
            Log.i(tag, desc);
    }

    public static void i(String tag, String desc, Throwable tr) {
        if (DEBUG)
            Log.i(tag, desc, tr);
    }

    public static void e(String tag, String desc) {
        if (DEBUG)
            Log.e(tag, desc);
    }

    public static void e(String tag, String desc, Throwable tr) {
        if (DEBUG)
            Log.e(tag, desc, tr);
    }
    public static   void exception(Exception e) {
        if (DEBUG) {
            e.printStackTrace();
        }
    }

}
