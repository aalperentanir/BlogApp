package com.alialperen.blogApp.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alialperen.blogApp.dto.UserDto;
import com.alialperen.blogApp.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	private final PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> findAll(){
		return ResponseEntity.ok(userService.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable final UUID id){
		return ResponseEntity.ok(userService.findById(id));
	
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<UUID> create(@RequestBody UserDto userDto){
		if(!userDto.getPassword().isBlank()) {
			userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}
		
		UUID id = userService.create(userDto);
		
		return new ResponseEntity<>(id,HttpStatus.CREATED);
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<UUID> findById(@PathVariable UUID id, @RequestBody UserDto userDto){
		userService.update(userDto);
		return ResponseEntity.ok(userDto.getId());
	}
	
	

}
