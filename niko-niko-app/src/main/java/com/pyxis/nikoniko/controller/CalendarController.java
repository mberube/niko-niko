package com.pyxis.nikoniko.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.pyxis.nikoniko.controller.transfer.NikoCale;
import com.pyxis.nikoniko.controller.transfer.RowData;
import com.pyxis.nikoniko.domain.CalendarDate;
import com.pyxis.nikoniko.domain.CalendarRepository;
import com.pyxis.nikoniko.domain.Maybe;
import com.pyxis.nikoniko.domain.MoodType;
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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
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

    @RequestMapping(value = "/mood", method = RequestMethod.POST)
    @ResponseBody
    public String setMood(@RequestParam("user") String username, @RequestParam("mood") long timestamp, @RequestParam("mood") int mood) {
	Maybe<User> user = userRepository.findByName(username);
	if (!user.exists()) {
	    throw new IllegalArgumentException("User not found");
	}
	MoodType smiley = MoodType.valueFromInt(mood);
	calendarRepository.setMood(user.bare(), new CalendarDate(timestamp), smiley);
	return "OK";
    }

    @RequestMapping(value = "/week", method = RequestMethod.GET)
    @ResponseBody
    public NikoCale getCalendar() {
	List<User> users = userRepository.list();
	List<RowData> rows = Lists.newArrayList();
	

	for (User user : users) {
	    List<MoodType> moods = calendarRepository.getMoodsFor(user);
	    
	    Iterable<Integer> transform = Iterables.transform(moods, new Function<MoodType, Integer>() {
		public Integer apply(MoodType type) {
	            return type.ordinal();
                }
	    });
	    
	    List<Integer> moodsAsIn = Lists.newArrayList(transform);
	    RowData row = new RowData(user.getUsername(), moodsAsIn);
	    rows.add(row);
	}
	return new NikoCale(rows);
    }
}
