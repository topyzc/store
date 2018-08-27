package com.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.store.domain.Order;
import com.store.domain.PageBean;
import com.store.domain.Product;

public interface ProductDao {

	List<Product> findHot() throws Exception;

	List<Product> findNew()throws Exception;

	Product getById(String pid) throws Exception;

	List<Product> findByPage(PageBean<Product> pb, String cid) throws Exception;

	int getTotalRecord(String cid) throws Exception;

	List<Product> findAll() throws Exception;

	void save(Product p)throws Exception;

	void del(String pid) throws SQLException;

	Product findById(String pid) throws SQLException;
	


	List<Product> findMyPruductsByPage(PageBean<Product> pb)throws Exception;

}
