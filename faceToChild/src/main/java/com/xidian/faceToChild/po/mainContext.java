package com.xidian.faceToChild.po;

public class mainContext {

	private Integer id;
	private String title;
	private String context;
	private String userID;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "mainContext [id=" + id + ", title=" + title + ", context=" + context + ", userID=" + userID + "]";
	}
	
}
