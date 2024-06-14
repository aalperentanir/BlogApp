package com.alialperen.blogApp.service;

import java.util.List;
import java.util.UUID;

import com.alialperen.blogApp.dto.UserDto;
import com.alialperen.blogApp.entity.User;

public interface UserService {
	
	List<UserDto> findAll();
	
	UserDto findById(final UUID id);
	
	UUID create(final UserDto userDto);
	
	void update(final UserDto userDto);
	
	void deleteById(final UUID id);
	
	UserDto findByEmail(String username);
	
	UserDto mapToDto(User user, UserDto dto);
	
	User mapToEntity(UserDto userDto, User user);
	
	
	

}
