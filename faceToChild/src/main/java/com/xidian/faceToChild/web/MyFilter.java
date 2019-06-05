package com.xidian.faceToChild.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义过滤器接口
 * @author PVer
 *
 */
public interface MyFilter {

	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain);
}
