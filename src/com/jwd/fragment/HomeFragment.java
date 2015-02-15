package com.jwd.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jwd.adapter.MyAdapter;
import com.jwd.base.IBindData;
import com.jwd.net.NetEnity;
import com.jwd.net.NetWorkTask;
import com.jwd.photo123.App;

@SuppressLint("ValidFragment")
public class HomeFragment extends BaseListFragment {
	private int flag;
	// HomeAdapter adapter;
	MyAdapter adapter;
	private final int PAGE_START = 1;
	private int curPage = 1;

	private boolean isLoading;

	public HomeFragment(int flag) {
		this.flag = flag;
	}

	public HomeFragment() {

	}

	@Override
	public void onShowView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// adapter = new HomeAdapter(this);
		adapter = new MyAdapter(this);
		loadData(flag, true, curPage, false);
	}

	public void loadData(int flag, boolean isUseCache, int page,
			boolean isRefresh) {
		if (isLoading) {
			return;
		}
		if (isUseCache) {
			if (App.getInstance().getMessages(flag + "") != null) {
				notifyDataChanged(flag, App.getInstance()
						.getMessages(flag + ""), isRefresh);
				return;
			}
		}
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				sLayout.setRefreshing(true);
			}
		}, 100);

		if (isRefresh) {
			switch (flag) {
			case 0:
				new NetWorkTask(this,
						NetEnity.NET_TAG_GET_MESSAGE_LATEST_REFRESH, page)
						.startLoading();
				break;
			case 1:
				new NetWorkTask(this,
						NetEnity.NET_TAG_GET_MESSAGE_FLOP_WEEK_REFRESH, page)
						.startLoading();
				break;
			case 2:
				new NetWorkTask(this,
						NetEnity.NET_TAG_GET_MESSAGE_TOP_WEEK_REFRESH, page)
						.startLoading();
				break;
			default:
				break;
			}
		} else {
			switch (flag) {
			case 0:
				new NetWorkTask(this, NetEnity.NET_TAG_GET_MESSAGE_LATEST, page)
						.startLoading();
				break;
			case 1:
				new NetWorkTask(this, NetEnity.NET_TAG_GET_MESSAGE_FLOP_WEEK,
						page).startLoading();
				break;
			case 2:
				new NetWorkTask(this, NetEnity.NET_TAG_GET_MESSAGE_TOP_WEEK,
						page).startLoading();
				break;
			default:
				break;
			}
		}
		isLoading = true;
	}

	@Override
	public void bindData(int code, int tag, Object data) {
		// TODO Auto-generated method stub
		Log.i("jwd", "code=" + code + "");
		isLoading = false;
		switch (tag) {
		case NetEnity.NET_TAG_GET_MESSAGE_LATEST:
		case NetEnity.NET_TAG_GET_MESSAGE_FLOP_WEEK:
		case NetEnity.NET_TAG_GET_MESSAGE_TOP_WEEK:
			getMessageResponse(code, data, false);
			break;
		case NetEnity.NET_TAG_GET_MESSAGE_LATEST_REFRESH:
		case NetEnity.NET_TAG_GET_MESSAGE_FLOP_WEEK_REFRESH:
		case NetEnity.NET_TAG_GET_MESSAGE_TOP_WEEK_REFRESH:
			getMessageResponse(code, data, true);
			break;
		case NetEnity.NET_TAG_VOTE_YES:
			// voteYesResponse(code, data);
			break;
		case NetEnity.NET_TAG_VOTE_NO:
			// voteNoResponse(code, data);
			break;
		}
	}

	public void getMessageResponse(int code, Object data, boolean isRefresh) {
		if (sLayout != null && sLayout.isRefreshing()) {
			sLayout.setRefreshing(false);
		}
		if (code == IBindData.RESPONSE_SUCCESS) {
			notifyDataChanged(flag, data, isRefresh);
		}
	}

	private void voteYesResponse(int code, Object data) {
		if (code == IBindData.RESPONSE_SUCCESS) {
			notifyDataChanged(flag, data, true);
		}
	}

	private void voteNoResponse(int code, Object data) {
		if (code == IBindData.RESPONSE_SUCCESS) {
			notifyDataChanged(flag, data, true);
		}
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		loadData(flag, false, ++curPage, false);
	}

	@Override
	public void onError(int code, String massage) {
		// TODO Auto-generated method stub
		switch (code) {
		case IBindData.RESPONSE_ERROR_DATA:
			Toast.makeText(mActivity, "暂无数据", 2000).show();
			break;
		case IBindData.RESPONSE_ERROR_NETWORK:
			Toast.makeText(mActivity, "网络不给力哦！", 2000).show();
			break;
		case IBindData.RESPONSE_ERROR_NULL:
			break;
		default:
			break;
		}
	}

	@Override
	public void onReFresh() {
		// TODO Auto-generated method stub
		curPage = 0;
		loadData(flag, false, curPage, true);
	}

	public void notifyDataChanged(int flag, Object data, boolean isRefresh) {
		if (recyclerView.getAdapter() == null) {
			recyclerView.setAdapter(adapter);
		}
		if (isRefresh) {
			adapter.cleanData();
			adapter.addData(flag + "", data);
		} else {
			adapter.addData(flag + "", data);
		}
	}
}
