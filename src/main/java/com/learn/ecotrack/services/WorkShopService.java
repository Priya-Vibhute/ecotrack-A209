package com.learn.ecotrack.services;

import java.util.List;

import com.learn.ecotrack.dtos.WorkshopDto;

public interface WorkShopService {
	
	 WorkshopDto addWorkshop(WorkshopDto workshopDto);
	 
	 WorkshopDto updateWorkshop(int id,WorkshopDto workshopDto);
	 
	 void deleteWorkshop(int id);
	 
	 List<WorkshopDto> getWorkshops();
	 
	 WorkshopDto getWorkshopById(int id);

}
