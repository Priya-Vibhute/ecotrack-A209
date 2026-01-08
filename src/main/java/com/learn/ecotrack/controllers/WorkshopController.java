package com.learn.ecotrack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecotrack.dtos.WorkshopDto;
import com.learn.ecotrack.services.WorkShopService;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {
	
	@Autowired
	private WorkShopService workShopService;
	
	@GetMapping
	public ResponseEntity<List<WorkshopDto>> fetchWorkshops()
	{
		return ResponseEntity.ok(workShopService.getWorkshops());
	}
	
    @GetMapping("/{id}")
	public ResponseEntity<WorkshopDto> getWorkshopById(@PathVariable int id)
	{
		return ResponseEntity.ok(workShopService.getWorkshopById(id));
	}
	
    @PostMapping
    public ResponseEntity<WorkshopDto> addWorkshop(@RequestBody WorkshopDto workshopDto)
    {
    	return new ResponseEntity<WorkshopDto>
    	(workShopService.addWorkshop(workshopDto),HttpStatus.CREATED); 	
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WorkshopDto> updateWorkshop
    (@PathVariable int id,@RequestBody WorkshopDto workshopDto)
    {
    	return ResponseEntity.ok(workShopService.updateWorkshop(id, workshopDto));
    }
	

}
