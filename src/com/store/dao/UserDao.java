package com.store.dao;

import java.sql.SQLException;

import com.store.domain.Manager;
import com.store.domain.User;

public interface UserDao {

	void save(User user)throws Exception ;

	void update(User user)throws Exception;

	User getByCode(String code)throws Exception;

	User getByUsernameAndPwd(String username, String password)throws Exception;

	Manager adminlogin(String username, String password) throws SQLException;
	
	

}
