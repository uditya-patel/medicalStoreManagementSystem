package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTests {
	
	@MockBean
	CustomerRepository cusRepo;
	
	@Autowired
	CustomerServiceImpl cusService;
	
	@Autowired
	Customer customer;
	
	@Test
	public void customerMembership() {
		Customer customer=new Customer();
		customer.setCustomerName("viswapriya");
		customer.setCustomerPassword("priya1234");
		customer.setCustomerConfirmPassword("priya1234");
		customer.setCustomerGender("FEMALE");
		customer.setCustomerMobilenumber("1234567890");
		customer.setCustomerAge("23");
		customer.setCustomerEmail("viswa@gmail.com");
		String response="profile created successfully";
		when(cusRepo.save(customer)).thenReturn(customer);
		assertEquals(response,cusService.customerMembership(customer));
	}
	
	@Test
	public void customerView() {
		Long customerId=2L;
		when(cusRepo.findByCustomerId(customerId)).thenReturn(customer);
		assertEquals(Optional.of(customer),cusService.customerView(customerId));
	}
	
	@Test
	public void delete() {
		Long customerId=7L;
		when(cusRepo.findByCustomerId(customerId)).thenReturn(customer);
		String response="Customer is deleted with id " + customerId;
		assertEquals(response,cusService.customerDelete(customerId));
	}
	
	@Test
	public void login() {
		LoginRequest log=new LoginRequest();
		log.setCustomerName("viswapriya");
		log.setPassword("priya1234");
		String s="welcome to HOME page";
		Customer customer=new Customer();
		customer.setCustomerName("viswapriya");
		customer.setCustomerPassword("priya1234");
		customer.setCustomerConfirmPassword("priya1234");
		customer.setCustomerGender("FEMALE");
		customer.setCustomerMobilenumber("1234567890");
		customer.setCustomerAge("23");
		customer.setCustomerEmail("viswa@gmail.com");
		when(cusRepo.findByCustomerName(customer.getCustomerName())).thenReturn(customer);
		assertEquals(s,cusService.loginPage(log));
	}
	

}
