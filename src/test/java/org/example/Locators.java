package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class Locators {
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

    //@Test
    public void locateById() throws InterruptedException {
        page.navigate(url);

        ElementHandle firstName = page.querySelector("#name");
        firstName.fill("Ebrahim Hossain");
        Thread.sleep(5000);
    }

    //@Test
    public void locateByName() throws InterruptedException {
        page.navigate(url);

        ElementHandle email = page.querySelector("[name='email']");
        email.fill("Test@noemail.com");
        Thread.sleep(5000);
    }


    //@Test
    public void locateByLink() throws InterruptedException {
        page.navigate("https://www.tutorialspoint.com/selenium/practice/links.php");
        ElementHandle link = page.querySelector("a:has-text(\"Created\")");
        link.click();
        Thread.sleep(5000);
    }

    @Test
    public void locateByTagName() throws InterruptedException {
        page.navigate("https://www.tutorialspoint.com/selenium/practice/links.php");

        List<ElementHandle> links = page.querySelectorAll("a");
        System.out.println("Number of a elements: "+links.size());
        Thread.sleep(5000);
    }


    @Test
    public void locateByXpath() throws InterruptedException {
        page.navigate("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        ElementHandle name = page.waitForSelector("//input[@id='name']");
        ElementHandle email = page.waitForSelector("//input[@id='email']");
        ElementHandle radiobtn = page.querySelector("//div[3]//div[1]//div[1]//div[2]//input[1]");
        ElementHandle mobile = page.waitForSelector("//input[@id='mobile']");

        name.fill("Ishtiaque Ahmed Tanim");
        email.fill("ishtiaqueahmed1998@gmail.com");
        radiobtn.click();
        mobile.fill("01557272482");



    }




    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }

}
