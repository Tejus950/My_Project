package com.hibernate.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.entity.Payments;
import com.hibernate.entity.Restaurants;
import com.hibernate.service.PaymentsService;

public class PaymentsServiceImpl  implements PaymentsService{
	
		Scanner sc = new Scanner(System.in);

		@Override
		public void insertPayment(SessionFactory sf) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Payments payment = new Payments();

	        System.out.println("Enter Order ID:");
	        payment.setOrderID(sc.nextInt());

	        System.out.println("Enter Amount:");
	        payment.setAmount(sc.nextDouble());

	        System.out.println("Enter Payment Method:");
	        payment.setPaymentMethod(sc.next());

	        System.out.println("Enter Payment Status:");
	        payment.setPaymentStatus(sc.next());

	        session.save(payment);
	        transaction.commit();
	        session.close();
	        System.out.println("Payment inserted successfully.");
			
		}

		@Override
		public void updatePayment(SessionFactory sf, Integer paymentId) {
			
			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Payments payment = session.get(Payments.class, paymentId);
	        if (payment != null) {
	            System.out.println("Current Amount: " + payment.getAmount());
	            System.out.print("Enter new Amount (enter -1 to keep current): ");
	            double newAmount = sc.nextDouble();
	            if (newAmount != -1) {
	                payment.setAmount(newAmount);
	            }

	            System.out.println("Current Payment Method: " + payment.getPaymentMethod());
	            System.out.print("Enter new Payment Method (leave blank to keep current): ");
	            String newMethod = sc.next();
	            if (!newMethod.isEmpty()) {
	                payment.setPaymentMethod(newMethod);
	            }

	            System.out.println("Current Payment Status: " + payment.getPaymentStatus());
	            System.out.print("Enter new Payment Status (leave blank to keep current): ");
	            String newStatus = sc.next();
	            if (!newStatus.isEmpty()) {
	                payment.setPaymentStatus(newStatus);
	            }

	            session.update(payment);
	            transaction.commit();
	            System.out.println("Payment updated successfully.");
	        } else {
	            System.out.println("Payment not found with ID: " + paymentId);
	        }
	        session.close();
			
		}

		@Override
		public void deletePayment(SessionFactory sf, Integer paymentId) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Payments payment = session.get(Payments.class, paymentId);
	        if (payment != null) {
	            session.delete(payment);
	            transaction.commit();
	            System.out.println("Payment with ID " + paymentId + " has been deleted successfully.");
	        } else {
	            System.out.println("Payment not found with ID: " + paymentId);
	        }
	        session.close();
		}

		@Override
		public void getAllPayment(SessionFactory sf) {

			Session session = sf.openSession();
	        Query<Payments> query = session.createQuery("from Payments", Payments.class);
	        List<Payments> payments = query.getResultList();

	        for (Payments payment : payments) {
	            System.out.println(payment);
	        }
	        session.close();
			
		}

		@Override
		public Payments  getPaymentById(SessionFactory sf, Integer paymentId) {

			Session session = sf.openSession();
	        Payments payment = session.get(Payments.class, paymentId);
	
	        if (payment != null) {
	            System.out.println(payment);
	        } else {
	            System.out.println("Payment not found with ID: " + paymentId);
	        }
	        session.close();
			return payment;
	    }
	}