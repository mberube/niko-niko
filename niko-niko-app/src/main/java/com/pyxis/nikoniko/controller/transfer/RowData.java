package com.pyxis.nikoniko.controller.transfer;

import java.util.List;

public class RowData {
    private String user;
    private List<Integer> moods;
    
    public RowData(String user, List<Integer> moods) {
	this.user = user;
	this.moods = moods;
    }

    public String getUser() {
        return user;
    }

    public List<Integer> getMoods() {
        return moods;
    }
}
