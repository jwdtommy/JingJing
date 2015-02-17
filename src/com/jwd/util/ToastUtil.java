package com.jwd.util;

import com.jwd.photo123.App;

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
