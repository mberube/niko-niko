package com.pyxis.nikoniko.domain;

import java.util.List;


public interface CalendarRepository {
    void setMood(User user, CalendarDate date, MoodType mood);
    List<MoodType> getMoodsFor(User user);
}
