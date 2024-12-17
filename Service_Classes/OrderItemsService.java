package com.hibernate.service;

import org.hibernate.SessionFactory;

public interface OrderItemsService {
	
	void insertOrderItem(SessionFactory sf);
	
	void updateOrderItem(SessionFactory sf , Integer orderItemId);
	
	void deleteOrderItem(SessionFactory sf , Integer orderItemId);
	
	void getAllOrderItems(SessionFactory sf);
	
    void getOrderItemById(SessionFactory sf, Integer orderItemId);

}
