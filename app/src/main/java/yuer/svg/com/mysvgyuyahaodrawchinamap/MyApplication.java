package yuer.svg.com.mysvgyuyahaodrawchinamap;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuer on 2018/6/21.
 */

public class MyApplication extends Application {

    public static final boolean DEBUG =  true;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
