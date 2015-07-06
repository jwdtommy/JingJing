package com.jwd.jingjing.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseActivity extends FragmentActivity implements
		IBindData {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		MobclickAgent.setDebugMode(true);
		MobclickAgent.openActivityDurationTrack(false);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this); // ͳ��ʱ��
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this); // ͳ��ʱ��
	}
}
