package com.pyxis.nikoniko.domain.calendar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pyxis.nikoniko.domain.calendar.MoodType;

public class MoodTypeTest {
    @Test
    public void canRetrieveSmileyFromInt() {
	assertEquals(MoodType.UNKNOWN, MoodType.valueFromInt(0));
	assertEquals(MoodType.HAPPY, MoodType.valueFromInt(1));
	assertEquals(MoodType.NEUTRAL, MoodType.valueFromInt(2));
	assertEquals(MoodType.MAD, MoodType.valueFromInt(3));
	assertEquals(MoodType.ABSENT, MoodType.valueFromInt(4));
    }

    @Test(expected=IllegalArgumentException.class)
    public void throwExceptionIfSmileyNotFound() {
	MoodType.valueFromInt(6);
    }
}
