package com.pyxis.nikoniko.system.web.page;

import static org.hamcrest.Matchers.containsString;
import static org.openqa.selenium.By.cssSelector;

import com.objogate.wl.web.AsyncWebDriver;

public class HomePage extends Page {

    public HomePage(AsyncWebDriver browser) {
	super(browser);
    }

    public void hasInputText(String text) {
	browser.element(cssSelector("#newUser")).assertValue(containsString(text));
    }
}
