package com.jwd.jingjing.adapter;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jwd.jingjing.base.BaseActivity;
import com.jwd.jingjing.fragment.BaseListFragment;
import com.jwd.jingjing.model.NewsItem;
import com.jwd.jingjing.photo123.App;
import com.jwd.jingjing.util.BFImageCache;
import com.jwd.jingjing.util.MLog;
import com.jwd.jingjing.view.ShareDialog;
import com.jwd.jingjing.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class NewsListAdapter extends
		RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
	BaseActivity context;
	private ArrayList<NewsItem> items;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView tv_content;
		public TextView tv_yes;
		public TextView tv_no;
		public TextView tv_string_yes;
		public TextView tv_string_no;
		public ImageView iv_image;
		ImageButton btn_share;

		public ViewHolder(View layout) {
			super(layout);
			tv_content = (TextView) layout.findViewById(R.id.tv_content);
			iv_image = (ImageView) layout.findViewById(R.id.iv_image);
			tv_string_yes = (TextView) layout.findViewById(R.id.tv1);
			tv_string_no = (TextView) layout.findViewById(R.id.tv2);
			tv_yes = (TextView) layout.findViewById(R.id.tv_yesNum);
			tv_no = (TextView) layout.findViewById(R.id.tv_noNum);
			btn_share = (ImageButton) layout.findViewById(R.id.btn_share);
		}
	}

	public NewsListAdapter(BaseListFragment frag) {
		super();
		this.context = (BaseActivity) frag.getActivity();
	}

	public void cleanData() {
		// TODO Auto-generated method stub
		if (items != null) {
			items.clear();
			notifyDataSetChanged();
		}
	}

	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public void refreshData(ArrayList<NewsItem> items) {
		this.items = items;
		notifyDataSetChanged();
	}

	public void addData(ArrayList<NewsItem> items) {
		if (this.items == null) {
			this.items = items;
		} else {
			this.items.addAll(items);
		}
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return items == null ? 0 : items.size();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		Log.i("jwd", "onCreateViewHolder()");
		View layout = LayoutInflater.from(context).inflate(R.layout.item_list,
				null);
		return new ViewHolder(layout);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int position) {
		// // TODO Auto-generated method stub
		// Log.i("jwd", "onBindViewHolder() pos=" + position);
		final NewsItem item = items.get(position);
		viewHolder.tv_content.setText(item.getTitle());
		MLog.i("getSourceurl=" + item.getSourceurl());
		ImageLoader.getInstance().displayImage(item.getSourceurl(),
				viewHolder.iv_image, App.imageDisplayConfig);
		viewHolder.btn_share.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShareDialog dialog = new ShareDialog(context);
				dialog.setContent(item.getTitle(), item.getTitle(), "",
						item.getSourceurl());
				dialog.show();
			}
		});
	}
}
