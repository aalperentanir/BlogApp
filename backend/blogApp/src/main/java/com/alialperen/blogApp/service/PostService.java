package com.alialperen.blogApp.service;

import java.util.List;
import java.util.UUID;

import com.alialperen.blogApp.dto.PostDto;
import com.alialperen.blogApp.entity.Post;

public interface PostService {

	
	List<PostDto> findAll();
	
	PostDto findById(final UUID id);
	
	UUID create(final PostDto postDto);
	
	void update(final PostDto postDto);
	
	void deleteById(final UUID id);
	
	PostDto mapToDTO(Post post, PostDto postDto);
	
	Post mapToEntity(PostDto postDto, Post post);
}
