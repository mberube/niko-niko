package com.pyxis.nikoniko.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pyxis.nikoniko.domain.calendar.Calendars;
import com.pyxis.nikoniko.view.transfer.CalendarView;

@Service
public class NikoCalendarView implements CalendarViewService {
    private final Calendars calendarService;

    @Autowired
    public NikoCalendarView(Calendars calendarService) {
	this.calendarService = calendarService;
    }

    public CalendarView getWeeklyCalendar() {
	return new CalendarView(calendarService.weekly());
    }
}
