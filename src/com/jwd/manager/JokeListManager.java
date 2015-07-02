package com.jwd.manager;

import java.util.ArrayList;

import com.jwd.http.ParseManager;
import com.jwd.model.NewsItem;
import com.jwd.presenter.INewsListView;
import com.jwd.util.FileUtils;

public class JokeListManager extends BaseManager {

	INewsListView iNewsListView;
	private ArrayList<NewsItem> newsItems;

	public JokeListManager(INewsListView iPresenterView) {
		super(iPresenterView);
		this.iNewsListView = iPresenterView;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String configFilePath() {
		// TODO Auto-generated method stub
		return FileUtils.getCollectFilePath();
	}

	@Override
	public String configUrl() {
		// TODO Auto-generated method stub
		return "http://route.showapi.com/107-33?showapi_appid=760&showapi_sign=1BF9176DE6EBA0A99F57A6BAB564AD91&showapi_timestamp=2015-06-16+13%3A19%3A35";
	}

	@Override
	public void onWebFinish(String result) {
		// TODO Auto-generated method stub
		newsItems = ParseManager.parseNewsItems(result);
		iNewsListView.showList(newsItems);
		iNewsListView.hideLoadingView();
	}

	@Override
	public void onWebError(WebError errorCode) {
		// TODO Auto-generated method stub
		iNewsListView.hideLoadingView();
	}

	@Override
	public void onFileFinish(String result) {
		// TODO Auto-generated method stub
		newsItems = ParseManager.parseNewsItems(result);
		iNewsListView.showList(newsItems);
	}

	@Override
	public void onFileError(FileError errorCode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onParse(String json) {
		// TODO Auto-generated method stub

	}

}
