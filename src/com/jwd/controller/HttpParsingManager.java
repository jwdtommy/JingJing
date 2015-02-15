package com.jwd.controller;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import android.content.Context;
import android.util.Log;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HttpParsingManager implements IHttpParsingCallback {
	public static HttpParsingManager manager = null;
	public static Context mContext;

	private HttpParsingManager() {

	}

	public static synchronized HttpParsingManager getInstance(Context context) {
		if (manager == null) {
			mContext = context;
			manager = new HttpParsingManager();
		}
		return manager;
	}
	@Override
	public void OnErrorListener(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnSuccessListener(String msg) {
		// TODO Auto-generated method stub

	}

}
