package com.alialperen.blogApp.service;

import java.util.List;

import com.alialperen.blogApp.dto.RoleDto;
import com.alialperen.blogApp.entity.Role;

public interface RoleService {

	
	List<RoleDto> findAll();
	
	RoleDto findById(final Long id);
	
	Long create(final RoleDto roleDto);
	
	void update(final RoleDto roleDto);
	
	void deleteById(final Long id);
	
	RoleDto mapToDto(Role role, RoleDto roleDto);
	
	Role mapToEntity(RoleDto roleDto, Role role);
	
	
	
}
