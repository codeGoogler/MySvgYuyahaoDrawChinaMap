package yuer.svg.com.mysvgyuyahaodrawchinamap.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;


import java.lang.reflect.Field;

import yuer.svg.com.mysvgyuyahaodrawchinamap.MyApplication;

public class DensityUtil {
    // 状态栏高度
    private static  int statusBarHeight = 0;
    // 屏幕像素点
    private static final Point screenSize = new Point();
    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dip2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }


    public static float getScreeFloatnWidth(Context context) {
        WindowManager wm = (WindowManager) context .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
    public static int getScreenIntWidth(Context context) {
        WindowManager wm = (WindowManager) context .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    public static int getScreenIntHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
    public static float getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
    public static int getWeightFrom(int weight){
        return getScreenIntWidth(MyApplication.context)/weight;
    }


    // 获取屏幕像素点
    public static Point getScreenSize(Activity context) {

        if (context == null) {
            return screenSize;
        }
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            Display diplay = wm.getDefaultDisplay();
            if (diplay != null) {
                diplay.getMetrics(mDisplayMetrics);
                int W = mDisplayMetrics.widthPixels;
                int H = mDisplayMetrics.heightPixels;
                if (W * H > 0 && (W > screenSize.x || H > screenSize.y)) {
                    screenSize.set(W, H);
                }
            }
        }
        return screenSize;
    }

    // 获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        if (statusBarHeight <= 0) {
            Rect frame = new Rect();
            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            statusBarHeight = frame.top;
        }
        if (statusBarHeight <= 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = context.getResources().getDimensionPixelSize(x);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return statusBarHeight;
    }
    /**
     * 获取底部navigationBar高度
     * @return
     */
    public static int getNavigationBarHeight() {
        Resources resources = MyApplication.context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        LogUtils.v("dbw", "Navi height:" + height);
        return height;
    }
}  