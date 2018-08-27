package com.store.service.impl;

import java.sql.SQLException;

import com.store.constant.Constant;
import com.store.dao.UserDao;
import com.store.dao.impl.UserDaoImpl;
import com.store.domain.Manager;
import com.store.domain.User;
import com.store.service.UserService;
import com.store.untils.BeanFactory;
import com.store.untils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	/**
	 * 用户注册
	 */
	public void regist(User user) throws Exception {
		//1.调用dao完成注册
		UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		ud.save(user);
		
		//2.发送邮件
		String emailMsg="恭喜"+user.getName()+":成为我们商城的一员,<a href='http://localhost:8080/store/user?method=active&code="+user.getCode()+"'>点此激活</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
	}

	/**
	 * 用户激活
	 * 
	 */
	@Override
	public User active(String code) throws Exception {
		//1.通过code获取用户
		UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		User user=ud.getByCode(code);
		System.out.println(user);
		//1.1通过激活码没有找到用户
		if(user ==null) {
			return null;
			
		}
		//2.若获取了，修改用户
		user.setState(Constant.USER_IS_ACTIVE);
		user.setCode(null);
		
		ud.update(user);
		return user;
	}
   
	/**
	 * 用户登录
	 * @throws Exception 
	 */
	@Override
	public User login(String username, String password) throws Exception {
		
		UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		return ud.getByUsernameAndPwd(username,password);
	}

	/**
	 * 管理员登录
	 */
	@Override
	public Manager adminlogin(String username, String password) throws SQLException {
		UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		return ud.adminlogin(username,password);
	}

}
