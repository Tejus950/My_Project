package com.hibernate.service;

import org.hibernate.SessionFactory;

public interface MenuItemsService {
	
	void insertMenuItem(SessionFactory sf );

	void updateMenuItem(SessionFactory sf , Integer itemId);

	void deleteMenuItem(SessionFactory sf , Integer itemId);

	void getAllMenuItems(SessionFactory sf);

	void getMenuItemById(SessionFactory sf, Integer itemId);

}

