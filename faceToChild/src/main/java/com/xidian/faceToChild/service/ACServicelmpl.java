package com.xidian.faceToChild.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xidian.faceToChild.mapper.ACMapper;
import com.xidian.faceToChild.po.mainContext;
import com.xidian.faceToChild.po.subContext;
import com.xidian.faceToChild.util.D;
import com.xidian.faceToChild.web.Page;

public class ACServicelmpl implements ACService {
	private ACMapper acmapper;

	public ACServicelmpl() {
		SqlSession session = D.getConn();
		acmapper = session.getMapper(ACMapper.class);
	}

	public void addNewMainContext(mainContext maincontext) {
		acmapper.addNewMainContext(maincontext);
	}

	public void addNewSubContext(subContext subcontext) {
		acmapper.addNewSubContext(subcontext);
	}

	public Page<mainContext> list(int pageNum, int pageSize, String keyword) {
		int total = acmapper.getMainContextByKeywordCount(keyword);
		int begin = (pageNum - 1) * pageSize;
		List<mainContext> datas = acmapper.getMainContextByKeyword(begin, pageSize, keyword);

		Page<mainContext> pageData = new Page<mainContext>(datas, total, pageSize, pageNum);
		return pageData;

	}

}
