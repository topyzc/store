package com.store.constant;

public interface Constant {
	/**
	 * 用户未激活
	 */
	int USER_IS_NOT_ACTIVE=0;
	
    /**
     * 用户已激活
     * 
     */
	int USER_IS_ACTIVE=1;
	
	String SAVE_NAME="ok";
	
	/**
	 * redis中存储分类列表的key
	 */
	String STORE_CATEGORY_LIST="STORE_CATEGORY_LIST";
	
	/**
	 * redis的服务器地址
	 */
	String REDIS_HOST="192.168.18.32";
	/**
	 * redis的端口号
	 */
	int REDIS_PORT=6379;
	/**
	 * 热门商品
	 * 
	 * 
	 */
	int PRODUCT_IS_HOT=0;
	/**
	 * 商品上架
	 */
	int PRODUCT_IS_UP=0;
	/**
	 * 商品下架
	 */
	int PRODUCT_IS_DOWN=1;
	

	/**
	 * 订单状态 未付款
	 */
	int ORDER_WEIFUKUAN=0;
	
	/**
	 * 订单状态 已付款
	 */
	int ORDER_YIFUKUAN=1;
	
	/**
	 * 订单状态 已发货
	 */
	int ORDER_YIFAHUO=2;
	
	/**
	 * 订单状态 已完成
	 */
	int ORDER_YIWANCHENG=3;
}
