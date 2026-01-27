package com.learn.ecotrack.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learn.ecotrack.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Workshop workshop;
	
	@ManyToOne
	@JsonManagedReference
	private User user;
	
	private Integer amount;
	
	private String razorpayOrderId;
	private String razorpayPaymentId;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	

}
