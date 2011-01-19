package com.pyxis.nikoniko.domain;

public enum MoodType {
    UNKNOWN, HAPPY, NEUTRAL, MAD, ABSENT;

    public static MoodType valueFromInt(int index) {
	MoodType[] values = MoodType.values();
	for(int i=0; i<values.length; i++) {
	    if(values[i].ordinal() == index) {
		return values[i];
	    }
	}
	throw new IllegalArgumentException("Mood type not found for index + " + index);
    }
}

