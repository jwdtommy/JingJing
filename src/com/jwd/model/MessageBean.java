package com.jwd.model;

public class MessageBean {
private String id;
private String userId;
private String userName;	
private String title;
private String content;
private int agreeCount;
private int denyCount;
private String date;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getAgreeCount() {
	return agreeCount;
}
public void setAgreeCount(int agreeCount) {
	this.agreeCount = agreeCount;
}
public int getDenyCount() {
	return denyCount;
}
public void setDenyCount(int denyCount) {
	this.denyCount = denyCount;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}





}
