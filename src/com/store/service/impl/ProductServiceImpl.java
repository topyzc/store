package com.store.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.store.dao.CategoryDao;
import com.store.dao.OrderDao;
import com.store.dao.ProductDao;
import com.store.domain.Order;
import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.untils.BeanFactory;

public class ProductServiceImpl implements ProductService{

	//private ProductDao pd=(ProductDao) BeanFactory.getBean("ProductDao");
	private ProductDao pd=(ProductDao) BeanFactory.getBean("ProductDao");
	@Override
	public List<Product> findHot() throws Exception {
		
		return pd.findHot();
	}

	@Override
	public List<Product> findNew() throws Exception {
		
		return pd.findNew();
	}

	@Override
	public Product getById(String pid) throws Exception {
		
		return pd.getById(pid);
	}

	
	/**
	 * 后台查询所有商品
	 * @throws Exception 
	 */
	@Override
	public List<Product> findAll() throws Exception {
		
		return pd.findAll();
	}

	@Override
	/**
	 * 保存商品
	 */
	public void save(Product p) throws Exception {
		ProductDao pDao= (ProductDao) BeanFactory.getBean("ProductDao");
		pDao.save(p);
		
	}

	@Override
	public void del(String pid) throws Exception {
		ProductDao pDao= (ProductDao) BeanFactory.getBean("ProductDao");
		pDao.del(pid);
	}

	@Override
	public Product findById(String pid) throws SQLException {
		ProductDao pDao= (ProductDao) BeanFactory.getBean("ProductDao");
		return pDao.findById(pid);
	}

	@Override
	/**
	 * 分页展示分类商品
	 */
	public PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception {
		ProductDao pDao= (ProductDao) BeanFactory.getBean("ProductDao");
		//1.创建pagebean
		PageBean<Product> pb = new PageBean<>(pageNumber, pageSize);
		
		//2.设置当前页数据
		List<Product> data = pDao.findByPage(pb,cid);
		pb.setData(data);
		
		//3.设置总记录数
		int totalRecord = pDao.getTotalRecord(cid);
		pb.setTotalRecord(totalRecord);
		
		return pb;
	}

}
