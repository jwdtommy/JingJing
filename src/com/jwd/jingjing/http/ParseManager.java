package com.jwd.jingjing.http;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jwd.jingjing.model.Girl;
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

	public static ArrayList<Girl> parseGirlsItems(String json) {
		MLog.i("json=" + json);
		Gson gson = new Gson();
		JSONObject o = null;
		try {
			o = new JSONObject(json);
			JSONObject json1 = o.optJSONObject("showapi_res_body");
			Iterator<String> keys = json1.keys();
			ArrayList<Girl> girls = new ArrayList<Girl>();
			while (keys.hasNext()) {
				String key = keys.next();
				if (!key.equals("code") && !key.equals("msg")) {
					JSONObject girlJson = json1.optJSONObject(key);
					String title = girlJson.optString("title");
					String description = girlJson.optString("description");
					String picUrl = girlJson.optString("picUrl");
					String url = girlJson.optString("url");
					Girl girl = new Girl();
					girl.setDescription(description);
					girl.setTitle(title);
					girl.setPicUrl(picUrl);
					girl.setUrl(url);
					girls.add(girl);
				}
			}
			return girls;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
