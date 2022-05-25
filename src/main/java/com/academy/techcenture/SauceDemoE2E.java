package com.academy.techcenture;

import com.github.javafaker.App;
import com.google.common.base.Verify;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;

import java.awt.*;
import java.time.Duration;

public class SauceDemoE2E {

    private static final String URL = "https://www.saucedemo.com/";

    public static void main(String[] args) {

        // 1. Launch the chrome browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //2. Go to https://www.saucedemo.com/
        driver.get(URL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //3. Verify the title of the page
        //title[text()='Swag Labs']
        boolean title = driver.getTitle().equals("Swag Labs");
        System.out.println  ( title? "Title is correct. " + title: "Title is NOT correct." );


        //4. Verify the SwagLabs Logo and Bot image
        WebElement loginLogo = driver.findElement(By.className("login_logo"));
        if (loginLogo.isDisplayed()) {
            System.out.println("Login Logo is displayed!");
        }else {
            System.out.println("Login Logo is NOT displayed!");
        }

        WebElement loginBotPicture = driver.findElement(By.className("bot_column"));
        if (loginBotPicture.isDisplayed()) {
            System.out.println("Login Bot picture is displayed!");
        }else{
            System.out.println("Login Bot picture is NOT displayed!");
        }


        //5. Use these credentials to login
        //   Username: “standard_user”.
        //   Password: “secret_sauce”

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.id("login-button"));
        loginBtn.click();


        //6. Verify title when user logs in
        title = driver.getTitle().equals("Swag Labs");
        System.out.println  ( title? "Title is correct. " + title: "Title is NOT correct." );


        //7. Verify Products title on top left
        WebElement titleProducts = driver.findElement(By.className("title"));
        if (titleProducts.isDisplayed()) {
            String titleText = titleProducts.getText();
            String expectedTitle = "PRODUCTS!";
            System.out.println("Except it "+ titleText);
        }else {
            System.out.println("Title is NOT displayed");
        }




       /*


        8. Click on Menu on Top left
        9. Verify there are 4 links: All items, About, Logout and Reset App State
        10. Click on about link (this will take the driver to another page)
        11. Verify the title of the new tab is “Cross Browser Testing, Selenium Testing, Mobile Testing | Sauce Labs”
        12. Come back to previous page
        13. Close the menu panel
        14. Verify there are 6 products
        15. Click on filter on top right and select PRICE(LOW TO HIGH)
        16. Verify the price of first item is $7.99 and last one is $49.99
        17. Click on the title of the first product
        18. Verify Title, description, price and add to cart button is displayed
        19. Verify the price is $7.99
        20. Click on add to cart button
        21. Verify Add to cart button has changed to Remove on the button
        22. Click on Cart icon on top right
        23. Verify Your Cart header is there
        23. Verify QTY is 1
        24. Verify Checkout button is enabled
        25. Click on Checkout button
        26. Fill out the form with random First name , Last name and zip code (use faker dependency)
        27. Verify Continue button is enabled and click it
        28. Verify CHECKOUT:OVERVIEW header is there
        29. Verify payment information is there and print the confirmation number on the console
        30. Verify shipping information is : “FREE PONY EXPRESS DELIVERY!”
        31. Verify the total price:
        read the item total and tax and add them up and verify if it’s matching the total price web element.
        32. Verify finish button is enabled and click it
        33. Verify “Thank you for your order”, “You order has been dispatched, and will arrive just as fast as the pony can get there!“, and image are displayed
        34. Verify Back home is enabled and click it
        35. Click on menu and click on logout link
        36. Wait for 3 seconds and close the driver*/







    }//end main

}
