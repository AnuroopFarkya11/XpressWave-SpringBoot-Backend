package com.PMS.PMSServer.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PMS.PMSServer.model.Address;
import com.PMS.PMSServer.service.AddressService;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {

	@Autowired
	private AddressService service;
	
	@GetMapping("/all")
	public ResponseEntity<?> selectAll(){
		List<Address> addresses=service.getAllAddresses();
		if(addresses != null) {
			return ResponseEntity.ok().body(addresses);
		}
		return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
	}
//	@GetMapping
//	public ResponseEntity<?> selectAddressByAddressID(@RequestParam int aid) throws Exception {
//
//		Address address = service.getAddressByAid(aid);
//
//		return ResponseEntity.ok().body(address);
//
//	}
	@GetMapping
    public ResponseEntity<?> getAllAddressesByUserID(@RequestParam int uid) throws Exception {

        List<Address> addresses = service.getAllAddressesByUserId(uid);

        return ResponseEntity.ok().body(addresses);

    }
	
	@PostMapping
	public ResponseEntity<?> create(@RequestParam int uid,@RequestBody Address a) throws Exception{

		Address address = service.addAddress(uid,a);

			return ResponseEntity.status(HttpStatus.CREATED).body(address);
		
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestParam int aid, @RequestBody Address add) {
		Address address = service.updateAddress(aid, add);
		if (address != null) {
			return ResponseEntity.status(HttpStatus.OK).body(address);
		}
		return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);

	}
}
