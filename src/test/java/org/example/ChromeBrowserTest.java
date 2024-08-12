package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class ChromeBrowserTest {

    String url = "";

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserType browserType;
    protected BrowserContext context;
    protected Page page;

    @BeforeSuite
    public void startChromeBrowser(){
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch (new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions());

        page = browser.newPage();
        System.out.println("Browser Version: " + browser.version());

    }

    @Test
    public void openURL(){
        page.navigate(url);
    }

    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }




}
