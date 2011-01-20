package com.pyxis.nikoniko.domain.calendar;

import java.util.ResourceBundle;

public enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    
    private ResourceBundle bundle = ResourceBundle.getBundle("com/pyxis/nikoniko/i18n/nikoniko");
    
    public String localized() {
	return bundle.getString(this.name().toLowerCase());
    }
}
