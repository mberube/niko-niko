package com.pyxis.nikoniko.view.transfer;

public class DateView {
    private final String day;
    private final String date;

    public DateView(String date, String day) {
	this.date = date;
	this.day = day;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }
}
