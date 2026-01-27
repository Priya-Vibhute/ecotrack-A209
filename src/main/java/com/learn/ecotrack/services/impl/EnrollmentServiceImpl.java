package com.learn.ecotrack.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.dtos.EnrollmentDto;
import com.learn.ecotrack.entities.Enrollment;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.entities.Workshop;
import com.learn.ecotrack.enums.PaymentStatus;
import com.learn.ecotrack.exceptions.NotFoundException;
import com.learn.ecotrack.repositories.EnrollmentRepository;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.repositories.WorkshopRepository;
import com.learn.ecotrack.services.EnrollmentService;
import com.learn.ecotrack.services.RazorpayService;
import com.razorpay.RazorpayException;

import com.razorpay.*;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkshopRepository workshopRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RazorpayService razorpayService;

	@Override
	public EnrollmentDto enroll(String email, int workshopId) {
		
		if(enrollmentRepository.existsByUserEmailAndWorkshopId(email, workshopId))
			throw new RuntimeException("user already enrolled");
		
		User user = userRepository.findByEmail(email)
		.orElseThrow(()->new NotFoundException("user not found"));
		
		Workshop workshop = workshopRepository.findById(workshopId)
		.orElseThrow(()->new NotFoundException("workshop not found"));
		
		Order order=null;
		
		try {
			order=razorpayService.createOrder((double)workshop.getPrice());
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Enrollment enrollment = new Enrollment();
		enrollment.setUser(user);
		enrollment.setWorkshop(workshop);
		enrollment.setAmount(workshop.getPrice());
		enrollment.setRazorpayOrderId(order.get("id"));
		enrollment.setPaymentStatus(PaymentStatus.CREATED);
		
		
		Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
			
		return modelMapper.map(savedEnrollment, EnrollmentDto.class);
	}

}
