package com.yoyiyi.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yoyiyi.domain.User;
import com.yoyiyi.utils.DataSourceUtils;

public class UserDao {
	/**
	 * 获取QueryRunner
	 * 
	 * @return QueryRunner
	 */
	private QueryRunner getQueryRunner() {
		return new QueryRunner(DataSourceUtils.getDataSource());
	}

	/**
	 * 检查用户名是否存在
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean checkUsername(String username) throws SQLException {
		Long count = (Long) getQueryRunner().query("select count(*) from user where username=?",
				new ScalarHandler<Long>(), username);
		return count > 0 ? true : false;

	}

	/**
	 * 注册
	 * 
	 * @param user
	 * @return 个数
	 * @throws SQLException
	 * 
	 */
	public int register(User user) throws SQLException {
		return getQueryRunner().update("insert into user values(?,?,?,?,?,?,?,?,?,?)", user.getUid(),
				user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(),
				user.getBirthday(), user.getSex(), user.getState(), user.getCode());

	}

	/**
	 * 激活用户账号
	 * 
	 * @param activeCode
	 * @throws SQLException
	 */
	public void active(String activeCode) throws SQLException {
		getQueryRunner().update("update user set state=? where code=?", 1, activeCode);
	}

}
