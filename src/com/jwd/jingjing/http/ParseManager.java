package com.jwd.jingjing.http;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jwd.jingjing.model.NewsItem;
import com.jwd.jingjing.util.MLog;

/**
 * 解析管理
 * 
 * @author jiangweidong
 * 
 */
public class ParseManager {
	public static ArrayList<NewsItem> parseNewsItems(String json) {
		MLog.i("json=" + json);
		Gson gson = new Gson();
		JSONObject o = null;
		try {
			o = new JSONObject(json);
			JSONObject aa = o.optJSONObject("showapi_res_body");
			JSONArray bb = aa.optJSONArray("list");
			Type listType = new TypeToken<ArrayList<NewsItem>>() {
			}.getType();
			ArrayList<NewsItem> list = gson.fromJson(bb.toString(), listType);
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
