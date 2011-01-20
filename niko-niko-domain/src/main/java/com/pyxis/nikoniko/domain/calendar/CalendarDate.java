package com.pyxis.nikoniko.domain.calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;
import org.joda.time.DateTimeConstants;
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

    public CalendarDate(int year, int month, int day) {
	this(new LocalDate(year, month, day));
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof CalendarDate)) {
	    return false;
	}
	CalendarDate that = (CalendarDate) obj;
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

    public long getMillisAtStartOfDay() {
	return calendarDay.toDateTimeAtStartOfDay().getMillis();
    }

    public String getDayOfTheWeek() {
	switch (calendarDay.getDayOfWeek()) {
	case DateTimeConstants.MONDAY:
	    return DayOfWeek.MONDAY.localized();
	case DateTimeConstants.TUESDAY:
	    return DayOfWeek.TUESDAY.localized();
	case DateTimeConstants.WEDNESDAY:
	    return DayOfWeek.WEDNESDAY.localized();
	case DateTimeConstants.THURSDAY:
	    return DayOfWeek.THURSDAY.localized();
	case DateTimeConstants.FRIDAY:
	    return DayOfWeek.FRIDAY.localized();
	case DateTimeConstants.SATURDAY:
	    return DayOfWeek.SATURDAY.localized();
	case DateTimeConstants.SUNDAY:
	    return DayOfWeek.SUNDAY.localized();
	default:
	    throw new IllegalArgumentException("Unkown day of the week");
	}
    }

    public String getDateAsString() {
	return normalize(calendarDay.getMonthOfYear()) + "/" + normalize(calendarDay.getDayOfMonth());
    }

    public String getFullDateAsString() {
	return normalize(calendarDay.getYear()) + "/" + normalize(calendarDay.getMonthOfYear()) + "/" + normalize(calendarDay.getDayOfMonth());
    }

    private String normalize(int number) {
	String value = Integer.toString(number);
	if (value.length() == 1) {
	    value = "0" + value;
	}
	return value;
    }
}
