package com.hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor

@Table(name = "Users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UserID;  //responsible for generating  primary key  with an auto-increment
	
	@Column(name = "Name", nullable = false)
	private String Name;
	
	@NaturalId
	@Column(name = "Email", unique = true, nullable = false)
	private String Email;
	
	@Column(name = "PhoneNumber", nullable = false)
	private String  PhoneNumber;
	
	@Column(name = "Password", nullable = false)
	private String Password;
	
	@Column(name = "Address", nullable = false)
	private String Address;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
     List<Orders> orders = new ArrayList<Orders>();
	
	@Override
	public String toString() {
		return "Users [UserID=" + UserID + ", Name=" + Name + ", Email=" + Email + ", PhoneNumber=" + PhoneNumber
				+ ", Password=" + Password + ", Address=" + Address + "]";
	}
}