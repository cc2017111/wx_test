package com.xidian.faceToChild.privilege;

import java.util.ArrayList;
import java.util.List;

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
 * 权限验证
 * 
 * @author PVer
 *
 */
public class PrivilegeFilter implements MyFilter {

	private static List<String> users;
	private Logger log = LogManager.getLogger(PrivilegeFilter.class);

	public PrivilegeFilter() {
		this.users = new ArrayList<String>();
		users.add("user/add");
		users.add("user/list");
		users.add("user/update");
		users.add("user/find");
		users.add("user/delete");
		users.add("AC/add_sub_context");
		users.add("AC/add_main_context");
	}

	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
		String reqPath = (String) req.getAttribute(Global.REQ_PATH);
		String sid = (String) req.getAttribute("token");
		HttpSession session = SessionManager.getSession(sid);
		User user = (User) session.getAttribute("userInfo");
		String role = user.getRole();

		if (!Global.ROLE_USER.equals(role)) {
			throw new NoPrivilegeException(E.NO_PRIVILEGE_ERROR_CODE, E.NO_PRIVILEGE_ERROR_INFO);
		}

		if (!users.contains(reqPath)) {
			throw new NoPrivilegeException(E.NO_PRIVILEGE_ERROR_CODE, E.NO_PRIVILEGE_ERROR_INFO);
		}

		log.info("通过权限验证");
		chain.doFilter(req, resp);
	}

}
