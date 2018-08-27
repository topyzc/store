package com.store.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.store.dao.UserDao;
import com.store.domain.Manager;
import com.store.domain.User;
import com.store.untils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	@Override
	/**
	 * 用户注册
	 */
	public void save(User user) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName()
				,user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex()
				,user.getState(),user.getCode()
				);
	}


	/**
	 * 更新用户
	 */
	public void update(User user) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update user set password=?,sex=?,state=?,code=? where uid=?";
		qr.update(sql,user.getPassword(),user.getSex(),user.getState(),user.getCode(),user.getUid());
	
	}

	/**
	 * 通过激活码获取用户
	 */
	public User getByCode(String code) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where code=? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class),code);
	}


	/**
	 * 用户登录
	 */
	@Override
	public User getByUsernameAndPwd(String username, String password) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username=? and password=? limit 1";
		
		return qr.query(sql, new BeanHandler<>(User.class),username,password);
	}


	@Override
	public Manager adminlogin(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from manager where username=? and password=? ";
		
		return qr.query(sql, new BeanHandler<>(Manager.class),username,password);
	}

}
