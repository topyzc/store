package com.store.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.service.CategoryService;
import com.store.service.impl.CategoryServiceImpl;

@WebServlet("/category")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * 查询所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//设置响应编码
		response.setContentType("text/html;charset=utf-8");
		
		//1.调用service,查询所有分类，返回值，json字符串
		CategoryService cs=new CategoryServiceImpl() ;
		//从 mysql中获取
		String value=cs.findAll();
		
		//从redis中获取列表
		//String value=cs.findAllFromRedis();
		//2.将字符串上传浏览器
		response.getWriter().println(value);
		return null;
	
		
	}

}
