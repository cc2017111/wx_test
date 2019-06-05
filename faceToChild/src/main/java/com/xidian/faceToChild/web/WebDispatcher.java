package com.xidian.faceToChild.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.management.ReflectionException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xidian.faceToChild.exception.BaseException;
import com.xidian.faceToChild.exception.InvalidParamException;
import com.xidian.faceToChild.privilege.LoginFilter;
import com.xidian.faceToChild.privilege.PrivilegeFilter;
import com.xidian.faceToChild.privilege.WhiteFilter;
import com.xidian.faceToChild.util.E;
import com.xidian.faceToChild.util.Global;
import com.xidian.faceToChild.util.NameUtil;

/**
 * 前端分发器，程序的统一入口
 * 
 * @author PVer
 *
 */
@WebServlet("/app/*")
public class WebDispatcher extends HttpServlet {
	public static final String PROJECT_NAME = "/faceToChild/";
	public static final String SUB_NAME = "app/";
	private static final String PACJAGE_NAME = "com.xidian.faceToChild.controller";
	private static ObjectMapper objMapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取用户的请求地址
		String uri = req.getRequestURI();
		// 去掉路径中的项目名称
		uri = uri.replace(PROJECT_NAME, "");
		uri = uri.replace(SUB_NAME, "");

		// 将请求路径存储到requset对象中
		req.setAttribute(Global.REQ_PATH, uri);

		// 创建过滤链
		MyFilterChain chain = new MyFilterChain();
		// 白名单验证器
		MyFilter whiteListFilter = new WhiteFilter();
		// 用户登录验证器
		MyFilter loginFilter = new LoginFilter();
		// 权限验证
		MyFilter privilegeFiter = new PrivilegeFilter();

		// 添加过滤器到过滤链
		chain.addFilter(whiteListFilter).addFilter(loginFilter).addFilter(privilegeFiter);

		ApiResult apiresult = new ApiResult();
		
		try {
			chain.doFilter(req, resp);
			
			// 使用/分割字符串 结果是数组
			String[] reqUri = uri.split("/");
			if (reqUri.length < 2) {
				throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE, E.INVALID_PARAM_ERROR_INFO);
			}
			String cat = reqUri[0];
			String opt = reqUri[1];

			// 名称风格的转换
			cat = NameUtil.convert2Caml(cat);
			cat = NameUtil.firstUpper(cat);
			opt = NameUtil.convert2Caml(opt);

			String catName = PACJAGE_NAME + "." + cat + "Controller";
			// 获取指定类的Class对象
			Class<?> clz = Class.forName(catName);
			// 创建对象实例
			Object instance = clz.newInstance();
			// 读取要调用的方法
			Method method = clz.getMethod(opt, HttpServletRequest.class, HttpServletResponse.class);
			// 调用该方法
			Object obj = method.invoke(instance, req, resp);

			if (obj != null) {
				apiresult.setData(obj);
			}

		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			// 获取异常的根本原因
			Throwable targetE = e.getTargetException();
			if (targetE instanceof BaseException) {
				BaseException srcException = (BaseException) targetE;
				apiresult.setCode(srcException.getCode());
				apiresult.setMsg(srcException.getMessage());
			} else {
				e.printStackTrace();
				apiresult.setCode(500);
				apiresult.setMsg("程序内部错误");
			}
		} catch (ReflectiveOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiresult.setCode(E.PATH_ERROR_CODE);
			apiresult.setMsg(E.PATH_ERROR_INFO);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			apiresult.setCode(e.getCode());
			apiresult.setMsg(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiresult.setCode(500);
			apiresult.setMsg("程序内部错误 ");
		}

		String reStr = objMapper.writeValueAsString(apiresult);
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(reStr);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
