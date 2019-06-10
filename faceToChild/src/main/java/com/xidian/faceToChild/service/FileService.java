package com.xidian.faceToChild.service;

import java.io.IOException;

import javax.servlet.http.Part;

import com.xidian.faceToChild.po.ACPicture;
import com.xidian.faceToChild.po.Media;

public interface FileService {

	String UploadFile(String path, Part part, Media media) throws IOException;
	
	String UploadACPicture(String path, Part part, ACPicture acpicture) throws IOException;
	
	Media found(Integer user_id);
	
	ACPicture findPictureByMainContextId(String fromAC);
}
