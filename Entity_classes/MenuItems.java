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
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "MenuItems")
public class MenuItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ItemID;
	private String Name;
	private String Description;
	private Double Price;
	
	private Integer RestaurantID;
	
	private boolean AvailabilityStatus  = true; ;
	
	@ManyToOne
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID", insertable = false, updatable = false)
    private Categories category;

	private Integer CategoryID;
	
	@Column(name = "CreatedAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;
	
	@Column(name = "UpdatedAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updatedAt;
	
	@ManyToOne
    @JoinColumn(name = "RestaurantID" ,referencedColumnName = "RestaurantID", insertable = false, updatable = false)
    private Restaurants restaurant;
	
	@OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
    List<OrderItems> orderItems = new ArrayList<OrderItems>();

	@Override
	public String toString() {
		return "MenuItems [ItemID=" + ItemID + ", Name=" + Name + ", Description=" + Description + ", Price=" + Price
				+ ", RestaurantID=" + RestaurantID + ", AvailabilityStatus=" + AvailabilityStatus + ", CategoryID="
				+ CategoryID + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}