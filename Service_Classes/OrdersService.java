package com.hibernate.service;

import org.hibernate.SessionFactory;

public interface OrdersService {
	
	void insertOrder(SessionFactory sf);

	void updateOrder(SessionFactory sf , Integer orderId);

	void deleteOrder(SessionFactory sf , Integer orderId);

	void getAllOrders(SessionFactory sf);

	void getOrderById(SessionFactory sf , Integer orderId);


}

