package com.yoyiyi.service;

import java.sql.SQLException;

import com.yoyiyi.dao.UserDao;
import com.yoyiyi.domain.User;

public class UserService {
	/**
	 * 判断用户是否注册成功
	 * 
	 * @param user
	 * @return
	 */
	public boolean register(User user) {
		UserDao userDao = new UserDao();
		int row = 0;
		try {
			row = userDao.register(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row > 0 ? true : false;
	}

	public void active(String activeCode) {
		UserDao dao = new UserDao();
		try {
			dao.active(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkUsername(String username) {
		UserDao dao = new UserDao();
		try {
			Long isExit = dao.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
