package com.hibernate.serviceImpl;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.entity.Reviews;
import com.hibernate.service.ReviewsService;

public class ReviewsServiceImpl implements ReviewsService{
	
		Scanner sc = new Scanner(System.in);

		@Override
		public void insertReview(SessionFactory sf) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Reviews review = new Reviews();

	        System.out.println("Enter User ID:");
	        review.setUserID((long) sc.nextInt());

	        System.out.println("Enter Restaurant ID:");
	        review.setRestaurantID(sc.nextInt());

	        System.out.println("Enter Rating (1-5):");
	        review.setRating(sc.nextInt());

	        sc.nextLine(); // Consume newline
	        System.out.println("Enter Comment:");
	        review.setComment(sc.nextLine());

	        session.save(review);
	        transaction.commit();
	        session.close();
	    }

		@Override
		public void updateReview(SessionFactory sf, Integer reviewId) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Reviews review = session.get(Reviews.class, reviewId);
	        if (review != null) {
	            System.out.println("Current Rating: " + review.getRating());
	            System.out.print("Enter new Rating (1-5, leave blank to keep current): ");
	            int newRating = sc.nextInt();
	            if (newRating >= 1 && newRating <= 5) {
	                review.setRating(newRating);
	            }

	            sc.nextLine(); // Consume newline
	            System.out.println("Current Comment: " + review.getComment());
	            System.out.print("Enter new Comment (leave blank to keep current): ");
	            String newComment = sc.nextLine();
	            if (!newComment.isEmpty()) {
	                review.setComment(newComment);
	            }

	            session.update(review);
	            transaction.commit();
	            System.out.println("Review updated successfully.");
	        } else {
	            System.out.println("Review not found with ID: " + reviewId);
	        }
	        session.close();
	    }
			
		@Override
		public void deleteReview(SessionFactory sf, Integer reviewId) {

			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();

	        Reviews review = session.get(Reviews.class, reviewId);
	        if (review != null) {
	            session.delete(review);
	            transaction.commit();
	            System.out.println("Review with ID " + reviewId + " has been deleted successfully.");
	        } else {
	            System.out.println("Review not found with ID: " + reviewId);
	        }
	        session.close();
	    }

		@Override
		public void getAllReviews(SessionFactory sf) {

			Session session = sf.openSession();
	        Query<Reviews> query = session.createQuery("from Reviews", Reviews.class);
	        List<Reviews> reviews = query.getResultList();

	        for (Reviews review : reviews) {
	            System.out.println(review);
	        }
	        session.close();
		}

		@Override
		public void getReviewById(SessionFactory sf, Integer reviewId) {

			Session session = sf.openSession();
	        Reviews review = session.get(Reviews.class, reviewId);

	        if (review != null) {
	            System.out.println(review);
	        } else {
	            System.out.println("Review not found with ID: " + reviewId);
	        }
	        session.close();
	 }
			
	}