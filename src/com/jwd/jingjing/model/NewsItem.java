package com.jwd.jingjing.model;

import java.io.Serializable;

public class NewsItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7277621847336650190L;
	private String mClass;
	private String height;
	private String sourceurl;
	private String thumburl;
	private String title;
	private String url;
	private String width;

	public String getmClass() {
		return mClass;
	}

	public void setmClass(String mClass) {
		this.mClass = mClass;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSourceurl() {
		return sourceurl;
	}

	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}

	public String getThumburl() {
		return thumburl;
	}

	public void setThumburl(String thumburl) {
		this.thumburl = thumburl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "NewsItem [mClass=" + mClass + ", height=" + height
				+ ", sourceurl=" + sourceurl + ", thumburl=" + thumburl
				+ ", title=" + title + ", url=" + url + ", width=" + width
				+ "]";
	}

}
