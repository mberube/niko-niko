package com.pyxis.nikoniko.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Embeddable
public class CalendarDate {
    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
    private LocalDate calendarDay;

    public CalendarDate() {
	this(new LocalDate());
    }

    public CalendarDate(LocalDate date) {
	this.calendarDay = date;
    }

    public LocalDate getCalendarDay() {
	return calendarDay;
    }
}
