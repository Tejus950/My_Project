package com.hibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Reviews")
public class Reviews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ReviewID;
	private Long UserID;
	private Integer RestaurantID;
	private Integer Rating;
	@Column(length = 1000)
	private String Comment;
	
	@Column(name = "CreatedAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;
	
	@ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", insertable = false, updatable = false)
    private Users user; // Reference to the Users entity

	@ManyToOne
    @JoinColumn(name = "RestaurantID", referencedColumnName = "RestaurantID", insertable = false, updatable = false)
    private Restaurants restaurant; // Reference to the Restaurants entity

	@Override
	public String toString() {
		return "Reviews [ReviewID=" + ReviewID + ", UserID=" + UserID + ", RestaurantID=" + RestaurantID + ", Rating="
				+ Rating + ", Comment=" + Comment + ", createdAt=" + createdAt + ", user=" + user + ", restaurant="
				+ restaurant + "]";
	}	
}   