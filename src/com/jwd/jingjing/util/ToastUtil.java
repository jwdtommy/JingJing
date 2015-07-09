package com.jwd.jingjing.util;

import com.jwd.jingjing.activity.App;

import android.widget.Toast;

public class ToastUtil {
	public static final boolean CAN_SHOW = true;

	public static void show(String text) {
		if (CAN_SHOW) {
			Toast.makeText(App.getInstance().getApplicationContext(), text,
					Toast.LENGTH_LONG).show();
		}
	}
}
