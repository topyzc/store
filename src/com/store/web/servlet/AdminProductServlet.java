package com.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.domain.Order;
import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.untils.BeanFactory;


@WebServlet("/adminProduct")
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
      
	
	
	/**
	 * 修改商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("pid");
		ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
		Product pt=ps.findById(pid);
		System.out.println(pt);
		//调用categoryservice 查询所有分类
		CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
		
		request.setAttribute("list", cs.findList());
		request.setAttribute("pt", pt);
		return "/admin/product/edit.jsp";
	}
	/**
	 * 跳转到添加的页面上
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//调用categoryservice 查询所有分类
			CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
			
			request.setAttribute("list", cs.findList());
		} catch (Exception e) {
		}
		return "/admin/product/add.jsp";
	}
	
     /**
      * 查询所有商品
      * @param request
      * @param response
      * @return
     * @throws Exception 
      */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		
    	ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
    
    	List<Product> list=ps.findAll();
    	
    	request.setAttribute("list", list);
    	return "/admin/product/list.jsp";
    }
    
    /**
	 * 删除商品
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 *  
	 */
	public String del(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			String pid=request.getParameter("id");
			ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
			ps.del(pid);
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("msg", "删除失败！");
			return "/jsp/msg";
		}
		return null;
	}
    
    

}
