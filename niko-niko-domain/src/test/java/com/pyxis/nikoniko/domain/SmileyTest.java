package com.pyxis.nikoniko.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SmileyTest {
    @Test
    public void canRetrieveSmileyFromInt() {
	assertEquals(Smiley.HAPPY, Smiley.valueFromInt(0));
	assertEquals(Smiley.NEUTRAL, Smiley.valueFromInt(1));
	assertEquals(Smiley.MAD, Smiley.valueFromInt(2));
    }

    @Test(expected=IllegalArgumentException.class)
    public void throwExceptionIfSmileyNotFound() {
	Smiley.valueFromInt(4);
    }
}
