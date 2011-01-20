package com.pyxis.nikoniko.view.transfer;

public class Day {
    private String name;
    private String date;
    
    public Day(String date, String name) {
	this.date = date;
	this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
