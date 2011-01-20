package com.pyxis.nikoniko.domain.calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class CalendarDateTest {
    
    @Before
    public void resetTime() {
	DateTimeUtils.setCurrentMillisSystem();
    }
    
    @Test
    public void canCreateDateFromTimestamp() {
	LocalDate reference = new LocalDate(2009, 3, 18);
	CalendarDate date = new CalendarDate(reference.toDateTimeAtCurrentTime().getMillis());
	
	assertEquals(reference.getYear(), date.getCalendarDay().getYear());
	assertEquals(reference.getDayOfMonth(), date.getCalendarDay().getDayOfMonth());
	assertEquals(reference.getMonthOfYear(), date.getCalendarDay().getMonthOfYear());
    }
    
    @Test
    public void equalsCalendarDates() {
	long timestamp = 123556;
	assertThat("date should be equals", new CalendarDate(timestamp), equalTo(new CalendarDate(timestamp)));
    }
    
    @Test
    public void twoDatesAreEqualsIfOnTheSameDay() {
	long timestamp = 123556;
	assertThat("dates should be equals on same day", new CalendarDate(timestamp), equalTo(new CalendarDate(timestamp+1000)));
    }
    
    @Test
    public void twoDatesAreNotEqualsIfOnDifferenceDay() {
	long timestamp = 123556;
	assertThat("dates should not be equal on different days", new CalendarDate(timestamp), not(equalTo(new CalendarDate(timestamp+24*60*60*1000))));
    }
    
    @Test
    public void canRetrieveDateFromYesterday() {
	CalendarDate today = new CalendarDate();
	CalendarDate yesterday = today.yesterday();
	assertThat("one day period", today.getMillisAtStartOfDay()-yesterday.getMillisAtStartOfDay() == 24*60*60*1000);
    }
    
    @Test
    public void canRetrieveDateFromAWeekAgo() {
	CalendarDate today = new CalendarDate();
	CalendarDate aWeekAgo = today.aWeekAgo();
	assertThat("7 day period", today.getMillisAtStartOfDay()-aWeekAgo.getMillisAtStartOfDay() == 7*24*60*60*1000);
    }
    
    @Test
    public void dayOfTheWeekCanBeRetrieved() {
	DateTimeUtils.setCurrentMillisFixed(new LocalDate(2011, 1, 19).toDateTimeAtStartOfDay().getMillis());
	assertThat(DayOfWeek.WEDNESDAY.localized(), equalTo(new CalendarDate().getDayOfTheWeek()));
	
	DateTimeUtils.setCurrentMillisFixed(new LocalDate(2011, 1, 17).toDateTimeAtStartOfDay().getMillis());
	assertThat(DayOfWeek.MONDAY.localized(), equalTo(new CalendarDate().getDayOfTheWeek()));
    }
    
    @Test
    public void canRetrieveDateAsString() {
	assertThat(new CalendarDate(2010, 1, 19).getDateAsString(), equalTo("01/19"));
	assertThat(new CalendarDate(2010, 10, 22).getDateAsString(), equalTo("10/22"));
	assertThat(new CalendarDate(2010, 3, 2).getDateAsString(), equalTo("03/02"));
    }
}
