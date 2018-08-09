package yuer.svg.com.mysvgyuyahaodrawchinamap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import yuer.svg.com.mysvgyuyahaodrawchinamap.view.ChaneseAllMapView;

/**
 * 类功能描述：</br>
 *  SVG打造一个中国地图
 * @author 于亚豪
 *  公众号：终端研发部
 * 博客地址：https://blog.csdn.net/androidstarjack
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class MainActivity extends AppCompatActivity {
    private ChaneseAllMapView chaneseAllMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        chaneseAllMapView = findViewById(R.id.chaneseAllMapView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chaneseAllMapView.onDestory();
    }
}
