package com.store.dao;

import java.util.List;

import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.PageBean;

public interface OrderDao {

	void save(Order order) throws Exception;

	void saveItem(OrderItem oi) throws Exception;

	int getTotalRecord(String uid)throws Exception;

	List<Order> findMyOrdersByPage(PageBean<Order> pb, String uid)throws Exception;

	Order getById(String oid)throws Exception;

	void update(Order order) throws Exception;

	List<Order> findAllByState(String state) throws Exception;


	

}
