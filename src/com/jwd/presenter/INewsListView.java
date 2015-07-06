package com.jwd.presenter;

import java.util.ArrayList;

import com.jwd.model.NewsItem;

public interface INewsListView extends IPresenterView {
	public void reFreshList(ArrayList<NewsItem> items);

	public void addList(ArrayList<NewsItem> items);

	public void clearList();

	public void hideLoadingView();
}
