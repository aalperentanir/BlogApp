package com.alialperen.blogApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alialperen.blogApp.dto.PostDto;
import com.alialperen.blogApp.service.PostService;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
	
	private final PostService postService;
	
	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> findAll(){
		return ResponseEntity.ok(postService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> findById(@PathVariable final UUID id){
		return ResponseEntity.ok(postService.findById(id));
	}
	
	 @PostMapping("/create")
	public ResponseEntity<UUID> create(@RequestBody PostDto postDto){
		UUID id = postService.create(postDto);
		return new ResponseEntity<>(id,HttpStatus.CREATED);
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<UUID> update(@PathVariable final UUID id, @RequestBody PostDto postDto){
		postService.update(postDto);
		
		return ResponseEntity.ok(postDto.getId());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable final UUID id){
		postService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
