package com.hibernate.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.entity.OrderItems;
import com.hibernate.service.OrderItemsService;

public class OrderItemsServiceImpl implements  OrderItemsService{
	
		Scanner sc = new Scanner(System.in);

		@Override
		public void insertOrderItem(SessionFactory sf) {
			
			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        OrderItems orderItem = new OrderItems();

	        System.out.println("Enter Order ID:");
	        orderItem.setOrderID(sc.nextInt());

	        System.out.println("Enter Item ID:");
	        orderItem.setItemID(sc.nextInt());

	        System.out.println("Enter Quantity:");
	        orderItem.setQuantity(sc.nextInt());

	        System.out.println("Enter Price:");
	        orderItem.setPrice(sc.nextDouble());

	        session.save(orderItem);
	        transaction.commit();
	        session.close();
	        System.out.println("Order Item inserted successfully.");
	    }

		@Override
		public void updateOrderItem(SessionFactory sf, Integer orderItemId) {
			
			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        OrderItems orderItem = session.get(OrderItems.class, orderItemId);
	        if (orderItem != null) {
	            System.out.println("Current Quantity: " + orderItem.getQuantity());
	            System.out.print("Enter new Quantity (enter -1 to keep current): ");
	            int newQuantity = sc.nextInt();
	            if (newQuantity != -1) {
	                orderItem.setQuantity(newQuantity);
	            }

	            System.out.println("Current Price: " + orderItem.getPrice());
	            System.out.print("Enter new Price (enter -1 to keep current): ");
	            double newPrice = sc.nextDouble();
	            if (newPrice != -1) {
	                orderItem.setPrice(newPrice);
	            }

	            session.update(orderItem);
	            transaction.commit();
	            System.out.println("Order Item updated successfully.");
	        } else {
	            System.out.println("Order Item not found with ID: " + orderItemId);
	        }
	        session.close();
	    }

		@Override
		public void deleteOrderItem(SessionFactory sf, Integer orderItemId) {
			
			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        OrderItems orderItem = session.get(OrderItems.class, orderItemId);
	        if (orderItem != null) {
	            session.delete(orderItem);
	            transaction.commit();
	            System.out.println("Order Item with ID " + orderItemId + " has been deleted successfully.");
	        } else {
	            System.out.println("Order Item not found with ID: " + orderItemId);
	        }
	        session.close();
		}

		@Override
		public void getAllOrderItems(SessionFactory sf) {
			
			Session session = sf.openSession();
	        Query<OrderItems> query = session.createQuery("from OrderItems", OrderItems.class);
	        List<OrderItems> orderItems = query.getResultList();

	        for (OrderItems item : orderItems) {
	            System.out.println(item);
	        }
	        session.close();
		}

		@Override
		public void getOrderItemById(SessionFactory sf, Integer orderItemId) {
			
			Session session = sf.openSession();
	        OrderItems orderItem = session.get(OrderItems.class, orderItemId);

	        if (orderItem != null) {
	            System.out.println(orderItem);
	        } else {
	            System.out.println("Order Item not found with ID: " + orderItemId);
	        }
	        session.close();
	    }
	}