package com.xidian.faceToChild.po;

public class mainContext {

	private Long id;
	private String title;
	private String context;
	private Integer userID;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "mainContext [id=" + id + ", title=" + title + ", context=" + context + ", userID=" + userID + "]";
	}

}
