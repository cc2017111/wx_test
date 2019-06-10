package com.xidian.faceToChild.service;

import com.xidian.faceToChild.po.mainContext;
import com.xidian.faceToChild.po.subContext;
import com.xidian.faceToChild.web.Page;

public interface ACService {

	/**
	 * 用户发表新帖子
	 * 
	 * @param maincontext
	 */
	void addNewMainContext(mainContext maincontext);

	/**
	 * 用户发表新回复帖子
	 * 
	 * @param subcontext
	 */
	void addNewSubContext(subContext subcontext);

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 *            页码
	 * @param PageSize
	 *            每页大小
	 * @param keyword
	 *            关键字
	 */
	Page<mainContext> list(int pageNum, int PageSize, String keyword);
}
