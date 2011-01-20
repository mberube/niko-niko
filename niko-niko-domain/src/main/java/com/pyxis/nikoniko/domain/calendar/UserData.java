package com.pyxis.nikoniko.domain.calendar;

import java.util.List;

public class UserData {
    private final String username;
    private final List<Integer> moodsAsIn;

    public UserData(String username, List<Integer> moodsAsIn) {
	this.username = username;
	this.moodsAsIn = moodsAsIn;
    }

    public String getUsername() {
        return username;
    }

    public List<Integer> getMoodsAsIn() {
        return moodsAsIn;
    }
}
