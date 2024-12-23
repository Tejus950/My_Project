package com.hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Restaurants")
public class Restaurants {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer RestaurantID;
	private String Name;
	private String Location;
	private String CuisineType;
	private Double Rating;
	private String  ContactNumber;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
     List<MenuItems> menuItems = new ArrayList<MenuItems>();
	
	@Override
	public String toString() {
		return "Restaurants [RestaurantID=" + RestaurantID + ", Name=" + Name + ", Location=" + Location
				+ ", CuisineType=" + CuisineType + ", Rating=" + Rating + ", ContactNumber=" + ContactNumber + "]";
	}
}