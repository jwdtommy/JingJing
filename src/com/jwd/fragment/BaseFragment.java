package com.jwd.fragment;

import com.jwd.base.IBindData;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author jiangweidong
 */
public abstract class BaseFragment extends Fragment
{
	protected Activity mActivity;
	protected LayoutInflater mInflater;
	protected View view;
	
	public BaseFragment()
	{
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//mActivity=this.getActivity();
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	    MobclickAgent.onPageStart(this.getClass().getName()); //ͳ��ҳ��
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	    MobclickAgent.onPageEnd(this.getClass().getName()); //ͳ��ҳ��
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mActivity=activity;
	}
	
	
}
