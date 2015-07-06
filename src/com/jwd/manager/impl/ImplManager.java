package com.jwd.manager.impl;

public interface ImplManager {

	enum WebError {
		CONNECT_FAIL, CONNECT_TIMEOUT;
	}

	enum FileError {
		NO_DATA;//
	}

	public String configFilePath();//

	public String configUrl();//

	public void onWebFinish(int tag,String result);//

	public void onWebError(int tag,WebError errorCode);//

	public void onFileFinish(int tag,String result);//

	public void onFileError(int tag,FileError errorCode);//

	public void onParse(String json);//

}
