package com.alialperen.blogApp.entity;

import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false, unique = true, name = "email")
	private String email;

	@Column(nullable = false, name = "password")
	private String password;

	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "active")
	private boolean active = true;
	
	@CreatedDate
	@Column(name = "createdDate", updatable = false)
	private LocalDateTime createdDate;
	
	@CreatedBy
	@Column(name = "createdBy", updatable = false)
	private UUID createdBy;
	
	@LastModifiedDate
	@Column(name = "lastModifiedDate")
	private LocalDateTime lastModifiedDate;
	
	@LastModifiedBy
	@Column(name = "lastModifiedBy")
	private UUID lastModifiedBy;
	
	@ManyToOne
	@JoinColumn(name="role_id", nullable=false)
	private Role role;

	@OneToMany(mappedBy = "user")
	private Set<Like> likes = new HashSet<>();

}
