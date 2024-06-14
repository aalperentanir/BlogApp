package com.alialperen.blogApp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alialperen.blogApp.dto.RoleDto;
import com.alialperen.blogApp.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RoleController {
	
	private final RoleService roleService;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<RoleDto>> findAll(){
		return ResponseEntity.ok(roleService.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<RoleDto> findById(@PathVariable final Long id){
		return ResponseEntity.ok(roleService.findById(id));
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody RoleDto roleDto){
		Long id = roleService.create(roleDto);
		
		return new ResponseEntity<>(id,HttpStatus.CREATED);
		
	}

}
