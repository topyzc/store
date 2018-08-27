package com.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.store.constant.Constant;
import com.store.domain.Manager;
import com.store.domain.User;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;
import com.store.untils.UUIDUtils;

@WebServlet("/adminUser")
public class AdminUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
      
	
	/**
	 * 退出功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();
		
	    response.sendRedirect(request.getContextPath());
	    
	    return null;
		
	}
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			System.out.println(username+password);
			//2.调用service完成登录
			UserService us=new UserServiceImpl();
			User mgr=us.login(username,password);
			System.out.println(mgr);
			//3.判断user ,根据结果生成提示
			if(mgr==null) {
				//用户名和密码不正确
				request.setAttribute("msg", "用户名和密码不正确");
				return "/admin/index.jsp";
			}
			
			//登录成功，保存用户的session
			request.getSession().setAttribute("mgr", mgr);
			
			response.sendRedirect(request.getContextPath()+"/admin/home.jsp");
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("msg", "登录失败");
			return "/jsp/msg.jsp";
		}
		return null;
	}
	
}
