package com.hibernate.entity;

import java.awt.MenuItem;
import java.util.List;

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

@Table(name = "OrderItems")
public class OrderItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer OrderItemID;
	private Integer OrderID;
	private Integer ItemID;
	private Integer Quantity;
	private Double Price;
	
	@ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID" , insertable = false, updatable = false)
    private Orders order;
	
	@ManyToOne
    @JoinColumn(name = "ItemID", referencedColumnName = "ItemID", insertable = false, updatable = false)
    private MenuItems menuItem;

	@Override
	public String toString() {
		return "OrderItems [OrderItemID=" + OrderItemID + ", OrderID=" + OrderID + ", ItemID=" + ItemID + ", Quantity="
				+ Quantity + ", Price=" + Price + ", order=" + order + ", menuItem=" + menuItem + "]";
	}

	public void setMenuItems(List<MenuItems> selectedItem) {
		// TODO Auto-generated method stub
		
	}

	public void add(OrderItems orderItem) {
		// TODO Auto-generated method stub
		
	}
	
	
}