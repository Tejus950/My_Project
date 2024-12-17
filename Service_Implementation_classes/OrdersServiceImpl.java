package com.hibernate.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.entity.Orders;
import com.hibernate.service.OrdersService;

public class OrdersServiceImpl implements OrdersService{
	
		Scanner sc = new Scanner(System.in);

		@Override
		public void insertOrder(SessionFactory sf) {
			
			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Orders order = new Orders();

	        System.out.println("Enter User ID:");
	        order.setUserID((long) sc.nextInt());

	        System.out.println("Enter Total Price:");
	        order.setTotalPrice(sc.nextDouble());

	        System.out.println("Enter Order Status:");
	        order.setOrderStatus(sc.next());

	        System.out.println("Enter Delivery Address:");
	        order.setDeliveryAddress(sc.next());

	        // Optionally set Timestamp (consider using LocalDateTime.now())
	        
	        session.save(order);
	        transaction.commit();
	        session.close();
	        
	        System.out.println("Order inserted successfully.");
	    }

		@Override
		public void updateOrder(SessionFactory sf, Integer orderId) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Orders order = session.get(Orders.class, orderId);
	        if (order != null) {
	            System.out.println("Current Order Status: " + order.getOrderStatus());
	            System.out.print("Enter new Order Status (leave blank to keep current): ");
	            String newStatus = sc.nextLine();
	            if (!newStatus.isEmpty()) {
	                order.setOrderStatus(newStatus);
	            }

	            System.out.println("Current Total Price: " + order.getTotalPrice());
	            System.out.print("Enter new Total Price (enter -1 to keep current): ");
	            double newPrice = sc.nextDouble();
	            if (newPrice != -1) {
	                order.setTotalPrice(newPrice);
	            }

	            System.out.println("Current Delivery Address: " + order.getDeliveryAddress());
	            System.out.print("Enter new Delivery Address (leave blank to keep current): ");
	            sc.nextLine(); // consume leftover newline
	            String newAddress = sc.nextLine();
	            if (!newAddress.isEmpty()) {
	                order.setDeliveryAddress(newAddress);
	            }

	            // Update the order in the database
	            session.update(order);
	            transaction.commit();
	            System.out.println("Order updated successfully.");
	        } else {
	            System.out.println("Order not found with ID: " + orderId);
	        }
	        session.close();
	    }

		@Override
		public void deleteOrder(SessionFactory sf, Integer orderId) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Orders order = session.get(Orders.class, orderId);
	        if (order != null) {
	            session.delete(order);
	            transaction.commit();
	            System.out.println("Order with ID " + orderId + " has been deleted successfully.");
	        } else {
	            System.out.println("Order not found with ID: " + orderId);
	        }
	        session.close();
		}

		@Override
		public void getAllOrders(SessionFactory sf) {

			Session session = sf.openSession();
	        Query<Orders> query = session.createQuery("from Orders", Orders.class);
	        List<Orders> orders = query.getResultList();

	        for (Orders order : orders) {
	            System.out.println(order);
	        }
	        session.close();
	    }

		@Override
		public void getOrderById(SessionFactory sf, Integer orderId) {

			Session session = sf.openSession();
	        Orders order = session.get(Orders.class, orderId);

	        if (order != null) {
	            System.out.println(order);
	        } else {
	            System.out.println("Order not found with ID: " + orderId);
	        }
	        session.close();
			
		}
	}