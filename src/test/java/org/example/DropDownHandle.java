package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DropDownHandle {
    protected String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";

    Playwright playwright;
    BrowserType browserType;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeSuite
    public void startChromeBrowser() {
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions());

        page = browser.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    // @Test
    public void selectByIndex() throws InterruptedException {
        page.navigate(url);
        ElementHandle dropdown = page.querySelector("#state");
        dropdown.selectOption(new SelectOption().setIndex(2));
        Thread.sleep(5000);
    }

    // @Test
    public void selectByValue() throws InterruptedException {
        page.navigate(url);
        ElementHandle dropdown = page.querySelector("#state");
        dropdown.selectOption(new SelectOption().setValue("Rajasthan"));
        Thread.sleep(5000);
    }

    @Test
    public void selectByVisibleText() throws InterruptedException {
        page.navigate(url);
        ElementHandle dropdown = page.querySelector("#state");
        dropdown.selectOption(new SelectOption().setLabel("NCR"));
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}
