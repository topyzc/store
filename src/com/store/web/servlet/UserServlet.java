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
import com.store.domain.User;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;
import com.store.untils.UUIDUtils;


public class UserServlet extends BaseServlet {
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
			User user=us.login(username,password);
			System.out.println(user);
			//3.判断user ,根据结果生成提示
			if(user==null) {
				//用户名和密码不正确
				request.setAttribute("msg", "用户名和密码不正确");
				return "/jsp/login.jsp";
			}
			//若用户不为空，继续判断身份激活
			if(Constant.USER_IS_ACTIVE!=user.getState()) {
				//未激活
				request.setAttribute("msg", "请先去激活，再登录");
				return "/jsp/msg.jsp";
			}
			//登录成功，保存用户的session
			request.getSession().setAttribute("user", user);
			if(Constant.SAVE_NAME.equals(request.getParameter("savename"))) {
				Cookie c=new Cookie("saveName",URLEncoder.encode(username, "utf-8"));
				c.setMaxAge(Integer.MAX_VALUE);
				c.setPath(request.getContextPath());
				response.addCookie(c);
			}
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("msg", "登录失败");
			return "/jsp/msg.jsp";
		}
		return null;
	}
	/**
	 * 跳转到登录页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 return "/jsp/login.jsp";
	}
	/**
	 * 用户激活 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)  {
		try {
			//1.接收code
			String code=request.getParameter("code");
			System.out.println(code);
			//2.调用service完成激活，返回值：user
			UserService us=new UserServiceImpl();
			User user=us.active(code);
			//3.判断user生成不同的提示信息
			if(user == null) {
				//没有找到这个用户，激活失败
				request.setAttribute("msg", "激活失败，请重新激活或者重新注册");
			  return "/jsp/msg.jsp";
			}
			
			//激活成功
			request.setAttribute("msg", "恭喜你，激活成功，可以登录了");
		} catch (Exception e) {
			
			request.setAttribute("msg", "激活失败，请重新激活或者重新注册");
			  return "/jsp/msg.jsp";
		}
		return "/jsp/msg.jsp";
	}
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.封装对象
			User user=new User();
			BeanUtils.populate(user, request.getParameterMap());
			//1.1手动封装对象uid, state,code
			  
			 user.setUid(UUIDUtils.getId());
			 user.setState(Constant.USER_IS_NOT_ACTIVE);
			 user.setCode(UUIDUtils.getCode());
			 user.setTelephone("15970321953");
			//2.调用service完成注册
			UserService us=new UserServiceImpl() ;
			us.regist(user);
			
			//3. 页面转发提示信息
			request.setAttribute("msg", "用户注册成功，请到邮箱激活！");
		}catch (Exception e) {
			
			e.printStackTrace();
			//转发到msg.jsp
			request.setAttribute("msg", "用户注册失败！");
			return "/jsp/msg.jsp";
		}
		return "/jsp/msg.jsp";
	}
   /**
    * 跳转到注册页面
    * @param request
    * @param response
    * @return
    * @throws ServletException
    * @throws IOException
    */
	
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/register.jsp";
	}
   
	
	

}
