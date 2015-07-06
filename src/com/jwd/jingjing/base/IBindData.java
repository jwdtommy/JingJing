package com.jwd.jingjing.base;

/**
 * @author jiangweidong
 */
public interface IBindData
{
	public static final int RESPONSE_ERROR_DATA=-1;
	public static final int RESPONSE_ERROR_NETWORK=-2;
	public static final int RESPONSE_ERROR_NULL=-3;
	public static final int RESPONSE_SUCCESS=1;
	public void bindData(int code,int tag,Object obj);
	public void onError(int code,String massage);
}
