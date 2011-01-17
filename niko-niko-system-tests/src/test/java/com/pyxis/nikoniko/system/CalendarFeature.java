package com.pyxis.nikoniko.system;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pyxis.nikoniko.system.web.DatabaseDriver;
import com.pyxis.nikoniko.system.web.NikonikoDriver;

public class CalendarFeature {
    NikonikoDriver nikoniko = new NikonikoDriver();
    DatabaseDriver database = new DatabaseDriver();

    @Before
    public void startApplication() throws Exception {
	database.start();
	nikoniko.start();
    }

    @After
    public void stopApplication() throws Exception {
	nikoniko.stop();
	database.stop();
    }

    @Test
    public void addUser() throws Exception {
	String firstUser = "Jean-Marc Curé-Labelle";
	nikoniko.showsEmptyCalendar();
	nikoniko.addUser(firstUser);
	nikoniko.showsUserInCalendar(firstUser);
    }
}
