package com.jwd.adapter;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jwd.base.BaseActivity;
import com.jwd.fragment.BaseListFragment;
import com.jwd.model.MessageBean;
import com.jwd.net.NetEnity;
import com.jwd.net.NetWorkTask;
import com.jwd.photo123.App;
import com.jwd.photo123.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
	private ArrayList<MessageBean> messages;
	BaseActivity context;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView tv_content;
		public TextView tv_yes;
		public TextView tv_no;
		public TextView tv_string_yes;
		public TextView tv_string_no;
		ImageButton btn_share;

		public ViewHolder(View layout) {
			super(layout);
			tv_content = (TextView) layout.findViewById(R.id.tv_content);
			tv_string_yes = (TextView) layout.findViewById(R.id.tv1);
			tv_string_no = (TextView) layout.findViewById(R.id.tv2);
			tv_yes = (TextView) layout.findViewById(R.id.tv_yesNum);
			tv_no = (TextView) layout.findViewById(R.id.tv_noNum);
			btn_share = (ImageButton) layout.findViewById(R.id.btn_share);
		}
	}

	public MyAdapter(BaseListFragment frag) {
		super();
		this.context = (BaseActivity) frag.getActivity();
	}

	public void setData(Object obj) {
		// this.cleanData();
		this.messages = (ArrayList<MessageBean>) obj;
		if (messages != null) {
			this.notifyItemRangeInserted(0, messages.size());
		}
	}

	public void cleanData() {
		// TODO Auto-generated method stub
		if (messages != null) {
			messages.clear();
		}
	}

	public Object getData() {
		// TODO Auto-generated method stub
		return messages;
	}

	public void addData(String tag, Object obj) {
		// TODO Auto-generated method stub
		Log.i("jwd", "addData()");
		if (messages != null) {
			messages.addAll((Collection<? extends MessageBean>) obj);
			this.notifyDataSetChanged();
		} else {
			setData(obj);
		}
		App.getInstance().putMessages(tag, messages);
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		if (messages == null) {
			Log.i("jwd", "getItemCount() messages is null");
		} else {
			Log.i("jwd", "getItemCount() messages size=" + messages.size());
		}
		return messages == null ? 0 : messages.size();
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
		// TODO Auto-generated method stub
		Log.i("jwd", "onBindViewHolder() pos=" + position);
		viewHolder.tv_content.setText(messages.get(position).getContent());
		viewHolder.tv_yes.setText("(" + messages.get(position).getAgreeCount()
				+ ")");
		viewHolder.tv_no.setText("(" + messages.get(position).getDenyCount()
				+ ")");

		viewHolder.tv_string_yes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// new NetWorkTask(context, NetEnity.NET_TAG_VOTE_YES,0,null
				// ,messages.get(position).getId(),messages.get(position).getAgreeCount())
				// .startLoading();
				String id = messages.get(position).getId();
				String yesNum = messages.get(position).getAgreeCount() + "";
				new NetWorkTask(context, NetEnity.NET_TAG_VOTE_YES, position,
						new NetWorkTask.IBindItemListener() {
							@Override
							public void onBindItemListener(int position) {
								// TODO Auto-generated method stub
								int oldNum = messages.get(position)
										.getAgreeCount();
								messages.get(position)
										.setAgreeCount(oldNum + 1);
								MyAdapter.this.notifyDataSetChanged();
							}
						}).setParams(id, yesNum).startLoading();
			}
		});
		viewHolder.tv_string_no.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String id = messages.get(position).getId();
				String noNum = messages.get(position).getDenyCount() + "";
				new NetWorkTask(context, NetEnity.NET_TAG_VOTE_NO, position,
						new NetWorkTask.IBindItemListener() {
							@Override
							public void onBindItemListener(int position) {
								// TODO Auto-generated method stub
								int oldNum = messages.get(position)
										.getDenyCount();
								messages.get(position).setDenyCount(oldNum + 1);
								MyAdapter.this.notifyDataSetChanged();
							}
						}).setParams(id, noNum).startLoading();
			}
		});
		viewHolder.btn_share.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("jwd", "----share----");
				context.getSocialService().setShareContent(
						messages.get(0).getContent());
				context.getSocialService().openShare(context, false);
			}
		});
	}
}
