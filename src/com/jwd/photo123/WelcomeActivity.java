package com.jwd.photo123;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.jwd.animation.AnimationLoader;
import com.jwd.base.BaseActivity;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;

public class WelcomeActivity extends BaseActivity {
	TextView tv_title;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_welcome);
		tv_title = (TextView) findViewById(R.id.tv_title);
		AnimatorListener listener = new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				WelcomeActivity.this.startActivity(new Intent(
						WelcomeActivity.this, HomeActivity.class));
				WelcomeActivity.this.finish();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
			}
		};
		AnimationLoader.doAnimation(listener, tv_title);
	//	 AnimationLoader.doAnimationSet(this, tv_title);
	//	 tv_title.animate().scaleYBy(10).setDuration(5000).start();
	}

	@Override
	public void bindData(int code, int tag, Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onError(int code, String massage) {
		// TODO Auto-generated method stub
	}
}
