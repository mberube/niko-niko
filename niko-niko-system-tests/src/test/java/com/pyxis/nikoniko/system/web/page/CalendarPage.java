package com.pyxis.nikoniko.system.web.page;

import static org.hamcrest.CoreMatchers.containsString;
import static org.openqa.selenium.By.cssSelector;

import org.hamcrest.MatcherAssert;

import com.objogate.wl.web.AsyncWebDriver;

public class CalendarPage extends Page {

    public CalendarPage(AsyncWebDriver browser) {
	super(browser);
    }

    public void addUser(String username) {
	browser.element(cssSelector("#newUser")).type(username+"\n");
    }
    
    public void showsEmptyCalendar() {
	browser.element(cssSelector("#myGrid .slick-row")).assertDoesNotExist();
    }

    public void showsUserInCalendar(String user) {
	browser.element(cssSelector("#myGrid .slick-row[row='0'] .c0")).assertText(containsString(user));
    }
}
