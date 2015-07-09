package com.jwd.jingjing.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.jwd.jingjing.base.BaseActivity;
import com.jwd.jingjing.util.PreferenceUtils;
import com.jwd.jingjing.util.ToastUtil;
import com.jwd.jingjing.view.TagGroup;
import com.jwd.jingjing.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class WriteActivity extends BaseActivity {
	private MaterialEditText ed_content;;

	private String content;
	private int sort;
	private TagGroup tagGroup;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_write);
		getActionBar().setTitle("发布你的草蛋事儿");
		getActionBar().setDisplayHomeAsUpEnabled(true);// actionBar左侧箭头是否显示
		getActionBar().setDisplayShowHomeEnabled(true);// actionBar左侧图标是否显示
		init();
		initData();
	}

	private void init() {
		ed_content = (MaterialEditText) findViewById(R.id.ed_content);
		tagGroup = (TagGroup) findViewById(R.id.tag_group);
	}

	private void initData() {
		tagGroup.setTags("杂七杂八", "生活", "工作", "爱情", "学校", "家庭", "钱", "小孩");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.write, menu);
		restoreActionBar();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.action_write) {
			content = ed_content.getText() + "";
			String username = PreferenceUtils.getUserName(this);
			if (TextUtils.isEmpty(content)) {
				ToastUtil.show("请填写内容哦");
				return true;
			}

			if (TextUtils.isEmpty(username)) {
				ToastUtil.show("请先登录");
				return true;
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
	}

	@Override
	public void bindData(int code, int tag, Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onError(int code, String massage) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
