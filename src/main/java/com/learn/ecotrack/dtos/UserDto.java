package com.learn.ecotrack.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learn.ecotrack.entities.Enrollment;
import com.learn.ecotrack.entities.Request;
import com.learn.ecotrack.entities.Role;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private List<Request> requests;
	private List<Enrollment> enrollments;
	

}
