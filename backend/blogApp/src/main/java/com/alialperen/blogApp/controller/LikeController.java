package com.alialperen.blogApp.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alialperen.blogApp.dto.LikeDto;
import com.alialperen.blogApp.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {
	
	private final LikeService likeService;
	
	@PostMapping("/{id}")
	public ResponseEntity<LikeDto> toggleLike(@PathVariable UUID id){
		LikeDto like = likeService.toggleLike(id);
		 return new ResponseEntity<>(like,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Boolean> isLiked(@PathVariable UUID id){
		Boolean isLiked = likeService.isLiked(id);
		return ResponseEntity.ok(isLiked);
	}
	
	@GetMapping("/count/{id}")
	public ResponseEntity<Long> countLikes(@PathVariable UUID id){
		Long count = likeService.countLikes(id);
		
		return ResponseEntity.ok(count);
	}

}
