package com.academy.techcenture;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.compress.archivers.tar.TarArchiveStructSparse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import com.academy.techcenture.CommonMethods.*;

import static com.academy.techcenture.CommonMethods.*;

public class SauceDemoE2E{

    private static final String URL = "https://www.saucedemo.com/";

    public static void main(String[] args) throws InterruptedException {

        // 1. Launch the Chrome browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();


        // 2. Go to https://www.saucedemo.com/
        driver.get(URL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // 3. Verify the title of the page
        boolean title = driver.getTitle().equals("Swag Labs");
        System.out.println(title ? "Title is correct. " + title : "Title is NOT correct.");

        // 4. Verify the SwagLabs Logo and Bot image
        WebElement loginLogo = driver.findElement(By.className("login_logo"));
        if (loginLogo.isDisplayed()) {
            System.out.println("Login Logo is displayed!");
        } else {
            System.out.println("Login Logo is NOT displayed!");
        }

        WebElement loginBotPicture = driver.findElement(By.className("bot_column"));
        if (loginBotPicture.isDisplayed()) {
            System.out.println("Login Bot picture is displayed!");
        } else {
            System.out.println("Login Bot picture is NOT displayed!");
        }

        // 5. Use these credentials to login
        //   Username: “standard_user”.
        //   Password: “secret_sauce”
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.id("login-button"));
        loginBtn.click();

        // 6. Verify title when user logs in
        title = driver.getTitle().equals("Swag Labs");
        System.out.println(title ? "Title is correct. " + title : "Title is NOT correct.");

        // 7. Verify Products title on top left
        WebElement titleProducts = driver.findElement(By.className("title"));
        if (titleProducts.isDisplayed()) {
            String titleText = titleProducts.getText();
            String expectedTitle = "PRODUCTS";
            System.out.println("Excpected title: " + expectedTitle + "." + "Displayed title: " + titleText);
        } else {
            System.out.println("Title is NOT displayed");
        }
        Thread.sleep(1000);
        // 8. Click on Menu on Top left
        WebElement menuBtn = driver.findElement(By.id("react-burger-menu-btn"));
        menuBtn.click();
        Thread.sleep(1000);

        // 9. Verify there are 4 links: All items, About, Logout and Reset App State
        List<WebElement> menuElements = driver.findElements(By.xpath("//a[@class='bm-item menu-item']"));
        String[] menuOptions = {"all items", "about", "logout", "reset app state"};
        for (int i = 0; i < menuElements.size(); i++) {
            boolean menuItemCorrect = menuElements.get(i).getText().trim().toLowerCase().equals(menuOptions[i]);
            if (!menuItemCorrect) {
                System.out.println("Incorrect menu item: " + menuOptions[i]);
            }
        }

        // 10. Click on about link (this will take the driver to another page)
        WebElement aboutMenuLink = driver.findElement(By.id("about_sidebar_link"));
        aboutMenuLink.click();

        // 11. Verify the title of the new tab is “Cross Browser Testing, Selenium Testing, Mobile Testing | Sauce Labs”
        Thread.sleep(3000);
        title = driver.getTitle().equals("Cross Browser Testing, Selenium Testing, Mobile Testing | Sauce Labs");
        System.out.println(title ? "Title is correct. " + title : "Title is NOT correct.");

        // 12. Come back to previous page
        Thread.sleep(3000);
        driver.navigate().back();

        // 13. Close the menu panel
        WebElement closeMenu = driver.findElement(By.id("react-burger-cross-btn"));
        if (closeMenu.isDisplayed()) {
            closeMenu.click();
        }

        // 14. Verify there are 6 products
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        if (items.size() == 6) {
            System.out.println("Number of items: " + items.size() + " Should be 6.");
        }

        //15. Click on filter on top right and select PRICE(LOW TO HIGH)
        WebElement filterBtn = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(filterBtn);
        select.selectByValue("lohi");

        // 16. Verify the price of first item is $7.99 and last one is $49.99
        WebElement priceLow = driver.findElement(By.xpath("//div[@class='inventory_list']//div[1]//div[2]//div[2]//div[1]"));
        String [] priceArrLow = priceLow.getText().split("\\$");
        boolean price7_99 = priceArrLow[1].equals("7.99");
        System.out.println("Lower price is 7.99 --> " + price7_99 );

        WebElement priceHigh = driver.findElement(By.xpath("//div[6]//div[2]//div[2]//div[1]"));
        String [] priceArr = priceHigh.getText().split("\\$");
        boolean price49_99 = priceArr[1].equals("49.99");
        System.out.println("Higher price is 49.99 --> " + price49_99);

        // 17. Click on the title of the first product
        WebElement firstProduct = driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Onesie')]"));
        firstProduct.click();


        // 18. Verify Title, description, price and add to cart button is displayed
        WebElement firstProductDetail = driver.findElement(By.xpath("//div[contains(text(),'Sauce')]"));
        boolean firstProductTitle = firstProductDetail.getText().equalsIgnoreCase("Sauce Labs Onesie");
        if(firstProductTitle){
            System.out.println(firstProductDetail.getText());
        }
        WebElement firstProductDescription = driver.findElement(By.xpath("//div[contains(text(),'Rib snap')]"));
        boolean description = firstProductDescription.getText().contains("Rib");
        if (description){
            System.out.println(firstProductDescription.getText());
        }
        WebElement firstProductPrice = driver.findElement(By.xpath("(//div[@class='inventory_details_price'])[1]"));
        if (firstProductPrice.isDisplayed()){
            System.out.println(firstProductPrice.getText());
        }
        WebElement firstProductAddToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        System.out.println("Add to cart button is displayed :  " + firstProductAddToCartBtn.isDisplayed());


        // 19. Verify the price is $7.99
        WebElement price = driver.findElement(By.xpath("(//div[@class='inventory_details_price'])[1]"));
        String [] priceAr = price.getText().split("\\$");
        boolean price799 = priceAr[1].equals("7.99");
        System.out.println("Price is 7.99 --> " + price799);


        // 20. Click on add to cart button
        firstProductAddToCartBtn.click();


        // 21. Verify Add to cart button has changed to Remove on the button
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-onesie"));
        boolean remove = removeBtn.getText().equalsIgnoreCase("remove");
        if (remove){
            System.out.println("Remove Button is : " +  remove);
        }

        // 22. Click on Cart icon on top right
        WebElement cartBtn = driver.findElement(By.className("shopping_cart_link"));
        cartBtn.click();


        // 23. Verify Your Cart header is there
        WebElement yourCartHeader = driver.findElement(By.className("title"));
        if(yourCartHeader.isDisplayed()){
            System.out.println("Header is : "+yourCartHeader.getText());
        }

        // 23. Verify QTY is 1
        WebElement quantityOnCart = driver.findElement(By.xpath("//div[contains(text(),'1')]"));
        if(quantityOnCart.getText().equals("1")){
            System.out.println("On the cart QTY is : " + quantityOnCart.getText());
        }

        // 24. Verify Checkout button is enabled
        WebElement checkoutBtn = driver.findElement(By.id("checkout"));
        if(checkoutBtn.isDisplayed()){
            System.out.println("Checkout is enabled : " + checkoutBtn.getText());
        }

        // 25. Click on Checkout button
        checkoutBtn.click();


        // 26. Fill out the form with random First name , Last name and zip code (use faker dependency)
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys(randomFirstName());

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys(randomLastName());

        WebElement zipcode = driver.findElement(By.id("postal-code"));
        zipcode.sendKeys(randomZipcode());


        // 27. Verify Continue button is enabled and click it
        WebElement continueBtn = driver.findElement(By.id("continue"));
        if(continueBtn.isEnabled()){
            continueBtn.click();
        }

        // 28. Verify CHECKOUT:OVERVIEW header is there
        WebElement checkoutHeader = driver.findElement(By.className("title"));
        if(checkoutHeader.isDisplayed()){
            System.out.println("Checkout page header is : "+checkoutHeader.getText());
        }

        // 29. Verify payment information is there and print the confirmation number on the console
        WebElement paymentInformation = driver.findElement(By.xpath("//div[contains(text(),'SauceCard')]"));
        String [] paymentArr = paymentInformation.getText().split("#");
        System.out.println(paymentArr[1]);


        // 30. Verify shipping information is : “FREE PONY EXPRESS DELIVERY!”
        WebElement shippingInfo = driver.findElement(By.xpath("//div[contains(text(),'PONY')]"));
        if(shippingInfo.isDisplayed()){
            System.out.println("Shipping information is : " + shippingInfo.getText());
        }


        // 31. Verify the total price:
        // read the item total and tax and add them up and verify if it’s matching the total price web element.
        WebElement itemTotalPrice = driver.findElement(By.className("summary_subtotal_label"));
        String [] itemTotalPriceArr = itemTotalPrice.getText().split("\\$");
        boolean itemTotalPriceBoolean = itemTotalPriceArr[1].equals("7.99");
        if(itemTotalPriceBoolean){
            System.out.println("Total price is : " + itemTotalPriceArr[1]);
        }

        WebElement summaryTax = driver.findElement(By.className("summary_tax_label"));
        String [] summaryTaxArr = summaryTax.getText().split("\\$");
        double summaryTaxDouble =  Double.parseDouble(summaryTaxArr[1]);

        WebElement summaryTotal = driver.findElement(By.className("summary_total_label"));
        String [] summaryTotalArr = summaryTotal.getText().split("\\$");
        double summaryTotalDouble =  Double.parseDouble(summaryTotalArr[1]);
        
        double itemTotal = Double.parseDouble(itemTotalPriceArr[1]);
        double sumTaxAndTotal = (summaryTaxDouble+itemTotal);

        if(summaryTotalDouble == sumTaxAndTotal){
            System.out.println("Item total is : " + itemTotal);
            System.out.println("Tax is : " + summaryTaxDouble);
            System.out.println("Total is : " + summaryTotalDouble);
        }
        Thread.sleep(2000);


        // 32. Verify finish button is enabled and click it
        WebElement finishButton = driver.findElement(By.id("finish"));
        if(finishButton.isEnabled()){
            System.out.println("Finish button is enabled : " + finishButton.getText());
            finishButton.click();
        }

        Thread.sleep(2000);
        // 33. Verify “Thank you for your order”, “You order has been dispatched, and will arrive just as fast as the pony can get there!“,
        // and image are displayed
        WebElement completeHeader = driver.findElement(By.xpath("//span[@class='title']"));
        if(completeHeader.isDisplayed()){
            System.out.println("Complete header is displayed : " + completeHeader.getText());
        }
        WebElement completeText = driver.findElement(By.className("complete-text"));
        if(completeText.isDisplayed()){
            System.out.println("Complete text is displayed : " + completeText.getText());
        }
        WebElement ponyExpressImage = driver.findElement(By.className("pony_express"));
        if(ponyExpressImage.isDisplayed()){
            System.out.println("Image is displayed!");
        }

        // 34. Verify Back home is enabled and click it
        WebElement backHomeBtn = driver.findElement(By.id("back-to-products"));
        if(backHomeBtn.isEnabled()){
            System.out.println("Back home is enabled : " + backHomeBtn.getText());
            backHomeBtn.click();
        }

        // 35. Click on menu and click on logout link
        Thread.sleep(2_000);
        WebElement menuBtnRepeat = driver.findElement(By.id("react-burger-menu-btn"));
        menuBtnRepeat.click();
        Thread.sleep(1_000);
        WebElement logoutBtn = driver.findElement(By.id("logout_sidebar_link"));
        logoutBtn.click();


        // 36. Wait for 3 seconds and close the driver
        Thread.sleep(3000);
        driver.close();
        System.out.println("End to end test complete successfully!");


    }//end main

}//end SauceDemoE2E

