package com.pyxis.nikoniko.domain.calendar;

import java.util.List;

import com.pyxis.nikoniko.domain.User;


public interface CalendarRepository {
    void setMood(User user, CalendarDate date, MoodType mood);
    List<MoodType> getMoodsFor(User user);
    List<CalendarDate> getDaysForLastWeek();
}
