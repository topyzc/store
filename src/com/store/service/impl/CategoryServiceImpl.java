package com.store.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.store.constant.Constant;
import com.store.dao.CategoryDao;
import com.store.dao.impl.CategoryDaoImpl;
import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.untils.BeanFactory;
import com.store.untils.JedisUtils;
import com.store.untils.JsonUtil;

import redis.clients.jedis.Jedis;



public class CategoryServiceImpl implements CategoryService {

	/**
	 * 后台查询所有分类
	 * @throws Exception 
	 */
	@Override
	public List<Category> findList() throws Exception {
		CategoryDao cd=(CategoryDao) BeanFactory.getBean("CategoryDao");
		return cd.findAll();
	}
	@Override
	/**
	 * 查询所有分类
	 */
	public String findAll() throws Exception {

		//1.调用dao 查询所有的分类
		// CategoryDao cd=new CategoryDaoImpl();
		//CategoryDao cd=(CategoryDao) BeanFactory.getBean("CategoryDao");
		 List<Category> list=findList();
		
		//2.将list转换成json字符串
		 if(list!=null && list.size()>0) {
			 return JsonUtil.list2json(list);
			 
		 }
		return null;
	}

	@Override
	/**
	 * 从redis中获取所有的分类
	 */
	public String findAllFromRedis() throws Exception {
		//1.获取jedis
		Jedis jedis = JedisUtils.getJedis();
		
		//2.从redis中获取数据
		String value = jedis.get(Constant.STORE_CATEGORY_LIST);
		
		//3.判断数据是否为空
		if(value == null){
			//3.1若为空 ,调用findAll() 将查询的结果放入redis return
			value = findAll();
			
			jedis.set(Constant.STORE_CATEGORY_LIST, value);
			System.out.println("从mysql中获取");
			return value;
		}
		//3.2若不为空,return
		System.out.println("从redis中获取");
		
		return value;
	}
	@Override
	/**
	 * 添加分类
	 */
	public void save(Category c) throws Exception {
		//1.调用dao 完成添加
		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
		cd.save(c);
		
		/*//2.更新redis
		Jedis j = null;
		try {
			j=JedisUtils.getJedis();
			//清除redis中数据
			j.del(Constant.STORE_CATEGORY_LIST);
		} finally {
			JedisUtils.closeJedis(j);
		}*/
	}
	@Override
	public void del(String cid) throws Exception {
		
		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
		
		cd.del(cid);
		
	}
	@Override
	public Category findById(String cid) throws SQLException {
		
		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
		return cd.findById(cid);
	}
	@Override
	public void update(Category c) throws SQLException {
		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
		 cd.update(c);
		
	}



}
