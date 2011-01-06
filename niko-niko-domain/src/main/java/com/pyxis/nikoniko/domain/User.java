package com.pyxis.nikoniko.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	@NotNull 
	private String username;
	
	@SuppressWarnings("unused")
	private User() {
		// for hibernate
	}
	
	public User(String _username) {
		username = _username;
	}

	public String getUsername() {
		return username;
	}
}
