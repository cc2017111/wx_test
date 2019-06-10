package com.xidian.faceToChild.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.xidian.faceToChild.exception.InvalidParamException;
import com.xidian.faceToChild.po.ACPicture;
import com.xidian.faceToChild.po.Media;
import com.xidian.faceToChild.po.User;
import com.xidian.faceToChild.service.FileService;
import com.xidian.faceToChild.service.FileServicelmpl;
import com.xidian.faceToChild.util.E;
import com.xidian.faceToChild.util.V;

public class FileController {
	private FileService fileservice;

	public FileController() {
		fileservice = new FileServicelmpl();
	}

	/**
	 * 文件上传接口
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public String upload(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
		String[] params = { "file_desc", "type" };
		V.valid(req, params);

		Media media = V.entity(req, Media.class, params);
		User user = (User) req.getSession().getAttribute("userInfo");
		media.setUser(user);
		Part part = req.getPart("file");
		if (part == null) {
			throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE, E.INVALID_PARAM_ERROR_INFO);
		}
		String path = (String) req.getAttribute("upload_path");
		String filePath = fileservice.UploadFile(path, part, media);
		return filePath;
	}
	
	public String addACPicture(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
		String[] params = { "from_AC", "file_desc", "type" };
		V.valid(req, params);

		ACPicture acPicture = V.entity(req, ACPicture.class, params);
		User user = (User) req.getSession().getAttribute("userInfo");
		acPicture.setCreateByUser(user.getId());
		Part part = req.getPart("file");
		if (part == null) {
			throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE, E.INVALID_PARAM_ERROR_INFO);
		}
		String path = (String) req.getAttribute("upload_path");
		String filePath = fileservice.UploadACPicture(path, part, acPicture);
		return filePath;
	}
	/**
	 * 获取用户头像
	 * @param req
	 * @param resp
	 * @return
	 */
	public Media found(HttpServletRequest req, HttpServletResponse resp){
		User user = (User) req.getSession().getAttribute("userInfo");
		
		Media media = fileservice.found(user.getId());
		return media;
	}
	/**
	 * 根据论坛id获取论坛图片
	 * @param req
	 * @param resp
	 * @return
	 */
	public ACPicture foundACPicture(HttpServletRequest req, HttpServletResponse resp){
		String from_ac = V.getValue(req, "from_ac", "");
		
		ACPicture acpicture = fileservice.findPictureByMainContextId(from_ac);
		return acpicture;
	}
}
