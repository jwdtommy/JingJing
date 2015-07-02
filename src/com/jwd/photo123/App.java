package com.jwd.photo123;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jwd.util.BFImageCache;
import com.jwd.util.MLog;

public class App extends Application {

	public static App instance = null;
	public static RequestQueue queue;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		queue = Volley.newRequestQueue(this);
		BFImageCache.getInstance().initilize(this);
		MLog.i("APP onCreate()..");
	}

	public static App getInstance() {
		if (instance == null) {
			throw new NullPointerException(
					"app not create should be terminated!");
		}
		return instance;
	}
}
