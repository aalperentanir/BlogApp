package com.alialperen.blogApp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alialperen.blogApp.dto.PostDto;
import com.alialperen.blogApp.entity.Post;
import com.alialperen.blogApp.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService {
	
	private final PostRepository postRepository;
	
	
	@Override
	public List<PostDto> findAll() {
		final List<Post> posts = postRepository.findAll(Sort.by("lastModifiedDate").descending());
		return posts.stream().map(post -> mapToDTO(post, new PostDto())).toList();
	}

	@Override
	public PostDto findById(UUID id) {
		// TODO Auto-generated method stub
		return postRepository.findById(id)
				.map(post -> mapToDTO(post, new PostDto()))
				.orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
	}

	@Override
	public UUID create(PostDto postDto) {
		Post post = new Post();
		mapToEntity(postDto, post);
	
		return postRepository.save(post).getId();
	}

	@Override
	public void update(PostDto postDto) {
		final Post post = postRepository.findById(postDto.getId())
				.orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postDto.getId()));
		mapToEntity(postDto, post);
		postRepository.save(post);
		
		
	}

	@Override
	public void deleteById(UUID id) {
		postRepository.deleteById(id);
		
	}

	@Override
	public PostDto mapToDTO(Post post, PostDto postDto) {
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setTags(post.getTags());
		postDto.setCreatedDate(post.getCreatedDate());
		postDto.setCreatedBy(post.getCreatedBy());
		postDto.setLastModifiedDate(post.getLastModifiedDate());
		postDto.setLastModifiedBy(post.getLastModifiedBy());
        return postDto;
	}

	@Override
	public Post mapToEntity(PostDto postDto, Post post) {
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setTags(postDto.getTags());
        post.setCreatedDate(postDto.getCreatedDate());
        post.setCreatedBy(postDto.getCreatedBy());
        post.setLastModifiedDate(postDto.getLastModifiedDate());
        post.setLastModifiedBy(postDto.getLastModifiedBy());
        return post;
	}

}
