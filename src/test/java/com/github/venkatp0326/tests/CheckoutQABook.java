package com.github.venkatp0326.tests;

import com.github.venkatp0326.base.UITest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CheckoutQABook extends UITest {

    @Test
    public void main() throws InterruptedException {
        System.out.println("Test");
        driver.get("http://www.amazon.com");

        //search text
        driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys("qa testing for beginners");
        driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]")).click();


        String searchResultsXpathIdentifier = "//span[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]";

        Thread.sleep(3000);

        //get links in matching results
        WebElement result = driver.findElement(By.xpath(searchResultsXpathIdentifier + "//h2/a"));


        //get price
        String priceWhole = driver.findElement(By.xpath(searchResultsXpathIdentifier + "//span[@class='a-price-whole']")).getText();
        String priceFraction = driver.findElement(By.xpath(searchResultsXpathIdentifier + "//span[@class='a-price-fraction']")).getText();

        float priceFromResults = Float.parseFloat(priceWhole+"."+priceFraction);
        System.out.println("Price from results = "+priceFromResults);

        System.out.println("Price = " + priceWhole + "." + priceFraction);


        //click link
        result.click();


        //get price - dealing cases for new books
        String bookPagePrice = driver.findElement(By.xpath("//*[@id=\"newBuyBoxPrice\"]")).getText();
        System.out.println("price = "+Float.parseFloat(bookPagePrice.replaceAll("[^\\d.]", "")));


        //Add to cart
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]")).click();


        //check precart price
        String preCartPriceStr = driver.findElement(By.xpath("//*[@id=\"hlb-subcart\"]/div[1]/span/span[2]")).getText();
        System.out.println("PreCart Price = "+preCartPriceStr);
        float preCartPrice = getPriceFromDollarString(preCartPriceStr);

        //Proceed to checkout
        driver.findElement(By.xpath("//*[@id=\"hlb-ptc-btn-native\"]")).click();

/*
        //enter email
        driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys("SampleEmail");
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();


        //enter password
        driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys("password");
        driver.findElement(By.xpath("//*[@id='signInSubmit']")).click();*/




        System.out.println("End");

    }

    private static float getPriceFromDollarString(String priceStr)
    {
        return Float.parseFloat(priceStr.replaceAll("[^\\d.]", ""));
    }

}
