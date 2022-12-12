package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LoginRequest;
import com.example.demo.exceptionhandler.CustomerIdInvalidException;
import com.example.demo.exceptionhandler.KeyViolationException;
import com.example.demo.exceptionhandler.PasswordNotMatchException;
import com.example.demo.exceptionhandler.ProfileNotFoundException;
import com.example.demo.exceptionhandler.WrongPasswordException;
import com.example.demo.exceptionhandler.WrongUsernameAndPassword;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository cusRepo;

	public String customerMembership(Customer customer) {

		Customer customer1 = cusRepo.findByCustomerName(customer.getCustomerName());

		if (customer1 != null) {
			throw new KeyViolationException("Profile is already existed");
		} else {
			cusRepo.save(customer);
			return ("profile created successfully");
		}

	}

	public Optional<Customer> customerView(long customerId) {
		try {
			return Optional.of(cusRepo.findByCustomerId(customerId));
		} catch (Exception e) {
			throw new ProfileNotFoundException("NO SUCH CUSTOMER ID");
		}

	}

	public String modify(Customer customer) {

		Customer customer1 = cusRepo.findByCustomerId(customer.getCustomerId());

		if (customer1 != null) {
			cusRepo.save(customer);
			return "updated successfully";
		} else {
			throw new ProfileNotFoundException("NO SUCH CUSTOMERID WAS REGISTERED");
		}
	}
	
	
	public String customerDelete(Long id) {

		Customer customer = (cusRepo.findByCustomerId(id));

		if (customer != null) {
			cusRepo.deleteById(id);
			return "Customer is deleted with id " + id;
		} else {
			throw new CustomerIdInvalidException("Customer is not found for this id " + id);
		}

	}
	
	public List<Customer> getAllCustomers(){
		return cusRepo.findAll();
	}
	
	public String loginPage(LoginRequest login) {

		Customer customer = cusRepo.findByCustomerName(login.getCustomerName());

		if (customer != null) {
			if (customer.getCustomerPassword().equals(login.getPassword())) {
				return "welcome to HOME page";
			} else {
				throw new WrongPasswordException("Enter correct password");
			}
		} else {
			throw new WrongUsernameAndPassword("Enter correct username and password");

		}

	}
	
	
	public String resetPassword(String username,String password,String resetPassword) {
		Customer customer=cusRepo.findByCustomerName(username);
		if(customer!=null) {
			if(password.equals(resetPassword)) {
				customer.setCustomerPassword(password);
				customer.setCustomerConfirmPassword(password);
				cusRepo.save(customer);
				return "Successfully reset the password";
			}
			else {
				throw new PasswordNotMatchException("reset password doesnot matches with password");
			}
		}
		else {
			throw new ProfileNotFoundException("NO SUCH CUSTOMERNAME WAS REGISTERED");
		}
	}
	

}
