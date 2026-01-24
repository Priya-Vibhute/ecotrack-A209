package com.learn.ecotrack.dtos;

import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.enums.RequestStatus;

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
public class RequestDto {
	
	
	private Integer id;
	private String itemType;
	private int quantity;// kg
	private RequestStatus status;
	private String reason;
	private User user;
	private String image;
	private String pickupAddress;

}
