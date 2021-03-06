package com.jwd.jingjing.activity;

import android.app.Application;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jwd.jingjing.util.BFImageCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * @INFO 谁是静静是集，美女图片、内涵图片，内涵段子、无节操图片于一体的幽默类网站，在这里可以看到极具搞笑的东西。 “美女诱惑”让你瞬间爆棚，
 *       特含“醉图”极度搞笑，锻炼腹肌力量，激发排泄效率。 无节操、无下限、爆笑内容，有效清理体内毒素，帮助维护高端笑点，适合全家每天使用。
 *       纯天然无污染优质原创内容，丰富槽点，给你最刺激的感官享受! 富含不明糟糕物，使用后精神柔滑不干燥，倍感舒爽。
 * @TAG
 * 
 * @author jiangweidong
 * 
 */
public class App extends Application {

	public static App instance = null;
	public static RequestQueue queue;
	public static ImageLoaderConfiguration imageLoaderConfig;
	public static DisplayImageOptions imageDisplayConfig;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		queue = Volley.newRequestQueue(this);
		BFImageCache.getInstance().initilize(this);
		configImageLoader();
	}

	public static App getInstance() {
		if (instance == null) {
			throw new NullPointerException(
					"app not create should be terminated!");
		}
		return instance;
	}

	private void configImageLoader() {
		imageLoaderConfig = new ImageLoaderConfiguration.Builder(this)
				.memoryCacheExtraOptions(480, 800)
				// max width, max height，即保存的每个缓存文件的最大长宽
				.threadPoolSize(3)
				// 线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				// You can pass your own memory cache
				// implementation/你可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
				.build();// 开始构建
		ImageLoader.getInstance().init(imageLoaderConfig);// 全局初始化此配置

		imageDisplayConfig = new DisplayImageOptions.Builder()
		// .showImageOnLoading(R.drawable.ic_launcher) // 设置图片在下载期间显示的图片
		// .showImageForEmptyUri(R.drawable.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
		// .showImageOnFail(R.drawable.ic_launcher) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.ARGB_8888)// 设置图片的解码类型//
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				// .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
				// .displayer(new FadeInBitmapDisplayer(1000))// 是否图片加载好后渐入的动画时间
				.build();// 构建完成
	}

}
