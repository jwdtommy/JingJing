package com.jwd.jingjing.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

public class UrlUtil {
	public static String formatUrl(String url,Object... params)
	{
		
		if(TextUtils.isEmpty(url))
		{
			return "";
		}
        Matcher m=Pattern.compile("\\{(\\d)\\}").matcher(url);
        while(m.find()){
        	url=url.replace(m.group(),params[Integer.parseInt(m.group(1))]+"");
        }
        return url;
	}

}
