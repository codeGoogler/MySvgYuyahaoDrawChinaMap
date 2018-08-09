### 前言
来继续学习SVG，要想深入了解还是要多动手进行实战。关于svg基础可以去看一下我的上一篇文章[《SVG前戏—让你的View多姿多彩》](https://mp.weixin.qq.com/s/reLa6yZST7uiP_nRScHWEg),今天就用SVG打造一个精美的UI效果。


#### 正文

先上效果图：

![开启线程池，进行绘制](https://upload-images.jianshu.io/upload_images/4614633-09d1d5b9dfe68d1c.gif?imageMogr2/auto-orient/strip)


![ 选择省份](https://upload-images.jianshu.io/upload_images/4614633-c46bcadadcdc80ac.gif?imageMogr2/auto-orient/strip)


我们都知道SVG的文件是纯粹的 XML。如：

![](https://upload-images.jianshu.io/upload_images/4614633-0df8f53d192baf76.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

 ![ ](https://upload-images.jianshu.io/upload_images/4614633-a2cf8c3aa17442e1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


[图片上传中...(12345.gif-60d52c-1533723732988-0)]

![123.gif](https://upload-images.jianshu.io/upload_images/4614633-4cf8cfd751327f42.gif?imageMogr2/auto-orient/strip)


看到这里，我们都明白了。原来里面类似以path的数据进行组装的。只需要进行解析xml就可以了。

解析关键代码：

![ ](https://upload-images.jianshu.io/upload_images/4614633-f7ce7ff534e2f032.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

关键点：还是在于UI绘制的时候，把解析到的数据进行分片绘制。这里用是path和region互相结合的一个算法。另外刚开始解析数据的时候，我开起了线程池进行绘制刷新的。详细可参考demo！


##### <path/>路径的路径描述指令含义表：

* M = moveto 相当于 android Path 里的moveTo(),用于移动起始点 
* L = lineto 相当于 android Path 里的lineTo()，用于画线 
* H = horizontal lineto 用于画水平线 
* V = vertical lineto 用于画竖直线 
* C = curveto 相当于cubicTo(),三次贝塞尔曲线 
* S = smooth curveto 同样三次贝塞尔曲线，更平滑 
* Q = quadratic Belzier curve quadTo()，二次贝塞尔曲线 
* T = smooth quadratic Belzier curveto 同样二次贝塞尔曲线，更平滑 
* A = elliptical Arc 相当于arcTo()，用于画弧 
* Z = closepath 相当于closeTo(),关闭path

大写代表绝对位置, 小写代表相对位置。


**SVG里面还有各种标签：**

包括line直线，circle圆，rect矩形，eliipse椭圆，polygon多边形，等等
这些只要我们又一个SVG文件，都可以将其转换成java代码


![ ](https://upload-images.jianshu.io/upload_images/4614633-c8438b00c6195358.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![ ](https://upload-images.jianshu.io/upload_images/4614633-868a9cc496eccb21.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


>地图项目地址： 
>
> https://github.com/androidstarjack/MySvgYuyahaoDrawChinaMap
作为一个程序员，我们当然不能手动去做这个工作，那就涉及两个问题，一个是SVG的解析，一个是解析后的绘制。虽然有人已经完成了这个工作，但我觉得还是自己动手敲一遍为好！任何时候不要太过于依赖第三方的。

**备注**：该demo仅为学习Android提供思路用，我很爱国的！
 ![ 该demo仅为学习Android提供思路用，我很爱国的](https://upload-images.jianshu.io/upload_images/4614633-03384da3a20f8937.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### 阅读更多

[**终于，我还是下决心学Java后台了**](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247486120&idx=1&sn=db5f94ad8554bd2739d962b7724033f3&chksm=eb476636dc30ef2072bacb21fa532eae3c9ab9315fac62becfd052ad990857f7d400e6584e72&scene=21#wechat_redirect)

[**谈一下Application和Context**](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247486109&idx=1&sn=44d9866f49f3bda71fd0c73365d1dd10&chksm=eb476603dc30ef153a548cda70a60f797e1bcc106e1e8baae2f5c55644303ed8f24a3a2fb974&scene=21#wechat_redirect)

[**金9银10的面试黄金季节，分享几个重要的面试题**](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247486108&idx=1&sn=b9c1a6fabd0a239b5f04db47d4d05c12&chksm=eb476602dc30ef14004f6aaae370b6c0a9092104b4e87fec80e09441e2062974429d88c05f1c&scene=21#wechat_redirect)

[**谈一下Application和Context**](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247486109&idx=1&sn=44d9866f49f3bda71fd0c73365d1dd10&chksm=eb476603dc30ef153a548cda70a60f797e1bcc106e1e8baae2f5c55644303ed8f24a3a2fb974&scene=21#wechat_redirect)

#### 相信自己，没有做不到的，只有想不到的

[在这里获得的不仅仅是技术！](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247485647&idx=1&sn=e4f8d8e6412b337f565365fe819b37f5&chksm=eb476451dc30ed474976c93beccf2b4b5103b1b68f00e986b38354fdcaa9964b97e805329461&scene=21#wechat_redirect)

![image](http://upload-images.jianshu.io/upload_images/4614633-96aa77eaf8e7e104?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 
