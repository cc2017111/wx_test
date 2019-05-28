package com.xidian.faceToChild.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xidian.faceToChild.exception.InvalidParamException;
import com.xidian.faceToChild.po.User;
import com.xidian.faceToChild.util.E;
import com.xidian.faceToChild.util.SessionManager;

@WebServlet("/user/test")
public class TestController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = req.getParameter("token");
		
		if (token == null || token.trim().length() == 0) {
			throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE, E.INVALID_PARAM_ERROR_INFO);
		}
		
		 HttpSession session = SessionManager.getSession(token);
		 
		 if(session == null) {
			 throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE, E.INVALID_PARAM_ERROR_INFO);
		 }
		 
		 Object user = session.getAttribute("userInfo");
		 if (user == null) {
			 throw new InvalidParamException(1003, "请登录");
		 }
		 resp.getWriter().write(user.toString());
	}
}
