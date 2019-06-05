package com.xidian.faceToChild.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xidian.faceToChild.po.mainContext;
import com.xidian.faceToChild.po.subContext;
import com.xidian.faceToChild.service.ACService;
import com.xidian.faceToChild.service.ACServicelmpl;
import com.xidian.faceToChild.util.V;

public class ACController {
	private ACService acservice;
	
	public ACController() {
		this.acservice = new ACServicelmpl();
	}
	
	public void addMainContext(HttpServletRequest req, HttpServletResponse rep) {
		String[] params = {"title", "context", "userID"};
		V.valid(req, params);
		
		mainContext maincontext = V.entity(req, mainContext.class, params);
		acservice.addNewMainContext(maincontext);
	}
	
	public void addSubContext(HttpServletRequest req, HttpServletResponse rep) {
		String[] params = {"title", "context", "userID", "fatherID", "mainID"};
		V.valid(req, params);
		
		subContext subcontext = V.entity(req, subContext.class, params);
		acservice.addNewSubContext(subcontext);
	}
}
