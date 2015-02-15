package com.jwd.animation;

import java.util.ArrayList;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class FadeInBottomAnimator extends ItemAnimator {

	private final static String TAG = "FadeInBottomAnimator";

	private ArrayList<ViewHolder> mPendingAdditions = new ArrayList<ViewHolder>();

	// 增加动画 默认y轴移动距离
	private final static int ADD_TRANS_Y = 100;
	// 增加动画 默认延时基数 毫秒
	private final static int ADD_BASE_DELAY = 600;
	// 增加动画 默认动画时常 毫秒
	private final static int ADD_BASE_DURATION = 500;

	@Override
	public boolean animateAdd(ViewHolder holder) {
		Log.i(TAG, "animateAdd");
		ViewCompat.setAlpha(holder.itemView, 0);
		ViewCompat.setTranslationY(holder.itemView, ADD_TRANS_Y);
		mPendingAdditions.add(holder);
		return true;
	}

	private void animateAddImpl(final ViewHolder holder) {

		int delay = ADD_BASE_DELAY;
		int pos = holder.getPosition();
		int size = mPendingAdditions.size();
		delay = pos * (delay / size);

		final View view = holder.itemView;
		ViewCompat.animate(view).cancel();
		ViewCompat.animate(view).alpha(1).translationY(0).setStartDelay(delay)
				.setDuration(ADD_BASE_DURATION)
				.setInterpolator(new DecelerateInterpolator())
				.setListener(new ViewPropertyAnimatorListener() {

					@Override
					public void onAnimationStart(View arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(View arg0) {
						dispatchAddFinished(holder);
					}

					@Override
					public void onAnimationCancel(View arg0) {
						ViewCompat.setAlpha(view, 1);
						ViewCompat.setTranslationY(view, 0);
					}
				}).start();
	}

	// @Override
	// public boolean animateChange(ViewHolder arg0, ViewHolder arg1, int arg2,
	// int arg3, int arg4, int arg5) {
	// // TODO Auto-generated method stub
	// return false;
	// }
	@Override
	public boolean animateMove(ViewHolder arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean animateRemove(ViewHolder arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void endAnimation(ViewHolder holder) {
		Log.i(TAG, "endAnimation");
		final View view = holder.itemView;
		ViewCompat.animate(view).cancel();
		if (mPendingAdditions.contains(holder)) {
			ViewCompat.setAlpha(view, 1);
			ViewCompat.setTranslationY(view, 0);
			dispatchAddFinished(holder);
			mPendingAdditions.remove(holder);
		}
	}

	@Override
	public void endAnimations() {
		Log.i(TAG, "endAnimations");
		int count = mPendingAdditions.size();
		for (int i = count - 1; i >= 0; i--) {
			ViewHolder holder = mPendingAdditions.get(i);
			ViewCompat.setAlpha(holder.itemView, 1);
			ViewCompat.setTranslationY(holder.itemView, 0);
			dispatchAddFinished(holder);
			mPendingAdditions.remove(holder);
		}
	}

	@Override
	public boolean isRunning() {
		Log.i(TAG, "isRunning");
		return false;
	}

	@Override
	public void runPendingAnimations() {
		Log.i(TAG, "runPendingAnimations");
		for (ViewHolder holder : mPendingAdditions) {
			animateAddImpl(holder);
		}
		mPendingAdditions.clear();
	}

	@Override
	public boolean animateChange(ViewHolder arg0, ViewHolder arg1, int arg2,
			int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
