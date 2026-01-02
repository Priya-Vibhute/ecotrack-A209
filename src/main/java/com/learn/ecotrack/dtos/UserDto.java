package com.learn.ecotrack.dtos;

import com.learn.ecotrack.entities.Role;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String name;
	private String email;
	private String password;
	private String phoneNo;
	private Role role;
	

}
