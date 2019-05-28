package com.xidian.faceToChild.mapper;

import com.xidian.faceToChild.po.User;

public interface UserMapper {
	/**
	 * 根据昵称查找用户
	 * @param username
	 * @return
	 */
	User findUserByNickname(String nickname);
	
	/**
	 * 根据用户id查找用户
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);
	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(User user);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	void updateUser(User user);
}
