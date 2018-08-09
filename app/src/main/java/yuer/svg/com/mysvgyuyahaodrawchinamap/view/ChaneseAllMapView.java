package yuer.svg.com.mysvgyuyahaodrawchinamap.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import yuer.svg.com.mysvgyuyahaodrawchinamap.MyApplication;
import yuer.svg.com.mysvgyuyahaodrawchinamap.R;
import yuer.svg.com.mysvgyuyahaodrawchinamap.model.ItemProvins;
import yuer.svg.com.mysvgyuyahaodrawchinamap.utils.DensityUtil;
import yuer.svg.com.mysvgyuyahaodrawchinamap.utils.LogUtils;


/**
 * 类功能描述：</br>
 * 打造一个精美的中国地图
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：2018/8/2</br> 修改备注：</br>
 */
public class ChaneseAllMapView extends View {
    private List<ItemProvins> list;
    private RectF rectF ;
    private Paint paint;
    private float scale;
    private  ItemProvins itemProvins = null;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private Paint drawText;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    if(list == null || rectF == null){
                        return;
                    }
                    for (final ItemProvins itemProvins : list) {
                       /* itemProvins.setDrawableColor(Color.parseColor(getRandColorCode()));
                        itemProvins.setNeedDraw(true);*/

                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                itemProvins.setDrawableColor(Color.parseColor(getRandColorCode()));
                                itemProvins.setNeedDraw(true);
                                handler.sendEmptyMessage(200);
                            }
                        });
                    }
                  /*  requestLayout();
                    postInvalidate();*/
                    break;
                case 200:
                    int  numNeedDraw = 0;
                    for (int i = 0 ; i  <list.size() ; i++ ){
                        ItemProvins itemProvins = list.get(i);
                        if(itemProvins.isNeedDraw()){
                            numNeedDraw++;
                        }
                    }
                    LogUtils.e("handleMessage",">>>>>> 111111" + " 需要绘制的集合的大小" + numNeedDraw);
                    requestLayout();
                    postInvalidate();
                    break;
            }
        }
    };

    public ChaneseAllMapView(Context context) {
        this(context,null,0);
    }

    public ChaneseAllMapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChaneseAllMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        list = new ArrayList<>();
        paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        drawText = new Paint();
        drawText.setStrokeWidth(5);
        drawText.setTextSize(16);
        drawText.setColor(Color.WHITE);
        drawText.setAntiAlias(true);
        drawText.setStyle(Paint.Style.FILL);
        parseMap();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int realyWidth = MeasureSpec.getSize(widthMeasureSpec);
        int realyHeight = MeasureSpec.getSize(heightMeasureSpec);
        if(rectF != null){
            float currentWidth = rectF.width();
            scale =realyWidth/currentWidth ;
        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(realyWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(realyHeight, MeasureSpec.EXACTLY));
    }

    //解析svg
    //进行绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.save();
        LogUtils.e("yyh","scale: " + scale + "   集合的大小： "  + list.size());
        canvas.scale(scale,scale);
        try {
            if(list.size() > 0){
                for (ItemProvins itemProvins : list){
                    if(itemProvins.isNeedDraw()){
                        itemProvins.drawChinaMap(canvas,paint,false);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(itemProvins != null){
            itemProvins.drawChinaMap(canvas,paint,true);
        }
        if(rectF != null){
            String noteTip = "中国海域";
            String noteTip2 = "该demo仅为学习android使用，我很爱国的";
            float x = rectF.centerX() - drawText.measureText(noteTip)/2;
            float x2 =rectF.centerX() - drawText.measureText(noteTip2)/2;
            float y = rectF.bottom +50;
            canvas.drawText(noteTip,x,y,drawText);
            canvas.drawText(noteTip2,x2,y+50,drawText);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        handlerToucthOnclick(event.getX(),event.getY());
        return super.onTouchEvent(event);

    }

    private void handlerToucthOnclick(float x, float y) {
        if(list == null){
            return;
        }

        for ( ItemProvins item:list) {
            if(item.handlerToucthOnclick(x/scale,y/scale)){
                itemProvins = item;
                break;
            };
        }
        postInvalidate();
    }

    private void parseMap(){
        new Thread(new Runnable() {
            @SuppressLint("RestrictedApi")
            @Override
            public void run() {
                float left = -1 ;
                float top = -1;
                float right = -1;
                float bottom = -1;
                //数显拿不到资源文件流
                try {
                    InputStream is = getResources().openRawResource(R.raw.china2);
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
                    Document document = builder.parse(is);
                    Element element = document.getDocumentElement();
                    NodeList pathList =  element.getElementsByTagName("path");
                    for (int i = 0; i < pathList.getLength(); i++) {
                        Element itemElement = (Element) pathList.item(i);
                        String pathData = itemElement.getAttribute("d");
                        String provinceName = itemElement.getAttribute("title");
                        Path path = PathParser.createPathFromPathData(pathData);
                        ItemProvins itemProvins = new ItemProvins(path,provinceName);
                        //itemProvins.setProvinceName(provinceName);
                        list.add(itemProvins);
                        RectF rectf = new RectF();
                        path.computeBounds(rectf,true);
                        left = left == -1 ? rectf.left : Math.min(left,rectf.left);
                        top = left == -1 ? rectf.top : Math.min(top,rectf.top);
                        right = left == -1 ? rectf.right : Math.max(right,rectf.right);
                        bottom = left == -1 ? rectf.bottom : Math.max(bottom,rectf.bottom);
                    }
                    //生成一个大的矩形
                    rectF = new RectF(left,top,right,bottom);
                    handler.sendEmptyMessage(100);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void  onDestory(){
        if(handler != null){
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
    /**
     * 获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
     * @return String
     */
    public static String getRandColorCode(){
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();
        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;
//        LogUtils.e("");
        return "#"+r+g+b;
    }


}
