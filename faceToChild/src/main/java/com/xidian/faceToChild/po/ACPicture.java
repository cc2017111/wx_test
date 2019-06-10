package com.xidian.faceToChild.po;

public class ACPicture {
	private Integer id;
	private String fileDesc;
	private String url;
	private Integer createByUser;
	private Integer type;
	private Long fromAC;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCreateByUser() {
		return createByUser;
	}

	public void setCreateByUser(Integer createByUser) {
		this.createByUser = createByUser;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getFromAC() {
		return fromAC;
	}

	public void setFromAC(Long fromAC) {
		this.fromAC = fromAC;
	}

	@Override
	public String toString() {
		return "ACPicture [id=" + id + ", fileDesc=" + fileDesc + ", url=" + url + ", createByUser=" + createByUser
				+ ", type=" + type + ", fromAC=" + fromAC + "]";
	}

}
