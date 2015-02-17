package com.jwd.net;

import java.util.HashMap;

public class NetEnity {
	public static final String server = "http://www.caoegg.cn";

	public static final int HTTP_METHOD_GET = 1;
	public static final int HTTP_METHOD_POST = 2;
	public static final int HTTP_METHOD_NONE = 3;

	public static final int NET_TAG_GET_MESSAGE_LATEST = 1001;// ����
	public static final int NET_TAG_GET_MESSAGE_FLOP_WEEK = 1002;// ��˥(ÿ��)
	public static final int NET_TAG_GET_MESSAGE_TOP_WEEK = 1003;// ���(ÿ��)
	public static final int NET_TAG_GET_MESSAGE_LATEST_REFRESH = 1004;// ����
	public static final int NET_TAG_GET_MESSAGE_FLOP_WEEK_REFRESH = 1005;// ��˥(ÿ��)
	public static final int NET_TAG_GET_MESSAGE_TOP_WEEK_REFRESH = 1006;// ���(ÿ��)
	public static final int NET_TAG_VOTE_YES = 1007;
	public static final int NET_TAG_VOTE_NO = 1008;
	public static final int Net_TAG_REGISTER = 1009;
	public static final int Net_TAG_LOGIN = 1010;

	private int tag;
	private String url;
	private int httpMethod;
	public static HashMap<Integer, NetEnity> netMap = new HashMap<Integer, NetEnity>();
	static {
		put(NET_TAG_GET_MESSAGE_LATEST, server + "/latest/" + "{0}",
				HTTP_METHOD_GET);
		put(NET_TAG_GET_MESSAGE_FLOP_WEEK, server + "/flop/week/" + "{0}",
				HTTP_METHOD_GET);
		put(NET_TAG_GET_MESSAGE_TOP_WEEK, server + "/top/week/" + "{0}",
				HTTP_METHOD_GET);
		put(NET_TAG_GET_MESSAGE_LATEST_REFRESH, server + "/latest/" + "{0}",
				HTTP_METHOD_GET);
		put(NET_TAG_GET_MESSAGE_FLOP_WEEK_REFRESH, server + "/flop/week/"
				+ "{0}", HTTP_METHOD_GET);
		put(NET_TAG_GET_MESSAGE_TOP_WEEK_REFRESH,
				server + "/top/week/" + "{0}", HTTP_METHOD_GET);

		put(NET_TAG_VOTE_YES, server + "/vote_yes.php", HTTP_METHOD_POST);
		put(NET_TAG_VOTE_NO, server + "/vote_no.php", HTTP_METHOD_POST);
		put(Net_TAG_REGISTER, server + "/action/act_register", HTTP_METHOD_POST);
		put(Net_TAG_LOGIN, server + "/action/act_login", HTTP_METHOD_POST);

	}

	public NetEnity(int tag, String url, int httpMethod) {
		this.tag = tag;
		this.url = url;
		this.httpMethod = httpMethod;
	}

	public static void put(int tag, String url, int httpMethod) {
		netMap.put(tag, new NetEnity(tag, url, httpMethod));
	}

	public static NetEnity getNetEnity(int tag) {
		return netMap.get(tag);
	}

	public static int getHttpMethod(int tag) {
		return netMap.get(tag).getHttpMethod();
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(int httpMethod) {
		this.httpMethod = httpMethod;
	}
}
