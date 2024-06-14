package com.alialperen.blogApp.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class LikeDto {
	
	private Long id;
	
	private LocalDateTime createdDate;
	
	private UUID userId;
	
	private UUID postId;

}
