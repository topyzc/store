package com.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.untils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao{

	/**
	 * 查询所有分类
	 */
	@Override
	public List<Category> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		return qr.query(sql,new BeanListHandler<>(Category.class));
	}
	
	@Override
	/**
	 * 添加分类
	 */
	public void save(Category c) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values (?,?);";
		qr.update(sql, c.getCid(),c.getCname());
	}

	/**
	 * 删除分类
	 * @throws SQLException 
	 */
	@Override
	public void del(String cid) throws SQLException {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from category where cid=?;";
		qr.update(sql, cid);
	}

	@Override
	public Category findById(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid=?;";
		return (Category) qr.query(sql,new BeanHandler<>(Category.class),cid);
	}

	@Override
	public void update(Category c) throws SQLException {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname=? where cid=?";
		qr.update(sql, c.getCname(),c.getCid());
	}

}
