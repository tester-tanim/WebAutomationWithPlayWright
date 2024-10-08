package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class NavigationCommand {
    protected String url = "https://www.tutorialspoint.com/selenium/practice/register.php";

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
    public void navigation() throws InterruptedException {
        page.navigate(url);
        ElementHandle logo = page.querySelector(".logo-desktop");
        logo.click();
        Thread.sleep(3000);

        page.goBack();
        Thread.sleep(3000);

        page.goForward();
        Thread.sleep(3000);

        page.goBack();
        Thread.sleep(3000);

        page.reload();
        Thread.sleep(3000);
    }

    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}
