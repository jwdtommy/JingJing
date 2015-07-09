package com.jwd.jingjing.activity;

import com.jwd.jingjing.base.IBindData;
import com.jwd.jingjing.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends FragmentActivity implements IBindData {

	private Button btn_login, btn_cancel;
	private EditText ed_username, ed_password;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_login);
	}

	private void initViews() {
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
