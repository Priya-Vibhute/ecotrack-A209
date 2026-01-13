package com.learn.ecotrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecotrack.dtos.EnrollmentDto;
import com.learn.ecotrack.services.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@PostMapping("/enroll/{workshopId}")
	public ResponseEntity<EnrollmentDto> enroll(@PathVariable int workshopId,
			@RequestParam String email)
	{
		return ResponseEntity.ok(enrollmentService.enroll(email, workshopId));
	}

}
