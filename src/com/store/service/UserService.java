package com.store.service;

import java.sql.SQLException;

import com.store.domain.Manager;
import com.store.domain.User;

public interface UserService {

	void regist(User user)throws Exception ;

	User active(String code)throws Exception;

	User login(String username, String password) throws Exception;

	Manager adminlogin(String username, String password) throws SQLException;

}
