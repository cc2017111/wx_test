package com.xidian.faceToChild.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;

import com.xidian.faceToChild.exception.InvalidParamException;
import com.xidian.faceToChild.mapper.MediaMapper;
import com.xidian.faceToChild.po.ACPicture;
import com.xidian.faceToChild.po.Media;
import com.xidian.faceToChild.util.D;
import com.xidian.faceToChild.util.E;
import com.xidian.faceToChild.util.Global;
import com.xidian.faceToChild.util.MediaValid;

public class FileServicelmpl implements FileService {

	private static final String IMAGES_PATH = "/upload_files/images/";
	private static final String AUDIO_PATH = "/upload_files/audio/";
	private static final String VIDEO_PATH = "/upload_files/video/";

	private MediaMapper mediamapper;

	public FileServicelmpl() {
		SqlSession session = D.getConn();
		mediamapper = session.getMapper(MediaMapper.class);
	}

	public String UploadFile(String path, Part part, Media media) throws IOException {
		String UPloadDir = path;
		String fileName = Long.toHexString(System.currentTimeMillis());
		String sufix = "";
		String returnPath = null;
		if (media.getType() == Global.MEDIA_IMAGE) {
			UPloadDir += IMAGES_PATH;
			returnPath = IMAGES_PATH;
		} else if (media.getType() == Global.MEDIA_AUDIO) {
			UPloadDir += AUDIO_PATH;
			returnPath = AUDIO_PATH;
		} else if (media.getType() == Global.MEDIA_VIDEO) {
			UPloadDir += VIDEO_PATH;
			returnPath = VIDEO_PATH;
		} else {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "不支持的文件类型");
		}
		// 如果目录不存在就创建目录
		File file = new File(UPloadDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 验证图片格式和大小
		MediaValid mediaValid = new MediaValid();
		mediaValid.valid(part, media.getType());
		String orgName = part.getHeader("Content-Disposition");
		// 截取不同类型的文件需要自行判断
		sufix = orgName.substring(orgName.lastIndexOf("=") + 2, orgName.length() - 1);

		UPloadDir += fileName + sufix;
		// 返回给用户的路径
		returnPath += fileName + sufix;
		part.write(UPloadDir);
		media.setUrl(returnPath);
		mediamapper.insert(media);
		return returnPath;
	}

	public Media found(Integer user_id) {
		Media media = mediamapper.findMediaByUserId(user_id);
		return media;
	}

	public String UploadACPicture(String path, Part part, ACPicture acpicture) throws IOException {
		String UPloadDir = path;
		String fileName = Long.toHexString(System.currentTimeMillis());
		String sufix = "";
		String returnPath = null;
		if (acpicture.getType() == Global.MEDIA_IMAGE) {
			UPloadDir += IMAGES_PATH;
			returnPath = IMAGES_PATH;
		} else {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "不支持的文件类型");
		}
		// 如果目录不存在就创建目录
		File file = new File(UPloadDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 验证图片格式和大小
		MediaValid mediaValid = new MediaValid();
		mediaValid.valid(part, acpicture.getType());
		String orgName = part.getHeader("Content-Disposition");
		// 截取不同类型的文件需要自行判断
		sufix = orgName.substring(orgName.lastIndexOf("=") + 2, orgName.length() - 1);

		UPloadDir += fileName + sufix;
		// 返回给用户的路径
		returnPath += fileName + sufix;
		part.write(UPloadDir);
		acpicture.setUrl(returnPath);
		mediamapper.insertACPicture(acpicture);
		return returnPath;
	}

	public ACPicture findPictureByMainContextId(String from_ac) {
		Long fromAC = Long.parseLong(from_ac);
		ACPicture acpicture = mediamapper.findPictureByMainContextId(fromAC);
		return acpicture;
	}

}
