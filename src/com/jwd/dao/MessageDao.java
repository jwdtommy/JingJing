package com.jwd.dao;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import android.content.Context;
import android.util.Log;
import com.jwd.model.MessageBean;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MessageDao extends BaseDao{
	
	public static final String URL_MESSAGE="http://www.caoegg.cn/latest";
	private Context context;
	public MessageDao(Context context)
	{
		this.context=context;
	}
	
	
	public ArrayList<MessageBean> getMessages(int type,int page,int number)
	{
    	Log.i("jwd","getMessages()") ;
		ArrayList<MessageBean> al=new ArrayList<MessageBean>();
		
		HttpUtils util = new HttpUtils();
		util.send(HttpMethod.POST, URL_MESSAGE, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("jwd", "onFailure:");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				//Log.i("jwd", "result:" + arg0.result);
				System.out.println(arg0.result);
				Document doc=Jsoup.parse(arg0.result);
				Log.i("jwd","aaaa:"+doc.data());
			}
		});
		return al;
	}
	
	
	/**
	 * ÃÊªªurl÷–±‰¡ø
	 * @param type
	 * @param page
	 * @param number
	 * @return
	 */
	
	private String getUrl(int type,int page,int number)
	{
		String url="";
		
		return url;
	}
	
	
	
	
}
