package com.jwd.animation;

import java.util.Timer;
import java.util.TimerTask;
import com.jwd.photo123.R;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.view.View;
public class AnimationLoader {
	private Context mContext;
	public static final int ANIM_1 = 0;

	public AnimationLoader(Context context) {
		mContext = context;
	}

	public static void doAnimation(final AnimatorListener listener,
			final View... view) {
		int delay = 0;
		final int add_delay = 200;
		final int duration = 3000;
		for (int i = 0; i < view.length; i++) {
			Handler hander = new Handler();
			final View temp_view = view[i];
			final int index = i;
			// TODO Auto-generated method stub
			final ObjectAnimator animator = ObjectAnimator.ofFloat(temp_view,
					"height",2, 300, 0, 0);
			animator.setDuration(duration);
			if (index == view.length - 1) {
				animator.addListener(listener);
			}
			hander.postDelayed(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					animator.start();
				}
			}, delay);
		}
		delay += add_delay;
	}
	public static void doAnimationSet(Context context,View view)
	{
		AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context,
			    R.animator.animators);
			set.setTarget(view);
			set.start();
	}
}
