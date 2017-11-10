package com.yoyiyi.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {

	// 连接池
	private static DataSource sDataSource = new ComboPooledDataSource();

	private static ThreadLocal<Connection> sTl = new ThreadLocal<Connection>();

	// 直接可以获取一个连接池
	public static DataSource getDataSource() {
		return sDataSource;
	}

	// 获取连接对象
	public static Connection getConnection() throws SQLException {
		Connection conn = sTl.get();
		if (conn == null) {
			conn = sDataSource.getConnection();
			sTl.set(conn);
		}
		return conn;
	}

	// 开启事务
	public static void startTransaction() throws SQLException {
		Connection conn = getConnection();
		if (conn != null) {
			conn.setAutoCommit(false);
		}
	}

	// 事务回滚
	public static void rollback() throws SQLException {
		Connection conn = getConnection();
		if (conn != null) {
			conn.rollback();
		}
	}

	// 提交并且 关闭资源及从ThreadLocall中释放
	public static void commitAndRelease() throws SQLException {
		Connection conn = getConnection();
		if (conn != null) {
			conn.commit(); // 事务提交
			conn.close();// 关闭资源
			sTl.remove();// 从线程绑定中移除
		}
	}

	// 关闭资源方法
	public static void closeConnection() throws SQLException {
		Connection conn = getConnection();
		if (conn != null) {
			conn.close();
		}
	}

	public static void closeStatement(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}

	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

}
