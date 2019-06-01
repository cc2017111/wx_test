package com.xidian.faceToChild.po;

public class subContext {
	private Integer id;
	private String title;
	private String context;
	private Integer userID;
	private Integer fatherID;
	private Integer mainID;
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
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getFatherID() {
		return fatherID;
	}
	public void setFatherID(Integer fatherID) {
		this.fatherID = fatherID;
	}
	public Integer getMainID() {
		return mainID;
	}
	public void setMainID(Integer mainID) {
		this.mainID = mainID;
	}
	@Override
	public String toString() {
		return "subContext [id=" + id + ", title=" + title + ", context=" + context + ", userID=" + userID
				+ ", fatherID=" + fatherID + ", mainID=" + mainID + "]";
	}
	
}
