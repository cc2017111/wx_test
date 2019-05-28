package com.xidian.faceToChild.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionManager {

	public static Map<String, HttpSession> sessionpool = new HashMap<String, HttpSession>();
	
	/**
	 * 存储session
	 * @param session
	 */
	public static void saveSession(HttpSession session) {
		sessionpool.put(session.getId(), session);
	}
	
	/**
	 * 获取session根据sessionId
	 * @param sid
	 * @return
	 */
	public static HttpSession getSession(String sid) {
		 return sessionpool.get(sid);
	}
	
	/**
	 * 删除session根据sessionId
	 * @param sid
	 */
	public static void removeSession(String sid) {
		sessionpool.remove(sid);
	}
}
