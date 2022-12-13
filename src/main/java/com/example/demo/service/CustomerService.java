package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Customer;

public interface CustomerService {
	
	String addCustomerDetails(Customer customer);
	
	Optional<Customer> customerView(long customerId);
	
	String modify(Customer customer);
	
	String customerDelete(Long id);
	
	List<Customer> getAllCustomers();
	
	String loginPage(LoginRequest login);
	
	String resetPassword(String username,String password,String resetPassword);
	
	

}
