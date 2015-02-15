package com.jwd.base;

import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity implements
		IBindData {
	UMSocialService socialService;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		socialService = UMServiceFactory.getUMSocialService("com.umeng.share");
		MobclickAgent.setDebugMode(true);
		MobclickAgent.openActivityDurationTrack(false);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this); // 统计时长
	}

	public UMSocialService getSocialService() {
		return socialService;
	}
}
