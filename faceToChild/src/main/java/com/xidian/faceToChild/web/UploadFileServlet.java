package com.xidian.faceToChild.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.xidian.faceToChild.util.V;

@WebServlet("/media/upload")
@MultipartConfig
public class UploadFileServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] params = {"file_desc", "type"};
		V.valid(req, params);
		Part part = req.getPart("file");
		String type = part.getContentType();
		long size = part.getSize();
		String name = part.getName();
		String dirPath = "/upload_file/images/";

		String baseDir = this.getServletContext().getRealPath(dirPath) + "\\";
		File file = new File(dirPath);
		//如果文件夹不存在就创建文件夹
		if(!file.exists()) {
			file.mkdirs();
		}
		//获取当前的系统时间并且转化为16进制毫秒
		//用来生成文件名字
		String fileName = Long.toHexString(System.currentTimeMillis());
		if (type.endsWith("jpeg")) {
			fileName = fileName + ".jpg";
		} else {
			return;
		}

		baseDir += fileName;
		part.write(baseDir);

		System.out.println(dirPath + fileName);
		
	}
}
