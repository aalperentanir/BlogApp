package com.alialperen.blogApp.service;

import java.util.UUID;

import com.alialperen.blogApp.dto.LikeDto;
import com.alialperen.blogApp.entity.Like;

public interface LikeService {

	Long countLikes(UUID postId);
	
	Boolean isLiked(UUID postId);
	
	LikeDto toggleLike(UUID postId);
	
	LikeDto mapToDTO(Like like, LikeDto likeDto);
	
	Like mapToEntity(LikeDto likeDto, Like like);
	
	
}
