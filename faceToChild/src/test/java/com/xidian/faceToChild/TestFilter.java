package com.xidian.faceToChild;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xidian.faceToChild.web.MyFilter;
import com.xidian.faceToChild.web.MyFilterChain;

public class TestFilter {

	/**
	 * 白名单
	 * @author PVer
	 *
	 */
	static class whiteFilter implements MyFilter {

		public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {

			System.out.println("白名单");
			chain.doFilter(req, resp);
		}

	}

	public static void main(String[] args) {

		whiteFilter whitefilter = new whiteFilter();
		MyFilterChain chain = new MyFilterChain();
		MyFilter myfilter = new MyFilter() {
			
			public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {

				System.out.println("这里是验证2");
			}
		};
		
		
		chain.addFilter(whitefilter);
		chain.addFilter(myfilter);
		chain.doFilter(null, null);
	}
}
