package com.PMS.PMSServer.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PMS.PMSServer.model.PMSParcel;
import com.PMS.PMSServer.service.ParcelService;

@RestController
@RequestMapping("/parcel")
@CrossOrigin("*")
public class ParcelController {
	
	@Autowired
	private ParcelService service;
	
	@GetMapping("/all")
	public ResponseEntity<?> selectAll() throws Exception{
		List<PMSParcel> parcel=service.getAllParcels();
		if(parcel != null) {
			return ResponseEntity.ok().body(parcel);
		}
		return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping
	public ResponseEntity<?> selectParcelByParcelId(@RequestParam int pid) throws Exception{
		
		PMSParcel parcel=service.getParcelByParcelID(pid);
		
		return ResponseEntity.ok().body(parcel);
	}
	@GetMapping("/user")
	public ResponseEntity<?> selectParcelByUserId(@RequestParam int uid) throws Exception{
		
		System.out.println("User id : " + uid);
		
		
		List<PMSParcel> parcel=service.getAllParcelByUserID(uid);
		
		return ResponseEntity.ok().body(parcel);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestParam int uid, @RequestBody PMSParcel p) throws Exception{
		
		PMSParcel parcel=service.addParcel(uid,p);
		
		if(parcel != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(parcel);
		}
		
		return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);	
	}
	
	
	@PutMapping
	public ResponseEntity<?> update(@RequestParam int pid,@RequestBody PMSParcel obj) throws Exception{
		PMSParcel parcel=service.updateParcel(pid,obj);
		if(parcel !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(parcel);
		}
			
		return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam int id) throws Exception{
		boolean isDeleted =service.deleteParcel(id);
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Parcel Deleted Sucessfully" );
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed To Delete Parcel" );
		}
		

	}
	
}
