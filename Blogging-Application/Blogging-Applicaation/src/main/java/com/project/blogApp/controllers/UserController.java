package com.project.blogApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.blogApp.payloads.ApiResponse;
import com.project.blogApp.payloads.UserDto;
import com.project.blogApp.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody  UserDto userDto) {
		return new ResponseEntity<UserDto>(userServiceImpl.createUser(userDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") long userId){
		return new ResponseEntity<UserDto> (userServiceImpl.getUserById(userId),HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") long userId){
		return new ResponseEntity<UserDto>(userServiceImpl.updateUser(userDto, userId),HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return new ResponseEntity<List<UserDto>> (userServiceImpl.getAllUsers(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") long userId) {
		userServiceImpl.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true),HttpStatus.OK);
	}
	
}
