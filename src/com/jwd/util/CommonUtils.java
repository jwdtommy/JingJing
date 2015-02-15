package com.jwd.util;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import com.jwd.photo123.App;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class CommonUtils {

	public static int MOBILE_TYPE_CHINA_MOBILE = 1000;
	public static int MOBILE_TYPE_CHINA_UNION_COM = 1001;
	public static int MOBILE_TYPE_CHINA_TELE_COM = 1002;

	public static boolean isSupportSinaSSO(Context context) {
		Intent intent = new Intent("com.sina.weibo.remotessoservice");
		List<ResolveInfo> list = context.getPackageManager()
				.queryIntentServices(intent, PackageManager.GET_INTENT_FILTERS);
		return list.size() > 0;
	}

	public static synchronized boolean isNetworkConnected() {
		boolean isConnected = false;
		Context context = App.getInstance();
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
		} else {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						isConnected = true;
					}
				}
			}
		}
		return isConnected;
	}

	public static synchronized boolean isWifiConnected() {
		ConnectivityManager connManager = (ConnectivityManager) App
				.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
			if (networkInfo != null) {
				int networkInfoType = networkInfo.getType();
				/*
				 * if (networkInfoType == ConnectivityManager.TYPE_WIFI ||
				 * networkInfoType == ConnectivityManager.TYPE_ETHERNET) {
				 * return networkInfo.isConnected(); }
				 */
				if (networkInfoType == ConnectivityManager.TYPE_WIFI) {
					return networkInfo.isConnected();
				}
			}
		}
		return false;
	}

	public static boolean isMobileNetworkConnected() {
		ConnectivityManager connManager = (ConnectivityManager) App
				.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
			if (networkInfo != null) {
				int networkInfoType = networkInfo.getType();
				if (networkInfoType == ConnectivityManager.TYPE_MOBILE) {
					return networkInfo.isConnected();
				}
			}
		}
		return false;
	}

	public static boolean isAllowOfflineDownload(int networkType) {
		switch (networkType) {
		case ConnectivityManager.TYPE_ETHERNET:
		case ConnectivityManager.TYPE_WIFI:
			return true;
		default:
			return false;
		}
	}

	public static int getCurrentVersionCode() {
		try {
			App app = App.getInstance();
			PackageManager packageManager = app.getPackageManager();
			String packageName = app.getPackageName();
			PackageInfo info = packageManager.getPackageInfo(packageName, 0);
			return info.versionCode;
		} catch (Exception e) {
		}
		return 1;
	}

	public static void hideKeyBoard(Activity activity) {
		if (activity.getCurrentFocus() != null) {
			((InputMethodManager) activity
					.getSystemService(Activity.INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(activity.getCurrentFocus()
							.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public static void setFullscreen(Activity activity, boolean on) {
		if (activity != null) {
			Window win = activity.getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
			if (on) {
				winParams.flags |= bits;
			} else {
				winParams.flags &= ~bits;
			}
			win.setAttributes(winParams);
		}
	}

	public static void setTranslucentStatus(Activity activity, boolean on) {
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		// final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		// if (on) {
		// winParams.flags |= bits;
		// } else {
		// winParams.flags &= ~bits;
		// }
		win.setAttributes(winParams);
	}

	public static void setTranslucentNavigation(Activity activity, boolean on) {
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		// final int bits =
		// WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
		// if (on) {
		// winParams.flags |= bits;
		// } else {
		// winParams.flags &= ~bits;
		// }
		win.setAttributes(winParams);
	}

	// 没有2g 3g判断，手机网络统�?��2
	public static int getNetworkType() {
		int type = 0;
		if (isWifiConnected()) {
			type = 1;
		} else if (isMobileNetworkConnected()) {
			type = 2;
		}
		return type;
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return "";
	}

	/**
	 * 获取手机卡类型，移动、联通�?电信
	 * 
	 */
	public static int getMobileType(Context context) {
		int type = 0;
		TelephonyManager iPhoneManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String iNumeric = iPhoneManager.getSimOperator();
		if (iNumeric.length() > 0) {
			if (iNumeric.equals("46000") || iNumeric.equals("46002")) {
				type = MOBILE_TYPE_CHINA_MOBILE;
				// 中国移动
			} else if (iNumeric.equals("46001")) {
				type = MOBILE_TYPE_CHINA_UNION_COM;
				// 中国联�?
			} else if (iNumeric.equals("46003")) {
				type = MOBILE_TYPE_CHINA_TELE_COM;
				// 中国电信
			}
		}
		return type;
	}

	public static boolean isChinaMobile(Context context) {

		if (getMobileType(context) == MOBILE_TYPE_CHINA_MOBILE) {
			return true;
		}
		return false;

	}

	/**
	 * �?��权限 READ_PHONE_STATE
	 * 
	 * Role:获取当前设置的电话号�?<BR>
	 * Date:2014-7-3<BR>
	 * 
	 * @author CODYY)Tommy
	 */
	public static String getNativePhoneNumber(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String NativePhoneNumber = null;
		NativePhoneNumber = telephonyManager.getLine1Number();
		return NativePhoneNumber;
	}

	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存�?
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目�?
			return  sdDir.toString();
		}
		return null;
	}

}
