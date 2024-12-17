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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer OrderID;
	private Long UserID;
	private Double TotalPrice;
	private String OrderStatus;
	
	@Column(name = "Timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime Timestamp;
	
	private String DeliveryAddress;
	
	@ManyToOne
    @JoinColumn(name = "UserID",referencedColumnName = "UserID", insertable = false, updatable = false)
    private Users user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItems> orderItems = new ArrayList<OrderItems>();
	
	@OneToOne(targetEntity = Payments.class, cascade = CascadeType.ALL)
    private Payments payment;
	
	@Override
	public String toString() {
		return "Orders [OrderID=" + OrderID + ", UserID=" + UserID + ", TotalPrice=" + TotalPrice + ", OrderStatus="
				+ OrderStatus + ", Timestamp=" + Timestamp + ", DeliveryAddress=" + DeliveryAddress + "]";
	}

	public void setRestaurant(Restaurants restaurant) {
		
	}
}