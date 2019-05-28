package com.xidian.faceToChild.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xidian.faceToChild.exception.BaseException;
import com.xidian.faceToChild.exception.InvalidParamException;
import com.xidian.faceToChild.mapper.UserMapper;
import com.xidian.faceToChild.po.User;
import com.xidian.faceToChild.service.UserService;
import com.xidian.faceToChild.service.UserServicelmpl;
import com.xidian.faceToChild.util.E;
import com.xidian.faceToChild.util.SessionManager;
import com.xidian.faceToChild.util.V;
import com.xidian.faceToChild.web.ApiResult;

public class UserController {
	private UserService userservice;
	private ObjectMapper objectMapper;

	public UserController() {
		this.userservice = new UserServicelmpl();
		this.objectMapper = new ObjectMapper();
	}

	public Map<String, String> login(HttpServletRequest req, HttpServletResponse resp) {
		//验证用户输入是否为空 
		String[] params = {"login_name", "password"};
		V.valid(req, params);
		
		String username = req.getParameter("login_name");
		String password = req.getParameter("password");
		
		User user = userservice.login(username, password);
		
		HttpSession session = req.getSession();
		session.setAttribute("userInfo", user);
		SessionManager.saveSession(session);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", session.getId());
		
		return map;
	} 
	
	public void addUser(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"id", "nick_name", "role", "tel", "picture", "password"};
		V.valid(req, params);

		User user = V.entity(req, User.class, params);
		userservice.addUser(user);
	}
	
	/**
	 * 删除指定ID的用户
	 * @param req
	 * @param resp
	 */
	public void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"id"};
		V.valid(req, params);
		
		int id = Integer.parseInt(req.getParameter("id"));
		userservice.deleteUser(id);
	}
	/**
	 * 修改用户的信息
	 * @param req
	 * @param resp
	 */
	public void updateUser(HttpServletRequest req, HttpServletResponse resp) {
		String[] mustParam = {"id"};
		V.valid(req, mustParam);
		String[] params ={"id", "nick_name", "role", "tel", "picture", "password"};
		User user = V.entity(req, User.class, params);
		
		userservice.updateUser(user);
	}
}
