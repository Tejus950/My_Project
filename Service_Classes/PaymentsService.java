package com.hibernate.service;

import org.hibernate.SessionFactory;

import com.hibernate.entity.Payments;

public interface PaymentsService {
	
    void insertPayment(SessionFactory sf );
	
	void updatePayment(SessionFactory sf , Integer userId);
	
	void deletePayment(SessionFactory sf , Integer userId);
	
	void getAllPayment(SessionFactory sf);
	
	Payments  getPaymentById(SessionFactory sf, Integer userId);

}