package com.alialperen.blogApp.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class PostDto {
	
	private UUID id;
	
	private String title;
	
	private String content;
	
	private Set<String> tags = new HashSet<>();
	
	private LocalDateTime createdDate;
	
	private UUID createdBy;
	
	private LocalDateTime lastModifiedDate;
	
	private UUID lastModifiedBy;

}
