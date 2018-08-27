package com.store.dao.impl;


import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.constant.Constant;
import com.store.dao.ProductDao;
import com.store.domain.Category;
import com.store.domain.Order;
import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.untils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {
	private QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	
	@Override
	public List<Product> findHot() throws Exception {
		
	     String sql="select * from product where is_hot=? and pflag=? order by pdate desc limit 9";
	     return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_HOT,Constant.PRODUCT_IS_UP);
	}

	@Override
	public List<Product> findNew() throws Exception {
		
		String sql = "select * from product where pflag = ? order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class),Constant.PRODUCT_IS_UP);
	}

	@Override
	/**
	 * 查询单个商品
	 */
	public Product getById(String pid) throws Exception {
		String sql="select * from product where pid=? limit 1";
		return qr.query(sql, new BeanHandler<>(Product.class),pid);
	}

	


	@Override
	/**
	 * 获取总记录数
	 */
	public int getTotalRecord(String cid) throws Exception {
	
		return ((Long)new QueryRunner(DataSourceUtils.getDataSource()).query("select count(*) from product where cid=? and pflag=?", new ScalarHandler(),cid,Constant.PRODUCT_IS_UP)).intValue();
	}

	@Override
	/**
	 * 查询当前页数
	 */
	public List<Product> findByPage(PageBean<Product> pb, String cid) throws Exception {
        String sql="select * from product where cid=? and pflag=? order by pdate desc limit ?,?";
		
		return qr.query(sql, new BeanListHandler<>(Product.class),cid,Constant.PRODUCT_IS_UP,pb.getStartIndex(),pb.getPageSize());
	}

	/**
	 * 后台查询所有商品
	 * @throws SQLException 
	 */
	@Override
	public List<Product> findAll() throws SQLException {
	    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	    String sql="select * from product where pflag=? order by pdate desc";
		return qr.query(sql, new BeanListHandler<>(Product.class),Constant.PRODUCT_IS_UP);
	}
	
	@Override
	/**
	 * 保存商品
	 */
	public void save(Product p) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?);";
		qr.update(sql, p.getPid(),p.getPname(),p.getMarket_price(),
				p.getShop_price(),p.getPimage(),p.getPdate(),
				p.getIs_hot(),p.getPdesc(),p.getPflag(),
				p.getCategory().getCid());
	}

	@Override
	public void del(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where pid=?;";
		qr.update(sql, pid);
		
	}

	@Override
	public Product findById(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=?;";
		return (Product) qr.query(sql,new BeanHandler<>(Product.class),pid);
	}

	@Override
	public List<Product> findMyPruductsByPage(PageBean<Product> pb) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//查询所有商品(基本信息)
		String sql="select * from product limit ?,?";
		List<Product> list = qr.query(sql, new BeanListHandler<>(Product.class),pb.getStartIndex(),pb.getPageSize());
		return list;
	}


}
