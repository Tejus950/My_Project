package com.hibernate.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.entity.MenuItems;
import com.hibernate.service.MenuItemsService;

public class MenuItemsServiceImpl implements MenuItemsService{

		Scanner sc = new Scanner(System.in);
		
		@Override
		public void insertMenuItem(SessionFactory sf) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();
	        
	        MenuItems menuItem = new MenuItems();
	        
	        System.out.println("Enter Menu Item Name:");
	        menuItem.setName(sc.nextLine());
	        
	        System.out.println("Enter Menu Item Description:");
	        menuItem.setDescription(sc.nextLine());
	        
	        System.out.println("Enter Menu Item Price:");
	        menuItem.setPrice(sc.nextDouble());
	        
	        System.out.println("Enter Restaurant ID:");
	        menuItem.setRestaurantID(sc.nextInt());
	        
	        System.out.println("Enter Category ID:");
	        menuItem.setCategoryID(sc.nextInt());
	        
	        System.out.println("Is the item available? (true/false):");
	        menuItem.setAvailabilityStatus(sc.nextBoolean());
	        
	        // Set createdAt and updatedAt if needed (consider using LocalDateTime.now())
	        
	        session.save(menuItem);
	        transaction.commit();
	        session.close();
	    }

		@Override
		public void updateMenuItem(SessionFactory sf, Integer itemId) {

			    Session session = sf.openSession();
			    Transaction transaction = session.beginTransaction();
			    
			    MenuItems menuItem = session.get(MenuItems.class, itemId);
			    if (menuItem != null) {
			        Scanner sc = new Scanner(System.in);
			        
			        System.out.println("Current Name: " + menuItem.getName());
			        System.out.print("Enter new Name (leave blank to keep current): ");
			        String newName = sc.nextLine();
			        if (!newName.isEmpty()) {
			            menuItem.setName(newName);
			        }
			        
			        System.out.println("Current Description: " + menuItem.getDescription());
			        System.out.print("Enter new Description (leave blank to keep current): ");
			        String newDescription = sc.nextLine();
			        if (!newDescription.isEmpty()) {
			            menuItem.setDescription(newDescription);
			        }
			        
			        System.out.println("Current Price: " + menuItem.getPrice());
			        System.out.print("Enter new Price (enter -1 to keep current): ");
			        double newPrice = sc.nextDouble();
			        if (newPrice != -1) {
			            menuItem.setPrice(newPrice);
			        }
			        
			        System.out.println("Current Availability Status: " + menuItem.isAvailabilityStatus());
			        System.out.print("Enter new Availability Status (true/false, leave blank to keep current): ");
			        String availabilityInput = sc.next();
			        if (!availabilityInput.isEmpty()) {
			            menuItem.setAvailabilityStatus(Boolean.parseBoolean(availabilityInput));
			        }
			        
			        // Update the item in the database
			        session.update(menuItem);
			        transaction.commit();
			        System.out.println("Menu Item updated successfully.");
			    } else {
			        System.out.println("Menu Item not found with ID: " + itemId);
			    }
			    session.close();
			}

		@Override
		public void deleteMenuItem(SessionFactory sf, Integer itemId) {

			Session session = sf.openSession();
		    Transaction transaction = session.beginTransaction();
		    
		    MenuItems menuItem = session.get(MenuItems.class, itemId);
		    if (menuItem != null) {
		        session.delete(menuItem);
		        transaction.commit();
		        System.out.println("Menu Item with ID " + itemId + " has been deleted successfully.");
		    } else {
		        System.out.println("Menu Item not found with ID: " + itemId);
		    }
		    session.close();
			
		}

		@Override
		public void getAllMenuItems(SessionFactory sf) {

			
			Session session = sf.openSession();
	        Query<MenuItems> query = session.createQuery("from MenuItems", MenuItems.class);
	        List<MenuItems> menuItems = query.getResultList();
	        
	        for (MenuItems item : menuItems) {
	            System.out.println(item);
	        }
	        session.close();
	    }

		@Override
		public void getMenuItemById(SessionFactory sf, Integer itemId) {

			
			Session session = sf.openSession();
	        MenuItems menuItem = session.get(MenuItems.class, itemId);
	        
	        if (menuItem != null) {
	            System.out.println(menuItem);
	        } else {
	            System.out.println("Menu Item not found with ID: " + itemId);
	        }
	        session.close();
	    }

		}