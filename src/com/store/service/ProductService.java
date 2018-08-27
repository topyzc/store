package com.store.service;

import java.sql.SQLException;
import java.util.List;

import com.store.domain.PageBean;
import com.store.domain.Product;

public interface ProductService {

	List<Product> findHot() throws Exception;

	List<Product> findNew()throws Exception;

	Product getById(String pid) throws Exception;

	PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception;

	List<Product> findAll() throws Exception;

	void save(Product p)throws Exception;

	void del(String pid) throws Exception;

	Product findById(String pid) throws SQLException;




}
