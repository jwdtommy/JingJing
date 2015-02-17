package com.jwd.net;

import java.util.HashMap;
import java.util.Map;

import com.jwd.base.BaseActivity;
import com.jwd.base.IBindData;
import com.jwd.fragment.BaseFragment;
import com.jwd.util.CommonUtils;
import com.lidroid.xutils.HttpUtils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.view.View;

/**
 * 
 * @author jiangweidong
 * 
 */
public class NetWorkTask extends AsyncTaskLoader<Object> {
	private IBindData iBindData;
	private Context context;
	private int tag = 0;
	private Object[] params;
	HttpUtils httpUtil;
	private boolean needLoading = true;
	private String message;
	private IBindItemListener bindItemListener;
	private View bindView;
	private int position = -1;

	public interface IBindItemListener {
		void onBindItemListener(int position);
	}

	public NetWorkTask(BaseActivity activity, int tag, Object... params) {
		super(activity);
		this.iBindData = activity;
		this.context = activity;
		this.tag = tag;
		this.params = params;
	}

	public NetWorkTask(BaseFragment fragment, int tag, Object... params) {
		super(fragment.getActivity());
		this.iBindData = fragment;
		this.context = fragment.getActivity();
		this.tag = tag;
		this.params = params;
	}

	public NetWorkTask(BaseActivity activity, int tag, int position,
			IBindItemListener listener) {
		super(activity);
		this.iBindData = activity;
		this.context = activity;
		this.bindItemListener = listener;
		this.tag = tag;
		this.position = position;
	}
	
	

	public NetWorkTask setParams(Object... params) {
		this.params = params;
		return this;
	}

	public NetWorkTask setLoading(boolean needLoading) {
		this.needLoading = needLoading;
		return this;
	}

	public NetWorkTask setLoading(boolean needLoading, String message) {
		this.needLoading = needLoading;
		this.message = message;
		return this;
	}

	@Override
	public Map<String, Object> loadInBackground() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if (!CommonUtils.isNetworkConnected()) {
			map.put("isConnected", false);
			return map;
		} else {
			map.put("isConnected", true);
		}
		Object data = null;
		NetWorkDao dao = new NetWorkDao(context, tag, params);
		Log.i("jwd", "tag:" + tag);
		Log.i("jwd", "url:" + dao.getUrl());
		switch (tag) {
		case NetEnity.NET_TAG_GET_MESSAGE_LATEST:
		case NetEnity.NET_TAG_GET_MESSAGE_LATEST_REFRESH:
			data = dao.getMessages();
			break;
		case NetEnity.NET_TAG_GET_MESSAGE_FLOP_WEEK:
		case NetEnity.NET_TAG_GET_MESSAGE_FLOP_WEEK_REFRESH:
			data = dao.getMessages();
			break;
		case NetEnity.NET_TAG_GET_MESSAGE_TOP_WEEK:
		case NetEnity.NET_TAG_GET_MESSAGE_TOP_WEEK_REFRESH:
			data = dao.getMessages();
			break;
		case NetEnity.NET_TAG_VOTE_YES:
			data = dao.voteYes();
			break;
		case NetEnity.NET_TAG_VOTE_NO:
			data = dao.voteNo();
			break;
		case NetEnity.Net_TAG_REGISTER:
			data=dao.register();
			break;
		case NetEnity.Net_TAG_LOGIN:
			data=dao.login();
			break;
		}
		map.put("tag", tag);
		map.put("data", data);
		return map;
	}

	@Override
	public void deliverResult(Object map) {
		// TODO Auto-generated method stub
		super.deliverResult(map);
		if (map == null) {
			iBindData.onError(IBindData.RESPONSE_ERROR_NULL, "");
			return;
		}
		if (((Boolean) (((HashMap) map).get("isConnected")) == false)) {
			iBindData.onError(IBindData.RESPONSE_ERROR_NETWORK, "");
			return;
		}
		if (map != null) {
			int tag = (Integer) ((HashMap) map).get("tag");
			Object data = ((HashMap) map).get("data");
			if (data != null) {
				iBindData.bindData(IBindData.RESPONSE_SUCCESS, tag, data);
				if (position>=0&&bindItemListener!=null) {
					bindItemListener.onBindItemListener(position);
				}
			} else {
				iBindData.onError(IBindData.RESPONSE_ERROR_DATA, "");
			}
		}
	}

	@Override
	protected void onStartLoading() {
		// TODO Auto-generated method stub
		super.onStartLoading();
		forceLoad();
	}

	@Override
	protected void onStopLoading() {
		// TODO Auto-generated method stub
		super.onStopLoading();
	}

}
