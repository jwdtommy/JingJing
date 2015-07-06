package com.jwd.jingjing.manager;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jwd.jingjing.manager.impl.ImplManager;
import com.jwd.jingjing.photo123.App;
import com.jwd.jingjing.presenter.IPresenterView;
import com.jwd.jingjing.util.FileUtils;
import com.jwd.jingjing.util.HttpConnectionUtils;

public abstract class BaseManager implements ImplManager {
	protected String filePath;
	protected String url;
	protected String json;
	protected int httpMethod;

	public BaseManager(IPresenterView iPresenterView) {
		filePath = configFilePath();
		url = configUrl();
	}

	private void loadFile(int tag) {
		try {
			json = (String) FileUtils.unserializeObject(filePath);
			onFileFinish(tag,json);
		} catch (Exception e) {
			onFileError(tag,FileError.NO_DATA);
		}
	}

	private void saveFile(String result) {
		try {
			FileUtils.serializeObject(filePath, result);
		} catch (Exception e) {
		}
	}

	public void loadWeb(final int tag,final HashMap<String, String> params) {
		try {
			if (httpMethod == Method.GET) {
				HttpConnectionUtils.setUrlParameter(url, params);
				System.out.println("loadWeb url=" + url);
			}
			StringRequest request = new StringRequest(httpMethod, url,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							saveFile(response);
							onWebFinish(tag,response);
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							// TODO Auto-generated method stub
							onWebError(tag,WebError.CONNECT_FAIL);
						}

					}) {
				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
					// TODO Auto-generated method stub
					if (httpMethod == Method.POST) {
						return params;
					}
					return super.getParams();
				}
			};
			App.queue.add(request);
		} catch (Exception e) {
			// TODO: handle exception
			onWebError(tag,WebError.CONNECT_FAIL);
		}
	}

	public void excute(int tag,HashMap<String, String> params) {
		loadFile(tag);
		loadWeb(tag,params);
	}
}
