package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
	
	@Email
	@NotBlank(message="username should not be blank")
	private String customerName;
	
	@NotBlank(message="password should not be blank")
	private String password;

}
