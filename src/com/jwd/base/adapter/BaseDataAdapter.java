package com.jwd.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

/**
 * @author JTommy
 */
public abstract class BaseDataAdapter extends BaseAdapter implements
		IBindAdapter {
	private LayoutInflater inflater;
	private int resouce;

	public BaseDataAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		resouce = getResouce();
	}

	@Override
	public View getView(int position, View convertView,
			android.view.ViewGroup parent) {
		if (convertView == null) {
			convertView = getConvertView();
		}
		findView(position, convertView, parent);
		setItemData(position, convertView, parent);
		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}
	public LayoutInflater getInflater() {
		return inflater;
	}

	public View getConvertView() {
		View view = inflater.inflate(resouce, null);
		return view;
	}
}
