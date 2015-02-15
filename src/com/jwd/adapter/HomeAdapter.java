package com.jwd.adapter;

import java.util.ArrayList;
import java.util.Collection;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.jwd.base.BaseActivity;
import com.jwd.base.adapter.BaseDataAdapter;
import com.jwd.base.adapter.ViewHolder;
import com.jwd.model.MessageBean;
import com.jwd.net.NetEnity;
import com.jwd.net.NetWorkTask;
import com.jwd.photo123.App;
import com.jwd.photo123.R;

public class HomeAdapter extends BaseDataAdapter {
	private ArrayList<MessageBean> messages;
	TextView tv_content;
	TextView tv_yes;
	TextView tv_no;
	TextView tv_string_yes;
	TextView tv_string_no;
	BaseActivity context;
	ImageButton btn_share;

	public HomeAdapter(Fragment frag) {
		super(frag.getActivity());
		this.context = (BaseActivity) frag.getActivity();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setData(Object obj) {
		// TODO Auto-generated method stub
		this.cleanData();
		this.messages = (ArrayList<MessageBean>) obj;
		this.notifyDataSetChanged();
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return messages;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// return messages==null?0:messages.size();
		return messages == null ? 0 : messages.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getResouce() {
		// TODO Auto-generated method stub
		return R.layout.item_list;
	}

	@Override
	public void setItemData(final int position, View convertView,
			ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		Log.i("jwd", "title pos:" + position);
		tv_content.setText(messages.get(position).getContent());
		tv_yes.setText("(" + messages.get(position).getAgreeCount() + ")");
		tv_no.setText("(" + messages.get(position).getDenyCount() + ")");

	}

	@Override
	public void findView(int position, View convertView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		tv_content = (TextView) ViewHolder.get(convertView, R.id.tv_content);
		tv_string_yes = (TextView) ViewHolder.get(convertView, R.id.tv1);
		tv_string_no = (TextView) ViewHolder.get(convertView, R.id.tv2);
		tv_yes = (TextView) ViewHolder.get(convertView, R.id.tv_yesNum);
		tv_no = (TextView) ViewHolder.get(convertView, R.id.tv_noNum);
		btn_share = (ImageButton) ViewHolder.get(convertView, R.id.btn_share);
	}

	@Override
	public void cleanData() {
		// TODO Auto-generated method stub
		if (messages != null) {
			messages.clear();
		}
		notifyDataSetChanged();
	}

	@Override
	public void removeData(int pos) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addData(String tag, Object obj) {
		// TODO Auto-generated method stub
		if (messages != null) {
			messages.addAll((Collection<? extends MessageBean>) obj);
			this.notifyDataSetChanged();
		} else {
			setData(obj);
		}
		App.getInstance().putMessages(tag, messages);
	}

	private class BindItemListener implements NetWorkTask.IBindItemListener {
		public BindItemListener(int position) {
			// TODO Auto-generated constructor stub
		}

		public void onBindItemListener(int position) {
		};
	}

}
