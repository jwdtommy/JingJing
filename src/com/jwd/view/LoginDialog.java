package com.jwd.view;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.gc.materialdesign.views.ButtonFlat;
import com.jwd.base.BaseActivity;
import com.jwd.base.IBindData;
import com.jwd.model.LoginBean;
import com.jwd.net.NetEnity;
import com.jwd.net.NetWorkTask;
import com.jwd.photo123.App;
import com.jwd.photo123.R;
import com.jwd.util.PreferenceUtils;
import com.jwd.util.ToastUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginDialog extends Dialog implements IBindData {
	private ButtonFlat btn_login, btn_cancel, btn_register;
	private MaterialEditText ed_username, ed_password;
	private BaseActivity mContext;
	private boolean canDismiss = true;

	public LoginDialog(BaseActivity context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
	}

	public LoginDialog(BaseActivity context, int theme) {
		super(context, theme);
		this.mContext = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setCanDismiss(true);
		setContentView(R.layout.activity_login);
		setTitle("登录/注册");
		initViews();
	}

	private void initViews() {
		btn_login = (ButtonFlat) findViewById(R.id.btn_login);
		btn_register = (ButtonFlat) findViewById(R.id.btn_register);
		ed_username = (MaterialEditText) findViewById(R.id.ed_username);
		ed_password = (MaterialEditText) findViewById(R.id.ed_password);
		btn_login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("jwd", "onClick");
				String username = null;
				String password = null;

				if (ed_username.getText() != null) {
					username = ed_username.getText().toString().trim();
				}
				if (ed_password.getText() != null) {
					password = ed_password.getText().toString().trim();
				}
				if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
					username = "jwdtommy1@qq.com";
					password = "123456";
				}

				if (checkUsername(username) && checkPassword(password)) {
					setCanDismiss(false);
					new NetWorkTask(mContext, NetEnity.Net_TAG_LOGIN, username,
							password).startLoading();
				}
			}
		});
	}

	public boolean isCanDismiss() {
		return canDismiss;
	}

	public void setCanDismiss(boolean canDismiss) {
		this.canDismiss = canDismiss;
		this.setCancelable(canDismiss);
		this.setCanceledOnTouchOutside(canDismiss);
	}

	public ButtonFlat getBtn_login() {
		return btn_login;
	}

	public void setBtn_login(ButtonFlat btn_login) {
		this.btn_login = btn_login;
	}

	public ButtonFlat getBtn_cancel() {
		return btn_cancel;
	}

	public void setBtn_cancel(ButtonFlat btn_cancel) {
		this.btn_cancel = btn_cancel;
	}

	public ButtonFlat getBtn_register() {
		return btn_register;
	}

	public void setBtn_register(ButtonFlat btn_register) {
		this.btn_register = btn_register;
	}

	public MaterialEditText getEd_username() {
		return ed_username;
	}

	public void setEd_username(MaterialEditText ed_username) {
		this.ed_username = ed_username;
	}

	public MaterialEditText getEd_password() {
		return ed_password;
	}

	public void setEd_password(MaterialEditText ed_password) {
		this.ed_password = ed_password;
	}

	@Override
	public void bindData(int code, int tag, Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onError(int code, String massage) {
		// TODO Auto-generated method stub
	}

	private boolean checkUsername(String username) {
		return true;
	}

	private boolean checkPassword(String password) {
		return true;
	}

}
