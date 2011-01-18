package com.pyxis.nikoniko.domain;

public enum Smiley {
    HAPPY, NEUTRAL, MAD;

    public static Smiley valueFromInt(int index) {
	Smiley[] values = Smiley.values();
	for(int i=0; i<values.length; i++) {
	    if(values[i].ordinal() == index) {
		return values[i];
	    }
	}
	throw new IllegalArgumentException("Smiley not found");
    }
}

