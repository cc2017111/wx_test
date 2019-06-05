package com.xidian.faceToChild.privilege;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;

import com.xidian.faceToChild.util.Global;
import com.xidian.faceToChild.web.MyFilter;
import com.xidian.faceToChild.web.MyFilterChain;
import com.xidian.faceToChild.web.WebDispatcher;

public class WhiteFilter implements MyFilter {
	private static final List<String> WhiteList;
	private org.apache.log4j.Logger log = LogManager.getLogger(WhiteFilter.class);
	static {
		WhiteList = new ArrayList<String>();
		WhiteList.add("user/login");
		WhiteList.add("user/addUser");
	}

	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {

		String requestUri = req.getRequestURI();
		String path = (String) req.getAttribute(Global.REQ_PATH);

		if (!WhiteList.contains(path)) {
			chain.doFilter(req, resp);
		} else {
			log.info("通过白名单验证");
		}
	}

}
