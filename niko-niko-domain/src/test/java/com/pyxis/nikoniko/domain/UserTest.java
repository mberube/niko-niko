package com.pyxis.nikoniko.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {
	@Test
	public void nameCanBeRetrieved() {
		User user = new User("bob");
		assertEquals("bob", user.getUsername());
	}
}
