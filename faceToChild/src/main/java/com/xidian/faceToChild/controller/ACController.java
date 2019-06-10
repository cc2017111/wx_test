package com.xidian.faceToChild.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xidian.faceToChild.po.User;
import com.xidian.faceToChild.po.mainContext;
import com.xidian.faceToChild.po.subContext;
import com.xidian.faceToChild.service.ACService;
import com.xidian.faceToChild.service.ACServicelmpl;
import com.xidian.faceToChild.util.V;
import com.xidian.faceToChild.web.Page;

public class ACController {
	private ACService acservice;

	public ACController() {
		this.acservice = new ACServicelmpl();
	}

	public void addMainContext(HttpServletRequest req, HttpServletResponse rep) {

		String[] params = {"id", "title", "context"};
		V.valid(req, params);
		User user = (User) req.getSession().getAttribute("userInfo");
		
		mainContext maincontext = V.entity(req, mainContext.class, params);
		maincontext.setUserID(user.getId());
		acservice.addNewMainContext(maincontext);
		
	}

	public void addSubContext(HttpServletRequest req, HttpServletResponse rep) {
		String[] params = { "title", "context", "userID", "fatherID", "mainID" };
		V.valid(req, params);

		subContext subcontext = V.entity(req, subContext.class, params);
		acservice.addNewSubContext(subcontext);
	}

	public Page<mainContext> list(HttpServletRequest req, HttpServletResponse resp) {
		String pageNumStr = V.getValue(req, "page_num", "1");
		String pageSizeStr = V.getValue(req, "page_size", "3");
		String keyword = V.getValue(req, "keyword", "");

		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);

		Page<mainContext> data = acservice.list(pageNum, pageSize, keyword);
		return data;
	}
}
