package com.jwd.photo123;

import android.os.Bundle;
import com.jwd.base.BaseActivity;

public class WriteActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_write);
		getActionBar().setTitle("发布你的草蛋事儿");
		getActionBar().setDisplayHomeAsUpEnabled(true);// actionBar左侧箭头是否显示
		getActionBar().setDisplayShowHomeEnabled(false);// actionBar左侧图标是否显示
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
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
