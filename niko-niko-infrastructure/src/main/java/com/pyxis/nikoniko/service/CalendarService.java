package com.pyxis.nikoniko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.pyxis.nikoniko.domain.User;
import com.pyxis.nikoniko.domain.UserRepository;
import com.pyxis.nikoniko.domain.calendar.Calendar;
import com.pyxis.nikoniko.domain.calendar.CalendarDate;
import com.pyxis.nikoniko.domain.calendar.CalendarRepository;
import com.pyxis.nikoniko.domain.calendar.Calendars;
import com.pyxis.nikoniko.domain.calendar.MoodType;
import com.pyxis.nikoniko.domain.calendar.UserData;

@Service
public class CalendarService implements Calendars {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    @Autowired
    public CalendarService(UserRepository userRepository, CalendarRepository calendarRepository) {
	this.userRepository = userRepository;
	this.calendarRepository = calendarRepository;
	
    }
    
    public Calendar weekly() {
	List<User> users = userRepository.list();
	List<UserData> rows = Lists.newArrayList();

	for (User user : users) {
	    List<MoodType> moods = calendarRepository.getMoodsFor(user);

	    Iterable<Integer> transform = Iterables.transform(moods, new Function<MoodType, Integer>() {
		public Integer apply(MoodType type) {
		    return type.ordinal();
		}
	    });

	    List<Integer> moodsAsIn = Lists.newArrayList(transform);
	    UserData row = new UserData(user.getUsername(), moodsAsIn);
	    rows.add(row);
	}
	List<CalendarDate> titles = calendarRepository.getDaysForLastWeek();
	return new Calendar(titles, rows);
    }

}
