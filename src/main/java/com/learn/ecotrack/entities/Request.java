package com.learn.ecotrack.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learn.ecotrack.enums.RequestStatus;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String itemType;
	private int quantity;// kg
	
	@Enumerated(EnumType.STRING)
	private RequestStatus status;
	
	private String reason;
	
	@ManyToOne
	@JsonManagedReference
	private User user;
	private String image;
	
	

}
