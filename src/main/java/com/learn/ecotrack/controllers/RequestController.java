package com.learn.ecotrack.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learn.ecotrack.dtos.RequestDto;
import com.learn.ecotrack.dtos.WorkshopDto;
import com.learn.ecotrack.services.FileService;
import com.learn.ecotrack.services.RequestService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/requests")
public class RequestController {
	
	@Autowired
	private FileService fileService;
	
	@Value("${request.images}")
	private String path;
	
	
	@Autowired
	private RequestService requestService;
    
	
	@PostMapping("/{email}/add")
	public ResponseEntity<RequestDto> 
	addRequest(@PathVariable String email ,@RequestBody RequestDto requestDto)
	{
		return new ResponseEntity<RequestDto>
		(requestService.addRequest(email, requestDto),HttpStatus.CREATED);
	}
	

	
	@PutMapping("/{id}/approve")
	public ResponseEntity<RequestDto> approveRequest(@PathVariable int id)
	{
		return ResponseEntity.ok(requestService.approveRequest(id));
	}
	

	
	@PutMapping("/{id}/reject")
	public ResponseEntity<RequestDto> rejectRequest
	(@PathVariable int id,@RequestParam String reason)
	{
		
		return ResponseEntity.ok(requestService.rejectRequest(id, reason));
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<RequestDto>> requests
	(@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "30") int pageSize)
	{
		return ResponseEntity.ok(requestService.getAllRequests(pageNumber,pageSize));
	}
	
	
	@GetMapping("/{email}")
	public ResponseEntity<List<RequestDto>> 
	fetchRequestsByEmail(@PathVariable String email)
	{
		return ResponseEntity.ok(requestService.getRequestsByEmail(email));
	}
	
	
	@PutMapping("/{id}/upload-image")
    public ResponseEntity<?> uploadImage(@PathVariable int id,
    		 @RequestParam("requestImage") MultipartFile multipartFile)
    {
    	
		RequestDto request = requestService.getRequestById(id);
    	
    	String fileName = fileService.uploadFile(multipartFile, path);
    	
    	request.setImage(fileName);
    	
    	requestService.updateRequest(id, request);
    	
    	HashMap<String, String> hashMap = new HashMap<String, String>();
    	
    	
    	hashMap.put("message",fileName+" uploaded successfully");
    	
    	return ResponseEntity.ok(hashMap);
    }
	
	@GetMapping("/{id}/get-image")
	public void getImage(@PathVariable int id,HttpServletResponse response)
	{
		
		RequestDto requestDto = requestService.getRequestById(id);
		InputStream resource = fileService.getResource(path, requestDto.getImage());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		try {
			StreamUtils.copy(resource, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
