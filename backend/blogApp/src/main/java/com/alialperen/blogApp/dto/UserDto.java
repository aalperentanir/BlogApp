package com.alialperen.blogApp.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class UserDto {
	
    private UUID id;
    private String email;
    private String password;
    private String fullname;
    private boolean active = true;
    private LocalDateTime createdDate;
    private UUID createdBy;
    private LocalDateTime lastModifiedDate;
    private UUID lastModifiedBy;
    private Long roleId;

}
