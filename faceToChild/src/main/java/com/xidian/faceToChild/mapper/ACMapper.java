package com.xidian.faceToChild.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xidian.faceToChild.po.mainContext;
import com.xidian.faceToChild.po.subContext;

public interface ACMapper {

	void addNewMainContext(mainContext maincontext);
	
	void addNewSubContext(subContext subContext);
	/**
	 * 返回符合条件的文章数量
	 * @param keyword
	 * @return
	 */
	int getMainContextByKeywordCount(@Param("keyword")String keyword);
	
	/**
	 * 返回符合条件的文章数据
	 * @param begin
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	List<mainContext> getMainContextByKeyword(@Param("begin")int begin, @Param("pageSize")int pageSize, @Param("keyword")String keyword);
}
