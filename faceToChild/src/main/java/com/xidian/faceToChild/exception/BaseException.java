package com.xidian.faceToChild.exception;

public class BaseException extends RuntimeException{

	//错误编码
	private int code;
	public BaseException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
