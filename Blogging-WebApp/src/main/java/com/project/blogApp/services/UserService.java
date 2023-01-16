package com.project.blogApp.services;

import java.util.List;

import com.project.blogApp.payloads.UserDto;

public interface UserService {

		UserDto createUser(UserDto userDto);
		UserDto updateUser(UserDto userDto, Long userId);
		UserDto getUserById(Long userId);
		List<UserDto> getAllUsers();
		void deleteUser(Long userId);
}
