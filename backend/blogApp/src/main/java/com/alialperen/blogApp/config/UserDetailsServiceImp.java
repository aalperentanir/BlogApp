package com.alialperen.blogApp.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.alialperen.blogApp.dto.UserDto;
import com.alialperen.blogApp.service.RoleService;
import com.alialperen.blogApp.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService{
	
	private final UserService userService;
	
	private final RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = userService.findByEmail(username);
		return User.withUsername(username)
				   .password(userDto.getPassword())
				   .disabled(!userDto.isActive())
				   .roles(roleService.findById(userDto.getRoleId()).getName().name())
				   .build();
	}

}
