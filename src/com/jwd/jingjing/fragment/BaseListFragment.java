package com.jwd.jingjing.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jwd.jingjing.animation.FadeInBottomAnimator;
import com.jwd.jingjing.R;

/**
 * @author jiangweidong
 */
public abstract class BaseListFragment extends BaseFragment {
	private int lastItemIndex;
	private int totalItemCount = -1;
	protected SwipeRefreshLayout sLayout;
	protected RecyclerView recyclerView;
	protected LinearLayoutManager mLayoutManager;
	private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
		@Override
		public void onRefresh() {
			// TODO Auto-generated method stub
			onReFresh();
		}
	};

	private RecyclerView.OnScrollListener OnScrollListener = new RecyclerView.OnScrollListener() {

		@Override
		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			// TODO Auto-generated method stub
			super.onScrollStateChanged(recyclerView, newState);
		}

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			// TODO Auto-generated method stub
			super.onScrolled(recyclerView, dx, dy);
			int lastVisibleItem = ((LinearLayoutManager) mLayoutManager)
					.findLastVisibleItemPosition();
			int totalItemCount = mLayoutManager.getItemCount();
			if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {
				onLoadMore();
			}
		}

	};

	public BaseListFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mInflater = inflater;
		view = inflater.inflate(R.layout.frag_base_list, null);
		sLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_swipe);
		sLayout.setOnRefreshListener(refreshListener);
		recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		recyclerView.setHasFixedSize(true);
		recyclerView.setOnScrollListener(OnScrollListener);
		mLayoutManager = new LinearLayoutManager(this.getActivity());
		mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new FadeInBottomAnimator());
		onShowView(inflater, container, savedInstanceState);
		return view;
	}

	public abstract void onShowView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState);

	public abstract void onLoadMore();

	public abstract void onReFresh();
}
