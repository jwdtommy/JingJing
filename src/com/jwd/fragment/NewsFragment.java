package com.jwd.fragment;

import java.util.ArrayList;

import com.jwd.adapter.NewsListAdapter;
import com.jwd.manager.JokeListManager;
import com.jwd.model.NewsItem;
import com.jwd.presenter.INewsListView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class NewsFragment extends BaseListFragment implements INewsListView {
	private JokeListManager manager;
	private NewsListAdapter adapter;

	public NewsFragment(int position) {

	}

	@Override
	public void onShowView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		adapter = new NewsListAdapter(this);
		manager = new JokeListManager(this);
		manager.excute(null);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReFresh() {
		// TODO Auto-generated method stub
		manager.loadWeb(null);
	}

	@Override
	public void showList(ArrayList<NewsItem> items) {
		// TODO Auto-generated method stub
		if (recyclerView.getAdapter() == null) {
			recyclerView.setAdapter(adapter);
		}

		adapter.refreshData(items);
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
