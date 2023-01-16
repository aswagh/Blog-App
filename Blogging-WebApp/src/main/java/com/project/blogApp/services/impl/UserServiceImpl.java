package com.project.blogApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.blogApp.entities.User;
import com.project.blogApp.exceptions.ResourceNotFoundException;
import com.project.blogApp.payloads.UserDto;
import com.project.blogApp.repositories.UserRepository;
import com.project.blogApp.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private User user;
	private UserDto userDto;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		userDto.setUser_password(this.passwordEncoder.encode(userDto.getUser_password()));
		user = this.dtoToUser(userDto);
		return this.userToDto(userRepository.save(user)) ;
	}
	
	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		System.out.println(userId);
		userDto.setUser_password(this.passwordEncoder.encode(userDto.getUser_password()));

		user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));
		System.out.println(user);
		user.setUser_name(userDto.getUser_name());
		user.setEmail(userDto.getEmail());
		user.setUser_password(userDto.getUser_password());
		user.setAbout(userDto.getAbout());
		System.out.println();
		return this.userToDto(userRepository.save(user));
	}
	
	@Override
	public UserDto getUserById(Long userId) {
		System.out.println("user ID : "+userId);
		user =  this.userRepository.findById(userId)
				.orElseThrow(( )-> new ResourceNotFoundException("User", "Id", userId));
		System.out.println("User Details : "+user);
		return this.userToDto(user);
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List <UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Long userId) {
		user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id",userId));
		System.out.println("Deleted user = "+user);
		this.userRepository.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
//		return new User(userDto.getUser_id(), userDto.getUser_name(),userDto.getUser_email(),userDto.getUser_password(),userDto.getAbout());
		user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	public UserDto userToDto(User user) {
//		return new UserDto(user.getUser_id(),user.getUser_name(),user.getUser_email(),user.getUser_password(),user.getAbout());
		this.userDto = this.modelMapper.map(user, UserDto.class);
		return this.userDto;
	}

}
