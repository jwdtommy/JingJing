package com.jwd.base.adapter;

import android.view.View;
import android.view.ViewGroup;


/*  adapter数据接口
 * @author jiangweidong   
 */
public interface IBindAdapter {
/**
 * 在此处重置数据源	
 * @param obj
 */
public void setData(Object obj);
/**
 * 获取数据源
 */
public Object getData();
/**
 * 清除数据源
 */
public void cleanData();
/**
 * 删除某个item
 */
public void removeData(int pos);
/**
 * 追加数据
 */
public void addData(String tag,Object obj);
/**
 * 获取布局资源文件
 */
public int getResouce();
/**
 * 替代getView 在此处获取item中控件
 * @param position
 * @param view
 * @param viewGroup
 */
public void findView(int position,View view,ViewGroup viewGroup);
/**
 * 设置item中的数据
 * @param position
 * @param view
 * @param viewGroup
 */
public void setItemData(int position,View view,ViewGroup viewGroup);
}
