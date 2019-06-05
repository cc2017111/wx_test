package com.xidian.faceToChild.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilterChain {

	private List<MyFilter> filterChains;
	private int current;

	public MyFilterChain() {
		this.filterChains = new ArrayList<MyFilter>();
		this.current = 0;
	}

	// 执行过滤链
	public void doFilter(HttpServletRequest req, HttpServletResponse resp) {
		if (current < filterChains.size()) {
			MyFilter filter = filterChains.get(current);
			current++;
			filter.doFilter(req, resp, this);
		}
	}

	public MyFilterChain addFilter(MyFilter filter) {
		this.filterChains.add(filter);
		return this;
	}
}
