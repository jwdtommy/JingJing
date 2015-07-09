package com.jwd.jingjing.manager;

import java.util.ArrayList;

import com.jwd.jingjing.http.ParseManager;
import com.jwd.jingjing.model.NewsItem;
import com.jwd.jingjing.presenter.INewsListView;
import com.jwd.jingjing.util.FileUtils;

public class NewsListManager extends BaseManager {

	public static final int TAG_NEWSLIST_REFRESH = 0X1;
	public static final int TAG_NEWSLIST_ADD = 0X2;

	INewsListView iNewsListView;
	private ArrayList<NewsItem> newsItems;

	public NewsListManager(INewsListView iPresenterView) {
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
	public void onWebFinish(int tag, String result) {
		// TODO Auto-generated method stub
		newsItems = ParseManager.parseNewsItems(result);

		switch (tag) {
		case TAG_NEWSLIST_REFRESH:
			iNewsListView.reFreshList(newsItems);
			iNewsListView.hideLoadingView();
			break;
		case TAG_NEWSLIST_ADD:
			iNewsListView.addList(newsItems);
			iNewsListView.hideLoadingView();
			break;
		default:
			break;
		}
	}

	@Override
	public void onWebError(int tag, WebError errorCode) {
		// TODO Auto-generated method stub
		iNewsListView.hideLoadingView();
	}

	@Override
	public void onFileFinish(int tag, String result) {
		// TODO Auto-generated method stub
		newsItems = ParseManager.parseNewsItems(result);
		switch (tag) {
		case TAG_NEWSLIST_REFRESH:
			iNewsListView.reFreshList(newsItems);
			break;
		default:
			break;
		}
	}

	@Override
	public void onFileError(int tag, FileError errorCode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onParse(String json) {
		// TODO Auto-generated method stub

	}

}
