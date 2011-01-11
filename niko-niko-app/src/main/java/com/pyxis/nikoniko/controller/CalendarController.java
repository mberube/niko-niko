package com.pyxis.nikoniko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.UserRepository;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {

    private final UserRepository userRepository;

    @Autowired
    public CalendarController(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<String> users() {
	Iterable<String> iter = Iterables.transform(userRepository.list(), new Function<User, String>() {
	    public String apply(User user) {
		return user.getUsername();
	    }
	});
	return Lists.newArrayList(iter);
    }
}
