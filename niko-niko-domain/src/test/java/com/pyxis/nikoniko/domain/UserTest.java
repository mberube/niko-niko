package com.pyxis.nikoniko.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class UserTest {
    @Test
    public void nameCanBeRetrieved() {
	User user = new User("bob");
	assertEquals("bob", user.getUsername());
    }
    
    @Test
    public void usersWithSameNameAreEqual() {
	User user = new User("bob");
	assertThat("users should be equal", user, equalTo(new User("bob")));
    }
    
    @Test
    public void usersWithDifferentNameAreNotEqual() {
	User user = new User("alice");
	assertThat("users should not be equal", user, not(equalTo(new User("bob"))));
    }
    
    @Test
    public void userShouldNotEqualsNull() {
	User user = new User("alice");
	assertThat("users should not be equal", user, not(equalTo(null)));
    }
}
