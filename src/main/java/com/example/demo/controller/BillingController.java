package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptionhandler.BillNotFoundException;
import com.example.demo.model.Billing;
import com.example.demo.service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	@Autowired
	private BillingService billService;
	
	/*
	@PostMapping("/registration")
	public ResponseEntity<String> addBillDetails(@Valid @RequestBody Billing billing){
	    String response=billService.registration(billing);
		return new ResponseEntity<String>(response,HttpStatus.CREATED);
	}
	*/
	
	@DeleteMapping("/deleteBill")
	public ResponseEntity<String> deleteBill(@RequestParam("id") Long id) {
		String response = billService.billCancel(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
//	Uditya functionality
	
//	public ResponseEntity<Billing> createBill(@RequestParam("productId") Integer productId, @RequestParam("productQuantity") Integer productQuantity,@RequestParam("customerName") String customerName) {
		
//	}
	
	
	/*
	@PostMapping("/createBill")
	public ResponseEntity<Billing> createBill(@RequestBody Billing bill) {
		Billing _bill = (Billing) billService.createBill(bill);
		return new ResponseEntity<>(_bill, HttpStatus.CREATED);
		
	}
	*/
	
	@GetMapping("/getBillById/{id}")
	public ResponseEntity<Billing> getBillById(@PathVariable("id") Long billId) throws BillNotFoundException {
		Billing bill = billService.getBillById(billId);
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}
	
	@GetMapping("/getAllBills")
	public ResponseEntity<List<Billing>> getAllBills() {
		List<Billing> bills = billService.getAllBills();
		return new ResponseEntity<>(bills, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteBillById/{id}")
	public ResponseEntity<HttpStatus> deletBillById(@PathVariable("id") Long billId) throws BillNotFoundException {
		billService.deletBillById(billId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/updateBill/{id}")
	public ResponseEntity<Billing> updateBill(@PathVariable("id") Long billId, @RequestBody Billing bill) throws BillNotFoundException {
		Billing _bill = billService.updateBill(billId, bill);
		return new ResponseEntity<>(_bill, HttpStatus.OK);
	}

}
