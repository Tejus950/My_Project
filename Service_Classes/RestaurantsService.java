package com.hibernate.service;

import org.hibernate.SessionFactory;

import com.hibernate.entity.Restaurants;

public interface RestaurantsService {
	
	void insertRestaurant(SessionFactory sf);
	
    void updateRestaurant(SessionFactory sf, Integer restaurantId);
    
    void deleteRestaurant(SessionFactory sf, Integer restaurantId);
    
    void getAllRestaurants(SessionFactory sf);
    
    Restaurants getRestaurantById(SessionFactory sf, Integer restaurantId);
}



