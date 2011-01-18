package com.pyxis.nikoniko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.pyxis.nikoniko.domain.CalendarDate;
import com.pyxis.nikoniko.domain.CalendarRepository;
import com.pyxis.nikoniko.domain.Maybe;
import com.pyxis.nikoniko.domain.Smiley;
import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.UserRepository;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {
    private final UserRepository userRepository;
    private final CalendarRepository calendarRepository;

    @Autowired
    public CalendarController(UserRepository userRepository, CalendarRepository calendarRepository) {
	this.userRepository = userRepository;
	this.calendarRepository = calendarRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<String> users() {
	Iterable<String> iter = Iterables.transform(userRepository.list(), new Function<User, String>() {
	    public String apply(User user) {
		return user.getUsername();
	    }
	});
	return Lists.newArrayList(iter);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestParam("username") String username) {
	userRepository.add(new User(username));
	return "OK";
    }
    
    public String setMood(@RequestParam("user") String username, @RequestParam("mood") long timestamp, @RequestParam("mood") int mood) {
	Maybe<User> user = userRepository.findByName(username);
	if(!user.exists()) {
	    throw new IllegalArgumentException("User not found");
	}
	Smiley smiley = Smiley.valueFromInt(mood);
	calendarRepository.setMood(user.bare(), new CalendarDate(timestamp), smiley);
	return "OK";
    }
}
