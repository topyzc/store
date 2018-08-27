package com.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.untils.BeanFactory;
import com.store.untils.UUIDUtils;

/**
 * 后台分类模块
 */
@WebServlet("/adminCategory")
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	
	public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String cid=request.getParameter("id");
		String cname=request.getParameter("cname");
		CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
		Category c = new Category();
		c.setCid(cid);
		c.setCname(cname);
		System.out.println(c);
		cs.update(c);
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		return null;
	}
	
	/**
	 * 修改分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String cid=request.getParameter("id");
		CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
		Category list=cs.findById(cid);
		System.out.println(list);
		request.setAttribute("list", list);
		return "/admin/category/edit.jsp";
	}
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 *  
	 */
	public String del(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String cid=request.getParameter("id");
			CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
			cs.del(cid);
			response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("msg", "删除失败！");
			return "/jsp/msg";
		}
		return null;
	}
	/**
	 * 添加分类
	 */
	
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.封装category对象
			Category c = new Category();
			c.setCid(UUIDUtils.getId());
			c.setCname(request.getParameter("cname"));
			
			//2.调用service完成添加操作
			CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
			cs.save(c);
			
			//3.重定向
			response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return null;
	}
	
	/**
	 * 跳转到添加页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/admin/category/add.jsp";
	}
	/**
	 * 展示所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			CategoryService cs=(CategoryService) BeanFactory.getBean("CategoryService");
			List<Category> list=cs.findList();
			
			request.setAttribute("list", list);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/category/list.jsp";
	}

}
