package com.pyxis.nikoniko.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.google.common.base.Objects;

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

    public CalendarDate(long timestamp) {
	this(new LocalDate(timestamp));
    }

    public LocalDate getCalendarDay() {
	return calendarDay;
    }

    @Override
    public boolean equals(Object obj) {
	if(!(obj instanceof CalendarDate)) {
	    return false;
	}
	CalendarDate that = (CalendarDate)obj;
	return Objects.equal(this.calendarDay, that.calendarDay);
    }

    @Override
    public int hashCode() {
	return Objects.hashCode(calendarDay);
    }

    public CalendarDate aWeekAgo() {
	return new CalendarDate(calendarDay.minusDays(7));
    }

    public CalendarDate yesterday() {
	return new CalendarDate(calendarDay.minusDays(1));
    }
}
