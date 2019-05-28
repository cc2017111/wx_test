package com.xidian.faceToChild.web;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.xidian.faceToChild.util.D;

@WebListener
public class ReqListen implements ServletRequestListener{

	public void requestDestroyed(ServletRequestEvent arg0) {
		D.closeConn();
	}

	public void requestInitialized(ServletRequestEvent arg0) {
	}

}
