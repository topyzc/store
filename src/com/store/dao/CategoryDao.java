package com.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.store.domain.Category;

public interface CategoryDao {

	List<Category> findAll()throws Exception;

	void save(Category c) throws Exception;

	void del(String cid) throws SQLException;

	Category findById(String cid) throws SQLException;

	void update(Category c) throws SQLException;

	

}
