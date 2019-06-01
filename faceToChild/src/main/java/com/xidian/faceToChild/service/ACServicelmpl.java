package com.xidian.faceToChild.service;

import org.apache.ibatis.session.SqlSession;

import com.xidian.faceToChild.mapper.ACMapper;
import com.xidian.faceToChild.po.mainContext;
import com.xidian.faceToChild.po.subContext;
import com.xidian.faceToChild.util.D;

public class ACServicelmpl implements ACService{
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
	
}
