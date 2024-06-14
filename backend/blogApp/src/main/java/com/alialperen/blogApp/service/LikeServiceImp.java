package com.alialperen.blogApp.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alialperen.blogApp.dto.LikeDto;
import com.alialperen.blogApp.entity.Like;
import com.alialperen.blogApp.entity.User;
import com.alialperen.blogApp.repository.*;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImp implements LikeService{

	
	private final LikeRepository likeRepository;
	
	private final UserRepository userRepository;
	
	private final PostRepository postRepository;
	
	
	@Override
	public Long countLikes(UUID postId) {
		// TODO Auto-generated method stub
		return likeRepository.countByPostId(postId);
	}

	@Override
	public Boolean isLiked(UUID postId) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth == null  || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
			return null;
		}
		
		String email = auth.getName();
		final User user = userRepository.findByEmail(email).get();
		if(user == null) return null;
		
		Boolean isLiked = likeRepository.existsByUserIdAndPostId(user.getId(), postId);
		return isLiked;
	}

	@Override
	public LikeDto toggleLike(UUID postId) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
        
            return null;
        }

        String email = authentication.getName();
        final User user = userRepository.findByEmail(email).get();
  
        if(user == null) return null;
        
        Optional<Like> like = likeRepository.findByUserIdAndPostId(user.getId(), postId);
        LikeDto dto = new LikeDto();
        
        if(like.isPresent()) {
        	likeRepository.delete(like.get());
        }else {
        	Like newLike = new Like();
        	newLike.setUser(user);
        	newLike.setPost(postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found with id: "+postId)));
        	likeRepository.save(newLike);
        	mapToDTO(newLike, dto);
        }
		return dto;
	}

	@Override
	public LikeDto mapToDTO(Like like, LikeDto likeDto) {
		likeDto.setId(like.getId());
		likeDto.setUserId(like.getUser().getId());
		likeDto.setPostId(like.getPost().getId());
		likeDto.setCreatedDate(like.getCreatedDate());
        return likeDto;
	}

	@Override
	public Like mapToEntity(LikeDto likeDto, Like like) {
		 like.setId(likeDto.getId());
	        like.setUser(userRepository.findById(likeDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + likeDto.getUserId())));
	        like.setPost(postRepository.findById(likeDto.getPostId()).orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + likeDto.getPostId())));
	        like.setCreatedDate(likeDto.getCreatedDate());
	        return like;
	}

}
