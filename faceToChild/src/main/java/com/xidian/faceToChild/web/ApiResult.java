package com.xidian.faceToChild.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ApiResult {
	private int code;
	private String msg;
	private Object data;

	public ApiResult() {
		// TODO Auto-generated constructor stub
		this.setCode(200);
		this.setMsg("success");
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ApiResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
