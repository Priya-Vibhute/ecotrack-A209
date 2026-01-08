package com.learn.ecotrack.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.dtos.WorkshopDto;
import com.learn.ecotrack.entities.Workshop;
import com.learn.ecotrack.exceptions.NotFoundException;
import com.learn.ecotrack.repositories.WorkshopRepository;
import com.learn.ecotrack.services.WorkShopService;

@Service
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
		
		Workshop newWorkshop = modelMapper.map(workshopDto, Workshop.class);
		
		newWorkshop.setId(workshop.getId());
		Workshop savedWorkshop = workshopRepository.save(newWorkshop);
		
		return modelMapper.map(savedWorkshop, WorkshopDto.class);
	}

	@Override
	public void deleteWorkshop(int id) {
		Workshop workshop = workshopRepository.findById(id)
				.orElseThrow(()->new NotFoundException("Workshop not found"));
		
		workshopRepository.delete(workshop);
		
	}

	@Override
	public List<WorkshopDto> getWorkshops() {
		return workshopRepository.findAll()
				.stream().map(w->modelMapper.map(w, WorkshopDto.class))
				.toList();
	}

	@Override
	public WorkshopDto getWorkshopById(int id) {
		Workshop workshop = workshopRepository.findById(id)
				.orElseThrow(()->new NotFoundException("Workshop not found"));
		return modelMapper.map(workshop,WorkshopDto.class);
	}

}
