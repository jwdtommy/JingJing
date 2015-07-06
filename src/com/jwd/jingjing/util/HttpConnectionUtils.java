package com.jwd.jingjing.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.text.TextUtils;

/**
 * @author Johnson
 * 
 * */

public class HttpConnectionUtils {

	/**
	 * @author Johnson 设置分隔符
	 */
	private static String addUrlSeparator(String str) {
		if (!TextUtils.isEmpty(str)) {
			int a = str.indexOf("?");
			if (a != -1) {
				str = str + "&";
			} else {
				str = str + "?";
			}
		}
		return str;
	}

	/**
	 * @author Johnson 动态的向URL添加参数
	 */
	private static String setUrlParameter(String str, String key, String value) {
		if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(key)) {
			str = addUrlSeparator(str) + key + "=" + value;
		}
		return str;
	}

	/**
	 * @author Johnson 动态的向URL添加参数
	 */
	public static String setUrlParameter(String url, Map<String, String> map) {
		if (!TextUtils.isEmpty(url)) {
			if (map != null && map.size() > 0) {

				Set<String> set = map.keySet();
				Iterator<String> iterator = set.iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					String value = map.get(key);
					url = setUrlParameter(url, key, value);
				}
			}
		}
		return url;
	}

}
