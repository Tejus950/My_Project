package com.hibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor

@Table(name = "Payments")
public class Payments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer PaymentID;
	private Integer OrderID;
	private Double Amount;
	
	private String paymentMethod;
    private String paymentStatus;
    @Column(name = "Timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;
    
    @OneToOne(targetEntity = Orders.class)
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID", insertable = false, updatable = false)
    private Orders order;
    
  //  @JoinColumn(name = "OrderID")
  //  private Orders order;
    
	@Override
	public String toString() {
		return "Payments [PaymentID=" + PaymentID + ", OrderID=" + OrderID + ", Amount=" + Amount + ", paymentMethod="
				+ paymentMethod + ", paymentStatus=" + paymentStatus + ", timestamp=" + timestamp + "]";
	}
}