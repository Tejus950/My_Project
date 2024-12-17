package com.hibernate.service;

import org.hibernate.SessionFactory;

public interface ReviewsService {
	
    void insertReview(SessionFactory sf);
	
	void updateReview(SessionFactory sf , Integer reviewId);
	
	void deleteReview(SessionFactory sf , Integer reviewId);
	
	void getAllReviews(SessionFactory sf);
	
    void getReviewById(SessionFactory sf, Integer reviewId);

}
