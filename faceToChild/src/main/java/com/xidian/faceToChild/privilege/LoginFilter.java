package com.xidian.faceToChild.privilege;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xidian.faceToChild.exception.NoPrivilegeException;
import com.xidian.faceToChild.po.User;
import com.xidian.faceToChild.util.E;
import com.xidian.faceToChild.util.Global;
import com.xidian.faceToChild.util.SessionManager;
import com.xidian.faceToChild.web.MyFilter;
import com.xidian.faceToChild.web.MyFilterChain;

/**
 * 验证用户是否登陆过
 * 
 * @author PVer
 *
 */
public class LoginFilter implements MyFilter {

	private Logger log = LogManager.getLogger(LoginFilter.class);
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
		log.info("登录验证");
		// 获取token
		String token = req.getParameter("token");
		if (token == null) {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("JSESSIONID")) {
						token = cookie.getValue();
						break;
					}
				}
			}
		}

		log.info("获取token:" + token);
		// 验证token合法性
		if (token == null) {
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE, "请登录");
		}

		HttpSession session = SessionManager.getSession(token);

		if (session == null) {
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE, "请登录");
		}

		User user = (User) session.getAttribute("userInfo");
		if (user == null) {
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE, "请登录");
		}
		//存储token
		req.setAttribute("token", token);
		String role = user.getRole();
		if (Global.ROLE_ADMIN.equals(role)) {
			log.info("管理员权限不需要验证");
			return;
		}
		
		// 调用后续过滤器
		chain.doFilter(req, resp);
	}

}
