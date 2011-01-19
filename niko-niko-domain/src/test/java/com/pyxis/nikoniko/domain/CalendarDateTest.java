package com.pyxis.nikoniko.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsIn.isIn;
import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.Test;

public class CalendarDateTest {
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
	org.hamcrest.MatcherAssert.assertThat("", today.getCalendarDay().toDateTimeAtStartOfDay().getMillis()-yesterday.getCalendarDay().toDateTimeAtStartOfDay().getMillis() == 24*60*60*1000);
    }
    
    @Test
    public void canRetrieveDateFromAWeekAgo() {
	CalendarDate today = new CalendarDate();
	CalendarDate aWeekAgo = today.aWeekAgo();
	org.hamcrest.MatcherAssert.assertThat("", today.getCalendarDay().toDateTimeAtStartOfDay().getMillis()-aWeekAgo.getCalendarDay().toDateTimeAtStartOfDay().getMillis() == 7*24*60*60*1000);
    }
}
