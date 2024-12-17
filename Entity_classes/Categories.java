package com.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Categories")
public class Categories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer CategoryID;
	private String Name;
	private String Description;
	
	
	@Override
	public String toString() {
		return "Categories [CategoryID=" + CategoryID + ", Name=" + Name + ", Description=" + Description + "]";
	}
}