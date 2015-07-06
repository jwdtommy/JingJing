package com.jwd.jingjing.base.adapter;

import android.view.View;
import android.view.ViewGroup;


/*  adapter��ݽӿ�
 * @author jiangweidong   
 */
public interface IBindAdapter {
/**
 * �ڴ˴��������Դ	
 * @param obj
 */
public void setData(Object obj);
/**
 * ��ȡ���Դ
 */
public Object getData();
/**
 * ������Դ
 */
public void cleanData();
/**
 * ɾ��ĳ��item
 */
public void removeData(int pos);
/**
 * ׷�����
 */
public void addData(String tag,Object obj);
/**
 * ��ȡ������Դ�ļ�
 */
public int getResouce();
/**
 * ���getView �ڴ˴���ȡitem�пؼ�
 * @param position
 * @param view
 * @param viewGroup
 */
public void findView(int position,View view,ViewGroup viewGroup);
/**
 * ����item�е����
 * @param position
 * @param view
 * @param viewGroup
 */
public void setItemData(int position,View view,ViewGroup viewGroup);
}
