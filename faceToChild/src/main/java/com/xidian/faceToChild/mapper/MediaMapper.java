package com.xidian.faceToChild.mapper;

import com.xidian.faceToChild.po.ACPicture;
import com.xidian.faceToChild.po.Media;

public interface MediaMapper {

	void insert(Media media);
	
	Media findMediaByUserId(Integer create_by);
	
	void insertACPicture(ACPicture acpicture);
	
	ACPicture findPictureByMainContextId(Long fromAC);
}
