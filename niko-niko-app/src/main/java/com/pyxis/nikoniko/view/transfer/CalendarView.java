package com.pyxis.nikoniko.view.transfer;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.pyxis.nikoniko.domain.calendar.Calendar;
import com.pyxis.nikoniko.domain.calendar.CalendarDate;
import com.pyxis.nikoniko.domain.calendar.UserData;

public class CalendarView {
    private final Calendar calendar;

    public CalendarView(Calendar calendar) {
	this.calendar = calendar;
    }

    public List<UserData> getRows() {
        return calendar.getUserData();
    }

    public List<DateView> getColumnTitles() {
        List<CalendarDate> dates = calendar.getDates();
        Iterable<DateView> transform = Iterables.transform(dates, new Function<CalendarDate, DateView>() {
	    public DateView apply(CalendarDate date) {
	        return new DateView(date.getDateAsString(), date.getDayOfTheWeek());
            }
	});
        return Lists.newArrayList(transform);
    }
}
