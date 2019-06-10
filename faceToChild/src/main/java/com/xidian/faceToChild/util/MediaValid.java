package com.xidian.faceToChild.util;

import javax.servlet.http.Part;

import com.xidian.faceToChild.exception.InvalidParamException;

public class MediaValid {
	private static final String[] IMAGE_TYPE = { "jpeg", "png", "gif" };
	private static final int IMAGE_LIMIT = 1024 * 1024;
	private static final String[] AUDIO_TYPE = { "mp3", "png", "gif" };
	private static final int AUDIO_LIMIT = 1024 * 1024 * 30;
	private static final String[] VIDEO_TYPE = { "mp4", "avi", "wmv" };
	private static final int VIDEO_LIMIT = 1024 * 1024 * 300;

	public void valid(Part part, int type) {
		String[] formats = null;
		int limitsize = 0;
		if (type == Global.MEDIA_IMAGE) {
			formats = IMAGE_TYPE;
			limitsize = IMAGE_LIMIT;
		} else if (type == Global.MEDIA_AUDIO) {
			formats = AUDIO_TYPE;
			limitsize = AUDIO_LIMIT;

		} else if (type == Global.MEDIA_VIDEO) {
			formats = VIDEO_TYPE;
			limitsize = VIDEO_LIMIT;
		}
		String fileType = part.getContentType();
		boolean isValidFormat = false;
		for (String format : formats) {
			if (fileType.endsWith(format)) {
				isValidFormat = true;
				break;
			}
		}

		if (!isValidFormat) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "上传文件格式不合法");
		}
			// 验证文件大小是否合法
		long size = part.getSize();
		if (size > limitsize) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "上传文件超过" + (limitsize / 1024 / 1024) + "M");
		}
		
	}
}
