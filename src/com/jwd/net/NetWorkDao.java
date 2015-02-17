package com.jwd.net;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.util.Log;

import com.jwd.model.LoginBean;
import com.jwd.model.MessageBean;
import com.jwd.util.StringUtil;
import com.jwd.util.UrlUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NetWorkDao {
	private Context context;
	private String url;
	private HttpUtils httpUtil;
	private Object[] params;
	private HttpMethod httpMethod;

	public NetWorkDao(Context context, int tag, Object... params) {
		this.context = context;
		this.params = params;
		httpUtil = new HttpUtils();
		httpUtil.configTimeout(200000);
		setHttpMethod(tag);
		setUrl(tag, params);
	}

	public void setUrl(int tag, Object... params) {
		NetEnity netEnity = NetEnity.getNetEnity(tag);
		String temp_url = netEnity.getUrl();
		if (netEnity.getHttpMethod() == NetEnity.HTTP_METHOD_GET) {
			if (params != null) {
				temp_url = UrlUtil.formatUrl(temp_url, params);
			}
		}
		url = temp_url;
		Log.i("jwd", "url=" + url);
	}

	public void setHttpMethod(int tag) {
		switch (NetEnity.getHttpMethod(tag)) {
		case NetEnity.HTTP_METHOD_GET:
			httpMethod = HttpMethod.GET;
		case NetEnity.HTTP_METHOD_POST:
			httpMethod = HttpMethod.POST;
			break;
		default:
			httpMethod = HttpMethod.GET;
			break;
		}

	}

	public String getUrl() {
		return url;
	}

	public String getHtml(Object[] params) {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter(new BasicNameValuePair("page", params[0].toString()));
		try {
			return httpUtil.sendSync(httpMethod, url, rp).readString();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public ArrayList<MessageBean> getMessages() {
		ArrayList<MessageBean> al = new ArrayList<MessageBean>();
		// httpUtil.configUserAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
		httpUtil.configUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0");
		ResponseStream rs;
		try {
			rs = httpUtil.sendSync(HttpMethod.GET, url);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("jwd", "HttpException:" + e.getMessage());
			return null;
		}
		String html;
		try {
			html = rs.readString();
			Log.i("jwd", "html:" + html);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("jwd", "IOException:" + e.getMessage());
			return null;
		}
		Document doc = Jsoup.parse(html);
		/*
		 * Connection conn = Jsoup.connect(url); conn.timeout(20000);
		 * conn.ignoreHttpErrors(true);
		 * conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
		 * Document doc = null; try { doc = conn.get(); } catch (IOException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); return
		 * null; }
		 */
		Log.i("jwd", "data:" + doc.data());
		Elements elements = doc.getElementsByClass("infobox");
		for (Element element : elements) {
			String id = element.getElementById("dateleft").text()
					.replace("#", "").trim();
			Log.i("jwd", "id:" + id);
			String content = element.getElementsByClass("c").get(0).text();
			Log.i("jwd", "content:" + content);
			String str_yesNum = element.getElementsByClass("voteyes").get(0)
					.getElementsByTag("font").text();
			int yesNum = StringUtil.getNum(str_yesNum);
			Log.i("jwd", "�ҳ���������ӹ��ٵ���" + yesNum);
			String str_noNum = element.getElementsByClass("voteno").get(0)
					.getElementsByTag("font").text();
			int noNum = StringUtil.getNum(str_noNum);
			Log.i("jwd", "��Ѿ��ã�" + noNum);
			String dateAndName = element.getElementById("dateright").ownText()
					.replace(" ", "")
					.replaceAll("<br\\s*/?>|<p\\s*/?>|[\\s\\n]", "").trim();
			String date = dateAndName.substring(0, 10);
			String username = dateAndName.substring(10);
			// String name=dateAndName.split("mm/dd/yyyy")[1];
			Log.i("jwd", "���ڣ�" + date);
			Log.i("jwd", "����:" + username);
			MessageBean message = new MessageBean();
			message.setId(id);
			message.setContent(content);
			message.setDate(date);
			message.setAgreeCount(yesNum);
			message.setDenyCount(noNum);
			message.setUserName(username);
			al.add(message);
		}
		return al;
	}

	public boolean voteYes() {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("id", params[0] + "");
		rp.addBodyParameter("cm", params[1] + "");
		try {
			ResponseStream stream = httpUtil.sendSync(httpMethod, url, rp);
			try {
				if ("2".equals(stream.readString())) {
					return true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean voteNo() {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("id", params[0] + "");
		rp.addBodyParameter("cm", params[1] + "");
		try {
			ResponseStream stream = httpUtil.sendSync(httpMethod, url, rp);
			try {
				if ("2".equals(stream.readString())) {
					return true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean register() {
		RequestParams rp = new RequestParams();
		// reg_email=jwdtommy6%40qq.com&reg_password=123456&reg_nickname=%E5%95%8A%E5%86%AC6
		rp.addBodyParameter("reg_email", params[0] + "");
		rp.addBodyParameter("reg_password", params[1] + "");
		rp.addBodyParameter("reg_nickname", params[2] + "");
		try {
			ResponseStream stream = httpUtil.sendSync(httpMethod, url, rp);
			try {
				if ("2".equals(stream.readString())) {
					return true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public LoginBean login() {
		RequestParams rp = new RequestParams();
		// reg_email=jwdtommy6%40qq.com&reg_password=123456&reg_nickname=%E5%95%8A%E5%86%AC6
		httpUtil.configUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0");
		rp.addBodyParameter("referer", "http://www.caoegg.cn/");
		rp.addBodyParameter("login_email", params[0] + "");
		rp.addBodyParameter("login_password", params[1] + "");
		rp.addHeader("X-Requested-With", "XMLHttpRequest");
		rp.addHeader("Accept", "*/*");
		rp.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		rp.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		rp.addHeader("Origin", "http://www.caoegg.cn");
		rp.addHeader("Referer", "http://www.caoegg.cn/login.php");
		try {
			ResponseStream stream = httpUtil.sendSync(httpMethod, url, rp);
			String result;
			try {
				result = stream.readString();
				Log.i("jwd", "login result=" + result);
				if (result.contains("www.caoegg.cn")) {
					return new LoginBean(true, params[0] + "", "");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return new LoginBean(false, "", "");
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new LoginBean(false, "", "");
		}
		return new LoginBean(false, "", "");
	}
}
