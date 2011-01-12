package com.pyxis.nikoniko.system.web.webdriver;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {
    WebDriver newWebDriver();

    void disposeWebDriver(WebDriver webDriver);
}
