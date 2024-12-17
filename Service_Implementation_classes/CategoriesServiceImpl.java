package com.hibernate.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.entity.Categories;
import com.hibernate.service.CategoriesService;

public class CategoriesServiceImpl  implements CategoriesService{
	
		Scanner sc = new Scanner(System.in);

		@Override
		public void insertCategory(SessionFactory sf) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Categories category = new Categories();
	        
	        System.out.println("Enter Category Name:");
	        category.setName(sc.nextLine());
	        
	        System.out.println("Enter Category Description:");
	        category.setDescription(sc.nextLine());

	        session.save(category);
	        transaction.commit();
	        session.close();
	        System.out.println("Category added successfully.");
		}

		@Override
		public void updateCategory(SessionFactory sf, Integer categoryId) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Categories category = session.get(Categories.class, categoryId);
	        if (category != null) {
	            System.out.println("Current Name: " + category.getName());
	            System.out.print("Enter new Name (leave blank to keep current): ");
	            String newName = sc.nextLine();
	            if (!newName.isEmpty()) {
	                category.setName(newName);
	            }
	            
	            System.out.println("Current Description: " + category.getDescription());
	            System.out.print("Enter new Description (leave blank to keep current): ");
	            String newDescription = sc.nextLine();
	            if (!newDescription.isEmpty()) {
	                category.setDescription(newDescription);
	            }

	            session.update(category);
	            transaction.commit();
	            System.out.println("Category updated successfully.");
	        } else {
	            System.out.println("Category not found with ID: " + categoryId);
	        }
	        session.close();
	    }

		@Override
		public void deleteCategory(SessionFactory sf, Integer categoryId) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Categories category = session.get(Categories.class, categoryId);
	        if (category != null) {
	            session.delete(category);
	            transaction.commit();
	            System.out.println("Category with ID " + categoryId + " has been deleted successfully.");
	        } else {
	            System.out.println("Category not found with ID: " + categoryId);
	        }
	        session.close();
		}

		@Override
		public void getAllCategories(SessionFactory sf) {

			Session session = sf.openSession();
	        Query<Categories> query = session.createQuery("from Categories", Categories.class);
	        List<Categories> categories = query.getResultList();

	        for (Categories category : categories) {
	            System.out.println(category);
	        }
	        session.close();
			
		}

		@Override
		public void getCategoryById(SessionFactory sf, Integer categoryId) {

			Session session = sf.openSession();
	        Categories category = session.get(Categories.class, categoryId);

	        if (category != null) {
	            System.out.println(category);
	        } else {
	            System.out.println("Category not found with ID: " + categoryId);
	        }
	        session.close();
	    }
	}