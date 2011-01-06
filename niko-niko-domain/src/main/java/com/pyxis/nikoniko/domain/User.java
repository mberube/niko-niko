package com.pyxis.nikoniko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity @Table(name = "users")
public class User {
	@SuppressWarnings("unused")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(nullable = false, unique = true)
	private String username;
	
	@SuppressWarnings("unused")
	private User() {
		// for hibernate
	}
	
	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
