package com.alialperen.blogApp.entity;


import java.util.HashSet;
import java.util.Set;

import com.alialperen.blogApp.enums.RoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="role")
@Data
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, unique=true)
	private RoleEnum roleName;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy = "role")
	private Set<User> users = new HashSet<>();
	

}
