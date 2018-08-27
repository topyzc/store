package com.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<String, CartItem> itemMap = new HashMap<>();

	private Double total = 0.0;
	
	/**
	 * 获得所有的购物项
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return itemMap.values();
	}

	public Map<String, CartItem> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, CartItem> itemMap) {
		this.itemMap = itemMap;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * 加入购物车
	 * @param item
	 */
	public void add2cart(CartItem item) {
		//获取商品的id
		String pid=item.getProduct().getPid();
		//1.判断购物车中是否有
		if(itemMap.containsKey(pid)) {
			//有 修改数量=原来的数量+新加的数量
			CartItem oItem=itemMap.get(pid);
			
			oItem.setCount(oItem.getCount()+item.getCount());
			
		}else {
			itemMap.put(pid, item);
		}
		//修改总金额
		total+=item.getSubtotal();
	}
	/**
	 * 从购物车中移除一个购物项
	 */

    public void removeFromcart(String pid) {
		//1.从购物车中移除购物项
    	CartItem item=itemMap.remove(pid);
    	
    	//2.修改总金额
    	total -=item.getSubtotal();
	}
    /**
	 * 清空购物车
	 */

    public void clearcart() {
    	//1.清理空map集合
    	  itemMap.clear();
    	//2.修改总金额=0
		total=0.0;
	}
}
