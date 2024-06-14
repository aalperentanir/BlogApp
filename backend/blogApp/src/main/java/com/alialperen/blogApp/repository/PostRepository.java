package com.alialperen.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alialperen.blogApp.entity.Post;

import java.util.UUID;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

}
