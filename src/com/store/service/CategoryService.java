package com.store.service;

import java.sql.SQLException;
import java.util.List;

import com.store.domain.Category;

public interface CategoryService {

	String findAll()throws Exception;

	String findAllFromRedis()throws Exception;

	List<Category> findList() throws Exception;

	void save(Category c) throws Exception;

	void del(String cid)throws Exception;

	Category findById(String cid) throws SQLException;

	void update(Category c) throws SQLException;

}
