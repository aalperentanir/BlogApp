package com.alialperen.blogApp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alialperen.blogApp.dto.UserDto;
import com.alialperen.blogApp.entity.Role;
import com.alialperen.blogApp.entity.User;
import com.alialperen.blogApp.repository.RoleRepository;
import com.alialperen.blogApp.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
	
	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;

	@Override
	public List<UserDto> findAll() {
		final List<User> users = userRepository.findAll(Sort.by("lastModifiedDate").descending());
		return users.stream().map(user -> mapToDto(user, new UserDto())).toList();
	}

	@Override
	public UserDto findById(UUID id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).map(user -> mapToDto(user, new UserDto()))
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}

	@Override
	public UUID create(UserDto userDto) {
		User user = new User();
		mapToEntity(userDto, user);
		return userRepository.save(user).getId();
	}

	@Override
	public void update(UserDto userDto) {
		final User user = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getId()));
		mapToEntity(userDto, user);
		userRepository.save(user);
		
	}

	@Override
	public void deleteById(UUID id) {
		userRepository.deleteById(id);
		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public UserDto findByEmail(String username) {
		
		return userRepository.findByEmail(username)
				.map(user -> mapToDto(user, new UserDto()))
				.orElseThrow(() -> new EntityNotFoundException("User not found with email :" + username));
	}

	@Override
	public UserDto mapToDto(User user, UserDto dto) {
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setFullname(user.getFullName());
		dto.setActive(user.isActive());
		dto.setCreatedDate(user.getCreatedDate());
		dto.setCreatedBy(user.getCreatedBy());
		dto.setLastModifiedDate(user.getLastModifiedDate());
		dto.setLastModifiedBy(user.getLastModifiedBy());
		dto.setRoleId(user.getRole().getId());
        return dto;
	}

	@Override
	public User mapToEntity(UserDto userDto, User user) {
		user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullname());
        user.setActive(userDto.isActive());
        user.setCreatedDate(userDto.getCreatedDate());
        user.setCreatedBy(userDto.getCreatedBy());
        user.setLastModifiedDate(userDto.getLastModifiedDate());
        user.setLastModifiedBy(userDto.getLastModifiedBy());
        
        Long roleId = userDto.getRoleId();
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleId));
        
        // Loglama ekleyerek roleId ve role bilgilerini doğrulama
        System.out.println("Role ID: " + roleId + ", Role: " + role);

        user.setRole(role);
        return user;
//        user.setRole(roleRepository.findById(userDto.getRoleId())
//                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + userDto.getId())));
//        
//        System.out.println("Role Name: " + user.getRole());
//        return user;
	}
	
	

}
