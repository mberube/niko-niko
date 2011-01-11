package com.pyxis.nikoniko.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isIn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.UserRepository;

public class CalendarControllerTest {
    UserRepository userRepository = mock(UserRepository.class);;
    CalendarController controller = new CalendarController(userRepository);

    @Test
    public void shouldReturnEmptyListIfNoUsersAreFound() {
	when(userRepository.list()).thenReturn(Lists.<User> newArrayList());
	List<String> userList = controller.users();
	assertThat("user should be empty", userList.isEmpty());
    }

    @Test
    public void shouldReturnListOfUsersInRepository() {
	when(userRepository.list()).thenReturn(Lists.newArrayList(new User("bob")));
	List<String> userList = controller.users();
	assertThat("should contain", "bob", isIn(userList));
    }

    @Test
    public void shouldReturnManyUsersFromRepository() {
	when(userRepository.list()).thenReturn(Lists.newArrayList(new User("bob"), new User("alice")));
	List<String> userList = controller.users();
	assertThat("should contain", "alice", isIn(userList));
	assertThat("should contain", "bob", isIn(userList));
    }
}
