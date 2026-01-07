package com.learn.ecotrack.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.learn.ecotrack.dtos.WorkshopDto;
import com.learn.ecotrack.entities.Workshop;
import com.learn.ecotrack.exceptions.NotFoundException;
import com.learn.ecotrack.repositories.WorkshopRepository;
import com.learn.ecotrack.services.WorkShopService;

public class WorkshopServiceImpl implements WorkShopService{
	
	@Autowired
	private WorkshopRepository workshopRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public WorkshopDto addWorkshop(WorkshopDto workshopDto) {
		
		Workshop workshop = modelMapper.map(workshopDto, Workshop.class);
		Workshop savedWorkshop = workshopRepository.save(workshop);	
		return modelMapper.map(savedWorkshop, WorkshopDto.class);
	}

	@Override
	public WorkshopDto updateWorkshop(int id, WorkshopDto workshopDto) {
		
		Workshop workshop = workshopRepository.findById(id)
		.orElseThrow(()->new NotFoundException("Workshop not found"));
		
		return null;
	}

	@Override
	public void deleteWorkshop(int id) {
		Workshop workshop = workshopRepository.findById(id)
				.orElseThrow(()->new NotFoundException("Workshop not found"));
		
	}

	@Override
	public List<WorkshopDto> getWorkshops() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkshopDto getWorkshopById(int id) {
		Workshop workshop = workshopRepository.findById(id)
				.orElseThrow(()->new NotFoundException("Workshop not found"));
		return null;
	}

}
