package com.jwd.jingjing.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jwd.jingjing.adapter.NewsListAdapter;
import com.jwd.jingjing.manager.NewsListManager;
import com.jwd.jingjing.model.NewsItem;
import com.jwd.jingjing.presenter.INewsListView;

public class NewsFragment extends BaseListFragment implements INewsListView {
	private NewsListManager manager;
	private NewsListAdapter adapter;

	public NewsFragment() {

	}

	@Override
	public void onShowView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		adapter = new NewsListAdapter(this);
		manager = new NewsListManager(this);
		manager.excute(NewsListManager.TAG_NEWSLIST_REFRESH, null);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		manager.loadWeb(NewsListManager.TAG_NEWSLIST_ADD, null);
	}

	@Override
	public void onReFresh() {
		// TODO Auto-generated method stub
		manager.loadWeb(NewsListManager.TAG_NEWSLIST_REFRESH, null);
	}

	@Override
	public void reFreshList(ArrayList<NewsItem> items) {
		// TODO Auto-generated method stub
		if (recyclerView.getAdapter() == null) {
			recyclerView.setAdapter(adapter);
		}
		adapter.refreshData(items);
	}

	@Override
	public void addList(ArrayList<NewsItem> items) {
		// TODO Auto-generated method stub
		if (recyclerView.getAdapter() == null) {
			recyclerView.setAdapter(adapter);
		}
		adapter.addData(items);

	}

	@Override
	public void clearList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hideLoadingView() {
		// TODO Auto-generated method stub
		sLayout.setRefreshing(false);
	}

}
