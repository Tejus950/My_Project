package com.hibernate.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.entity.Restaurants;
import com.hibernate.service.RestaurantsService;

public class RestaurantsServiceImpl implements RestaurantsService{

		Scanner sc = new Scanner(System.in);

		@Override
		public void insertRestaurant(SessionFactory sf) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        try {
	            Restaurants restaurant = new Restaurants();

	            System.out.println("Enter Restaurant Name:");
	            restaurant.setName(sc.nextLine());

	            System.out.println("Enter Location:");
	            restaurant.setLocation(sc.nextLine());

	            System.out.println("Enter Cuisine Type:");
	            restaurant.setCuisineType(sc.nextLine());

	            System.out.println("Enter Rating:");
	            restaurant.setRating(sc.nextDouble());
	            sc.nextLine();  // Consume newline

	            System.out.println("Enter Contact Number:");
	            restaurant.setContactNumber(sc.nextLine());

	            restaurant.setCreatedAt(LocalDateTime.now());
	            restaurant.setUpdatedAt(LocalDateTime.now());

	            session.persist(restaurant);
	            transaction.commit();
	            System.out.println("Restaurant added successfully.");
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            System.out.println("An error occurred while adding the restaurant: " + e.getMessage());
	        } finally {
	            session.close();
	        }
	    }

		@Override
		public void updateRestaurant(SessionFactory sf, Integer restaurantId) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        try {
	            Restaurants restaurant = session.get(Restaurants.class, restaurantId);
	            if (restaurant == null) {
	                System.out.println("Restaurant not found with ID: " + restaurantId);
	                return;
	            }

	            System.out.println("Updating Restaurant: " + restaurant);
	            System.out.println("Enter new Name (or press Enter to keep current):");
	            String name = sc.nextLine();
	            if (!name.isEmpty()) restaurant.setName(name);

	            System.out.println("Enter new Location (or press Enter to keep current):");
	            String location = sc.nextLine();
	            if (!location.isEmpty()) restaurant.setLocation(location);

	            System.out.println("Enter new Cuisine Type (or press Enter to keep current):");
	            String cuisineType = sc.nextLine();
	            if (!cuisineType.isEmpty()) restaurant.setCuisineType(cuisineType);

	            System.out.println("Enter new Rating (or press Enter to keep current):");
	            String ratingInput = sc.nextLine();
	            if (!ratingInput.isEmpty()) restaurant.setRating(Double.parseDouble(ratingInput));

	            System.out.println("Enter new Contact Number (or press Enter to keep current):");
	            String contactNumber = sc.nextLine();
	            if (!contactNumber.isEmpty()) restaurant.setContactNumber(contactNumber);

	            restaurant.setUpdatedAt(LocalDateTime.now());
	            transaction.commit();
	            System.out.println("Restaurant updated successfully.");
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            System.out.println("An error occurred while updating the restaurant: " + e.getMessage());
	        } finally {
	            session.close();
	        }
	    }

		@Override
		public void deleteRestaurant(SessionFactory sf, Integer restaurantId) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        try {
	            Restaurants restaurant = session.get(Restaurants.class, restaurantId);
	            if (restaurant != null) {
	                session.delete(restaurant);
	                transaction.commit();
	                System.out.println("Restaurant deleted successfully.");
	            } else {
	                System.out.println("Restaurant not found with ID: " + restaurantId);
	            }
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            System.out.println("An error occurred while deleting the restaurant: " + e.getMessage());
	        } finally {
	            session.close();
	        }
	    }

		@Override
		public void getAllRestaurants(SessionFactory sf) {

			Session session = sf.openSession();
	        List<Restaurants> restaurantsList = new ArrayList<>();

	        try {
	            Query<Restaurants> query = session.createQuery("FROM Restaurants", Restaurants.class);
	            restaurantsList = query.getResultList();

	            for (Restaurants restaurant : restaurantsList) {
	                System.out.println(restaurant);
	            }
	        } catch (Exception e) {
	            System.out.println("An error occurred while retrieving restaurants: " + e.getMessage());
	        } finally {
	            session.close();
	        }
		}

		@Override
		public Restaurants getRestaurantById(SessionFactory sf, Integer restaurantId) {
			
			Session session = sf.openSession();
	        Restaurants restaurant = null;

	        try {
	            restaurant = session.get(Restaurants.class, restaurantId);
	            if (restaurant != null) {
	                System.out.println("Restaurant found: " + restaurant);
	            } else {
	                System.out.println("Restaurant not found with ID: " + restaurantId);
	            }
	        } catch (Exception e) {
	            System.out.println("An error occurred while retrieving the restaurant: " + e.getMessage());
	        } finally {
	            session.close();
	        }

	        return restaurant;
	    }


	}