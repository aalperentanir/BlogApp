package com.alialperen.blogApp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;
import com.alialperen.blogApp.dto.CredentilsDto;
import com.alialperen.blogApp.dto.UserDto;
import com.alialperen.blogApp.exception.AppException;
import com.alialperen.blogApp.service.JWTService;
import com.alialperen.blogApp.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody CredentilsDto credentilsDto){
    	try {
    		Authentication auth = authenticationManager.authenticate(
    				new UsernamePasswordAuthenticationToken(credentilsDto.email(), credentilsDto.password()));
    		
    		User user = (User) auth.getPrincipal();
    		
    		String role = user.getAuthorities().stream()
    				                           .map(GrantedAuthority::getAuthority)
    				                           .findFirst()
    				                           .orElse("ROLE_USER");
    		
            String jwtToken = jwtService.generateToken(credentilsDto.email(), role);
            Map<String, String> map = new HashMap<>();
            map.put("token", jwtToken);
            return ResponseEntity.ok(map);
    	} catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            e.printStackTrace();
            throw new AppException(HttpStatus.FORBIDDEN, "Incorrect email or password", e);
        } catch (DisabledException e) {
            e.printStackTrace();
            throw new AppException(HttpStatus.FORBIDDEN, "User is disable", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @PostMapping("/signup")
    public ResponseEntity<UUID> create(@RequestBody UserDto userDto) {
        if(!userDto.getPassword().isBlank()) {
        	userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userDto.setRoleId((long) 1); // 1 is USER
        try {
            UUID id = userService.create(userDto);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            String constraintName = ((ConstraintViolationException) e.getCause()).getConstraintName();
            System.out.println(constraintName);
            if(constraintName.equals("user.UK_ob8kqyqqgmefl0aco34akdtpe")) {
                throw new AppException(HttpStatus.BAD_REQUEST, "Email already exist", e);
            }
            throw new RuntimeException(e);
        }
    }

}
