package com.alialperen.blogApp.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alialperen.blogApp.dto.RoleDto;
import com.alialperen.blogApp.entity.Role;
import com.alialperen.blogApp.repository.RoleRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
	
	private final RoleRepository roleRepository;

	@Override
	public List<RoleDto> findAll() {
		final List<Role> roles = roleRepository.findAll(Sort.by("id"));
		return roles.stream()
				.map(role -> mapToDto(role, new RoleDto()))
				.toList();
	}

	@Override
	public RoleDto findById(Long id) {
        return roleRepository.findById(id)
                .map(role -> mapToDto(role, new RoleDto()))
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
	}

	@Override
	public Long create(RoleDto roleDto) {
		Role role = new Role();
		mapToEntity(roleDto, role);
		return roleRepository.save(role).getId();
	}

	@Override
	public void update(RoleDto roleDto) {
        final Role role = roleRepository.findById(roleDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleDto.getId()));
        mapToEntity(roleDto, role);
        roleRepository.save(role);
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);
		
	}

	@Override
	public RoleDto mapToDto(Role role, RoleDto roleDto) {
		roleDto.setId(role.getId());
		roleDto.setName(role.getRoleName());
		roleDto.setDescription(role.getDescription());
        return roleDto;

	}

	@Override
	public Role mapToEntity(RoleDto roleDto, Role role) {
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        return role;
	}
	

}
