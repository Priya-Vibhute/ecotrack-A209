package com.learn.ecotrack.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learn.ecotrack.dtos.WorkshopDto;
import com.learn.ecotrack.services.FileService;
import com.learn.ecotrack.services.WorkShopService;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {
	
	@Autowired
	private FileService fileService;
	
	@Value("${workshop.images}")
	private String path;
	
	
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
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteWorkshop(@PathVariable int id)
    {
    	workShopService.deleteWorkshop(id);
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("message", "Workshop deleted");
    	return ResponseEntity.ok(map);
    }
    
    @PutMapping("/{id}/upload-image")
    public ResponseEntity<?> uploadImage(@PathVariable int id,
    		 @RequestParam("workshopImage") MultipartFile multipartFile)
    {
    	WorkshopDto workshop = workShopService.getWorkshopById(id);
    	
    	String fileName = fileService.uploadFile(multipartFile, path);
    	
    	workshop.setImage(fileName);
    	
    	workShopService.updateWorkshop(id, workshop);
    	
    	HashMap<String, String> hashMap = new HashMap<String, String>();
    	
    	hashMap.put("message",fileName+" uploaded successfully");
    	
    	
    	return ResponseEntity.ok(hashMap);
    }
   

}
