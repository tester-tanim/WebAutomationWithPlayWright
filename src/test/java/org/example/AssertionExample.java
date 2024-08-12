package org.example;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AssertionExample {
    protected String url = "https://playwright.dev/docs/intro";

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

    @Test
    public void openURL() {
        page.navigate(url);

        String actualTitle = page.title();
        System.out.println("Title: "+actualTitle);
        String expectedTitle = "Playwright";
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("Success");
    }

    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}
