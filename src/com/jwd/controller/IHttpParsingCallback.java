package com.jwd.controller;

import android.media.MediaPlayer.OnErrorListener;

public interface IHttpParsingCallback {

public void OnErrorListener(String msg);
	
public void OnSuccessListener(String msg);

}
