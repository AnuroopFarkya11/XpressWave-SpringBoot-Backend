package com.PMS.PMSServer.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PMS.PMSServer.model.ParcelInvoice;
import com.PMS.PMSServer.service.InvoiceService;


@RestController

@RequestMapping("/invoice")
@CrossOrigin("*")
public class InvoiceController {
	@Autowired
	private InvoiceService service;
	
	
	
	
	
	@GetMapping("/all")
	public ResponseEntity<?> selectAllTransactions(){
		List<ParcelInvoice> transaction=service.readAllTransactions();
		if(transaction != null) {
			return ResponseEntity.ok().body(transaction);
		}
		return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping
	public ResponseEntity<?> selectTransactionByInvoiceNumber(@RequestParam int invoiceNumber) throws Exception {

		ParcelInvoice transaction = service.getTransactionByInoviceNumber(invoiceNumber);

		return ResponseEntity.ok().body(transaction);
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> createTransaction(@RequestBody ParcelInvoice transaction,@RequestParam int userId,@RequestParam int parcelId) throws Exception{
		ParcelInvoice trans;
		try {
			trans = service.createTransaction(userId,parcelId,transaction);
			if (trans != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(trans);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
	}

	
}
