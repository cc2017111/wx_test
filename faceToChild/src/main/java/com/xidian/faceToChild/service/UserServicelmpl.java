package com.xidian.faceToChild.service;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.apache.ibatis.session.SqlSession;

import com.xidian.faceToChild.exception.InvalidParamException;
import com.xidian.faceToChild.mapper.UserMapper;
import com.xidian.faceToChild.po.User;
import com.xidian.faceToChild.util.D;
import com.xidian.faceToChild.util.E;
import com.xidian.faceToChild.util.Global;
import com.xidian.faceToChild.util.Md5Util;

public class UserServicelmpl implements UserService {

	private UserMapper usermapper;

	public UserServicelmpl() {
		SqlSession session = D.getConn();
		usermapper = session.getMapper(UserMapper.class);
	}

	/**
	 * 用户登录
	 */
	public User login(String username, String password) {

		User user = usermapper.findUserByNickname(username);
		System.out.println(username);
		if (user == null) {
			throw new InvalidParamException(E.USER_INFO_ERROR_CODE, E.USER_INFO_ERROR_INFO);
		}
		// md5加密
		String encodePwd = Md5Util.encode(password);

		if (!user.getPassword().equalsIgnoreCase(encodePwd)) {
			throw new InvalidParamException(E.USER_INFO_ERROR_CODE, E.USER_INFO_ERROR_INFO);
		}
		return user;
	}

	/**
	 * 添加用户
	 */
	public void addUser(User user) {
		user.setRole(Global.ROLE_USER);
		user.setStatus(Global.ROLE_STATUS_ON);
		String encodePwd = Md5Util.encode(user.getPassword());
		user.setPassword(encodePwd);
		usermapper.addUser(user);
	}

	/**
	 * 删除用户（逻辑删除）
	 */
	public void deleteUser(Integer id) {
		User existUser = usermapper.findUserById(id);
		if(existUser == null) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "用户不存在");
		}
		User user = new User();
		user.setId(id);
//		user.setStatus(Global.ROLE_STATUS_OFF);
		usermapper.updateUser(user);
	}
	
	/**
	 * 更改用户信息
	 */

	public void updateUser(User user) {
		User existUser = usermapper.findUserById(user.getId());
		if(existUser == null) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "用户不存在");
		}
		
		usermapper.updateUser(user);
	}

}
