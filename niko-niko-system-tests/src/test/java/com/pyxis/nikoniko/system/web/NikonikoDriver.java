package com.pyxis.nikoniko.system.web;

import static com.pyxis.nikoniko.system.web.Routes.urlFor;
import static com.pyxis.nikoniko.system.web.serverdriver.AbstractServerDriverFactory.serverDriverFactory;
import static com.pyxis.nikoniko.system.web.webdriver.AbstractWebDriverFactory.webDriverFactory;

import org.openqa.selenium.WebDriver;

import com.objogate.wl.UnsynchronizedProber;
import com.objogate.wl.web.AsyncWebDriver;
import com.pyxis.nikoniko.system.web.page.HomePage;
import com.pyxis.nikoniko.system.web.serverdriver.ServerDriver;
import com.pyxis.nikoniko.system.web.serverdriver.ServerDriverFactory;
import com.pyxis.nikoniko.system.web.webdriver.WebDriverFactory;

public class NikonikoDriver {
    private final ServerDriverFactory serverDriverFactory = serverDriverFactory();
    private final WebDriverFactory webDriverFactory = webDriverFactory();

    private ServerDriver serverDriver;
    private WebDriver webdriver;
    private AsyncWebDriver browser;

    private HomePage homePage;
//    private CalendarPage calendarPage;

    public void start() throws Exception {
        createDrivers();
        startBrowser();
    }

    private void createDrivers() throws Exception {
        createServerDriver();
        createWebDriver();
        createPageDrivers();
    }

    private void createServerDriver() throws Exception {
        serverDriver = serverDriverFactory.newServerDriver();
    }

    private void createWebDriver() {
        webdriver = webDriverFactory.newWebDriver();
        browser = new AsyncWebDriver(new UnsynchronizedProber(), webdriver);
    }

    private void createPageDrivers() {
        homePage = new HomePage(browser);
//        calendarPage = new CalendarPage(browser);
    }

    private void startBrowser() {
        browser.navigate().to(urlFor(HomePage.class));
    }

    public void stop() throws Exception {
        webDriverFactory.disposeWebDriver(webdriver);
        serverDriverFactory.disposeServerDriver(serverDriver);
    }


}
