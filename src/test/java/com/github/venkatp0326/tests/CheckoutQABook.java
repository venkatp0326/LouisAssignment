package com.github.venkatp0326.tests;

import com.github.venkatp0326.base.UITest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
Test Objective:

1. Visit amazon.com Page
2. Search for Book 'qa testing for beginners'
3. Click on the 1st item in the listed results.

4. Before Click on add to cart Add to Cart asset price from Step3.
5. Click on Add to Cart.

6. Before Click on Proceed to Checkout asset price from Step3.
7. Click on proceed to checkout

8 . On the checkout page assert price from Step3.

 */

public class CheckoutQABook extends UITest {

    float priceFromResults;

    @Test
    public void main() throws InterruptedException {
        System.out.println("Test");

        searchAndOpenItem();
        addToCart();
        validatePreCart();
        checkout();

        System.out.println("End");

    }

    public void searchAndOpenItem()
    {

        driver.get("http://www.amazon.com");

        //search text
        driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys("qa testing for beginners");
        driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]")).click();

        String searchResultsXpathIdentifier = "//span[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]";

        //get top link in matching results
        WebElement result = driver.findElement(By.xpath(searchResultsXpathIdentifier + "//h2/a"));

        //get price
        String priceWhole = driver.findElement(By.xpath(searchResultsXpathIdentifier + "//span[@class='a-price-whole']")).getText();
        String priceFraction = driver.findElement(By.xpath(searchResultsXpathIdentifier + "//span[@class='a-price-fraction']")).getText();

        priceFromResults = Float.parseFloat(priceWhole + "." + priceFraction);
        System.out.println("Price from results = " + priceFromResults);

        //click result
        result.click();
    }

    public void addToCart() throws InterruptedException {
        //get price - dealing cases for new books
        String bookPagePriceStr = driver.findElement(By.xpath("//*[@id=\"newBuyBoxPrice\"]")).getText();
        float bookPagePrice = getPriceFromDollarString(bookPagePriceStr);
        System.out.println("price = " + bookPagePrice);

        Assert.assertEquals(bookPagePrice, priceFromResults);

        //Add to cart
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]")).click();
    }

    public void validatePreCart() throws InterruptedException {
        //check precart price
        String preCartPriceStr = driver.findElement(By.xpath("//*[@id=\"hlb-subcart\"]/div[1]/span/span[2]")).getText();

        float preCartPrice = getPriceFromDollarString(preCartPriceStr);
        System.out.println("PreCart Price = " + preCartPrice);
        Assert.assertEquals(preCartPrice, priceFromResults);

        //Proceed to checkout
        driver.findElement(By.xpath("//*[@id=\"hlb-ptc-btn-native\"]")).click();
    }

    public void checkout()
    {
        System.out.println("Final Checkout is intentionally left Undone due to login handling that needs real credentials and SMS verification");
        /*
        //enter email
        driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys("SampleEmail");
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();


        //enter password
        driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys("password");
        driver.findElement(By.xpath("//*[@id='signInSubmit']")).click();*/
    }

    private static float getPriceFromDollarString(String priceStr) {
        return Float.parseFloat(priceStr.replaceAll("[^\\d.]", ""));
    }

}
