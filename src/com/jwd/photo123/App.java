package com.jwd.photo123;

import java.util.ArrayList;
import java.util.HashMap;

import com.jwd.model.MessageBean;

import android.app.Application;

public class App extends Application {

	public static App instance = null;
	public static HashMap<String, ArrayList<MessageBean>> mapMessage = new HashMap<String, ArrayList<MessageBean>>();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
	}

	public static App getInstance() {
		if (instance == null) {
			throw new NullPointerException(
					"app not create should be terminated!");
		}
		return instance;
	}

	public void putMessages(String key, ArrayList<MessageBean> messages) {
		if (mapMessage == null) {
			return;
		}
		if (mapMessage.containsKey(key)) {
			mapMessage.remove(key);
		}
		mapMessage.put(key, messages);
	}

	public ArrayList<MessageBean> getMessages(String key) {
		if (mapMessage == null) {
			return null;
		}
		ArrayList<MessageBean> messages = mapMessage.get(key);
		return messages;
	}

}
