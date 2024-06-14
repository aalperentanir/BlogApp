package com.alialperen.blogApp.dto;

import com.alialperen.blogApp.enums.RoleEnum;

import lombok.Data;

@Data
public class RoleDto {
	
    private Long id;
    private RoleEnum name;
    private String description;

}
