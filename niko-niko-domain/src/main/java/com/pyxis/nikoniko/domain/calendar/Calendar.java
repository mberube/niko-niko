package com.pyxis.nikoniko.domain.calendar;

import java.util.List;

public class Calendar {

    private final List<UserData> userData;
    private final List<CalendarDate> dates;

    public Calendar(List<CalendarDate> dates, List<UserData> userData) {
	this.dates = dates;
	this.userData = userData;
    }

    public List<UserData> getUserData() {
        return userData;
    }

    public List<CalendarDate> getDates() {
        return dates;
    }
}
