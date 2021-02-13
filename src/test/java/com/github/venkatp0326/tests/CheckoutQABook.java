package com.github.venkatp0326.tests;

import com.github.venkatp0326.base.UITest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutQABook extends UITest {

    @Test
    public void main()  {
        System.out.println("Test");
        driver.get("http://www.amazon.com");

        //search text
        driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys("qa testing for beginners");
        driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]")).click();


        //get links of results
        List<WebElement> results = driver.findElements(By.xpath("//span[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//h2/a"));
        System.out.println("Size = "+results.size());

        //click link
        results.get(0).click();


        System.out.println("End");

    }

}
