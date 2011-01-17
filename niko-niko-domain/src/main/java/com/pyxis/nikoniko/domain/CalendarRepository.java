package com.pyxis.nikoniko.domain;


public interface CalendarRepository {
    void setMood(User user, CalendarDate date, Smiley mood);
}
