package com.github.venkatp0326.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class UITest {

    public WebDriver driver;
    private static final String CHROME_DRIVER_PATH = "C:\\bin\\chromedriver.exe";
    private ChromeOptions chromeOptions= new ChromeOptions();

    @BeforeSuite
    public void setupSuite()
    {
        chromeOptions.addArguments("start-maximized");
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

    }

    @BeforeTest
    public void setupTest()
    {
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterTest
    public void exitTest()
    {
        driver.close();
    }

}
