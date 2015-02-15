package com.jwd.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static int getNum(String str)
	{
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(str);   
		String strNum=m.replaceAll("");
       return Integer.parseInt(strNum);
	}
	
}
