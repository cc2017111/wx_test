package com.xidian.faceToChild.service;

import com.xidian.faceToChild.po.mainContext;
import com.xidian.faceToChild.po.subContext;

public interface ACService {
	
	/**
	 * 用户发表新帖子
	 * @param maincontext
	 */
	void addNewMainContext(mainContext maincontext);
	
	/**
	 * 用户发表新回复帖子 
	 * @param subcontext
	 */
	void addNewSubContext(subContext subcontext);
}
