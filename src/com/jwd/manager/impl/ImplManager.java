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

	public void onWebFinish(String result);//

	public void onWebError(WebError errorCode);//

	public void onFileFinish(String result);//

	public void onFileError(FileError errorCode);//

	public void onParse(String json);//

}
