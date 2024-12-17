package com.hibernate.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.hibernate.entity.Users;

public interface UsersService {
	
    void insertUser(SessionFactory sf );
	
	void updateUser(SessionFactory sf , Integer userId);
	
	void deleteUser(SessionFactory sf , Integer userId);
	
	Users getUserById(SessionFactory sf, Integer userId);
	
	List<Users> getAllUser(SessionFactory sf);
    
    Users login(SessionFactory sf, String email, String password); 
}

