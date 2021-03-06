package com.jwd.jingjing.base.adapter;

import android.util.SparseArray;
import android.view.View;

public class ViewHolder {
	private static SparseArray<View> viewHolder;
	private ViewHolder() {
	}
	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id) {
		viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}

	public static void destoryView() {
		if (viewHolder != null) {
			viewHolder.clear();
			viewHolder = null;
		}
	}
}
