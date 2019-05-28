package com.xidian.faceToChild.service;

import com.xidian.faceToChild.po.User;

public interface UserService {
	/**
	 * 登录接口
	 * 
	 * @param username
	 * @param password
	 */
	User login(String username, String password);

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 删除教师（逻辑删除）
	 * 
	 * @param id
	 * @return
	 */

	void deleteUser(Integer id);

	/**
	 * 修改用户信息
	 * @param user
	 */
	
	void updateUser(User user);
}
