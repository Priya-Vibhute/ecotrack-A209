package com.learn.ecotrack.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.entities.Workshop;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {
	
    private Integer id;
	
	private Workshop workshop;
	
	private User user;
	
	private Integer amount;

}
