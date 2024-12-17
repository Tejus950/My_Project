package com.hibernate.service;

import org.hibernate.SessionFactory;

public interface CategoriesService {

	void insertCategory(SessionFactory sf);
	
	void updateCategory(SessionFactory sf , Integer categoryId);
	
	void deleteCategory(SessionFactory sf , Integer categoryId);
	
	void getAllCategories(SessionFactory sf);
	
	void getCategoryById(SessionFactory sf, Integer categoryId);

}
