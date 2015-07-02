package com.jwd.adapter;

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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.jwd.base.BaseActivity;
import com.jwd.fragment.BaseListFragment;
import com.jwd.model.NewsItem;
import com.jwd.photo123.App;
import com.jwd.photo123.R;
import com.jwd.util.BFImageCache;
import com.jwd.util.MLog;

public class NewsListAdapter extends
		RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
	BaseActivity context;
	private ArrayList<NewsItem> items;
	private ImageLoader imageLoader;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView tv_content;
		public TextView tv_yes;
		public TextView tv_no;
		public TextView tv_string_yes;
		public TextView tv_string_no;
		public NetworkImageView iv_image;
		ImageButton btn_share;

		public ViewHolder(View layout) {
			super(layout);
			tv_content = (TextView) layout.findViewById(R.id.tv_content);
			iv_image = (NetworkImageView) layout.findViewById(R.id.iv_image);
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
		final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
		imageLoader = new ImageLoader(App.queue, new ImageCache() {
			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				// TODO Auto-generated method stub
				cache.put(url, bitmap);
			}

			@Override
			public Bitmap getBitmap(String url) {
				// TODO Auto-generated method stub
				return cache.get(url);
			}
		});
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
		this.notifyDataSetChanged();
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
		viewHolder.iv_image.setImageUrl(item.getSourceurl(), imageLoader);
		viewHolder.btn_share.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("jwd", "----share----");
				context.getSocialService().setShareContent(item.getTitle());
				context.getSocialService().openShare(context, false);
			}
		});
	}

	private void setHeight(ImageView view, float srcWidth, float desWidth,
			int srcHeight) {
		float precent = desWidth / srcWidth;
		ViewGroup.LayoutParams p = new LayoutParams(LayoutParams.FILL_PARENT,
				(int) (srcHeight * precent));
		view.setLayoutParams(p);
	}

}
