package com.jwd.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import com.jwd.photo123.App;

public class PreferenceUtils {

	public static final String CONFIG_FILE_NAME = "config";// config.xml

	public static final String USER_NAME = "username";
	public static final String TOKEN = "token";

	// 保存配置key-value
	public static void saveStringPreference(String keyName, String value,
			Context context) {
		Editor editor = context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).edit();
		editor.putString(keyName, value);
		editor.commit();
	}

	/**
	 * 获取配置值
	 * 
	 * @param keyName
	 *            key
	 * @param defValue
	 *            默认值
	 * @param context
	 * @return
	 */
	public static String getStringPreference(String keyName, String defValue,
			Context context) {
		return context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).getString(keyName, defValue);
	}

	/**
	 * 该键值存在且不为空
	 * 
	 * @param keyName
	 * @param context
	 * @return
	 */
	public static boolean isStringExist(String keyName, Context context) {
		String value = getStringPreference(keyName, null, context);
		return value != null && value.length() != 0;
	}

	// 获取布尔类型，默认值false
	public static boolean getBoolPreference(String keyName, Context context) {
		return context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).getBoolean(keyName, false);
	}

	// 获取布尔类型，自定义默认值
	public static boolean getBoolPreference(String keyName,
			boolean defaultValue, Context context) {
		return context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).getBoolean(keyName, defaultValue);
	}

	// 保存布尔类型
	public static void saveBoolPreference(String keyName, boolean value,
			Context context) {
		Editor editor = context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).edit();
		editor.putBoolean(keyName, value);
		editor.commit();
	}

	// 获取浮点类型，自定义默认值
	public static float getFloatPreference(String keyName, float defautValue,
			Context context) {
		return context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).getFloat(keyName, defautValue);
	}

	// 保存浮点类型
	public static void saveFloatPreference(String keyName, float value,
			Context context) {
		Editor editor = context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).edit();
		editor.putFloat(keyName, value);
		editor.commit();
	}

	// 获取int类型，自定义默认值
	public static int getIntPreference(String keyName, int defautValue,
			Context context) {
		return context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).getInt(keyName, defautValue);
	}

	// 获取long类型，......
	public static long getLongPreference(String keyName, int defautValue,
			Context context) {
		return context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).getLong(keyName, defautValue);
	}

	// 保存long类型
	public static void saveLongPreference(String keyName, long value,
			Context context) {
		Editor editor = context.getSharedPreferences(CONFIG_FILE_NAME,
				Context.MODE_PRIVATE).edit();
		editor.putLong(keyName, value);
		editor.commit();
	}

	// 保存int类型
	public static void saveIntPreference(String keyName, int value) {
		Editor editor = App.getInstance()
				.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE)
				.edit();
		editor.putInt(keyName, value);
		editor.commit();
	}

	// 清除某个配置
	public static void removePreference(String keyName) {
		Editor editor = App.getInstance()
				.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE)
				.edit();
		editor.remove(keyName);
		editor.commit();
	}

	public static void removeAllPreference() {
		Editor editor = App.getInstance()
				.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE)
				.edit();
		editor.clear();
		editor.commit();

	}
}
