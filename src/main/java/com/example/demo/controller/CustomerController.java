package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

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

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerServiceImpl cusService;

	@PostMapping("/signup")
	public ResponseEntity<String> addCustomerDetails(@Valid @RequestBody Customer customer){
	    String response=cusService.customerMembership(customer);
		return new ResponseEntity<String>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/View Profile")
	public ResponseEntity<Customer> fetchDetails(@RequestParam("id")long id){
		Optional<Customer> customer=cusService.customerView(id);
		return new ResponseEntity<Customer>(customer.get(),HttpStatus.FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Customer customer){
		String response=cusService.modify(customer);
		return new ResponseEntity<String>(response,HttpStatus.RESET_CONTENT);
	}
	
	@DeleteMapping("/Delete Profile")
	public ResponseEntity<String> deleteProfile(@RequestParam("id") Long id) {
		String response = cusService.customerDelete(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/GetAllDetails")
	public ResponseEntity<List<Customer>> getCustomers(){
		List<Customer> lis=cusService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(lis,HttpStatus.OK);
	}
	
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("username")String userName,@RequestParam("password")String password){
		LoginRequest loginRequest=new LoginRequest();
		loginRequest.setCustomerName(userName);
		loginRequest.setPassword(password);
		String response=cusService.loginPage(loginRequest);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@GetMapping("/forgot password")
	public ResponseEntity<String> passwordReset(@RequestParam("username")String username,
			@RequestParam("password")String password,
			@RequestParam("resetPassword")String confirmPassword) {
		String response = cusService.resetPassword(username,password,confirmPassword);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
}
