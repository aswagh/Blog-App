package com.project.blogApp.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

	private long user_id;
	
	@NotEmpty
	@Size(min = 2, message = "Username must be equal or greater than 2 characters")
	private String user_name;
	
//	@Email(message = "Email does not meet expectationss")
	@Email
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email does not meet expectationss")
	private String email;
	
	@NotEmpty
//	@Size(min = 7, message = "The password you have entered doesn't meet expectations")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "The password you have entered doesn't meet expectations")
	private String user_password;
	
	private String about;

	
}
