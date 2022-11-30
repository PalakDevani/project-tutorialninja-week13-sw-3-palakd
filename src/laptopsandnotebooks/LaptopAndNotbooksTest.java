package laptopsandnotebooks;

import com.google.common.base.Verify;
import javafx.scene.control.Tab;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class LaptopAndNotbooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test

    public void nameverifyProductsPriceDisplayHighToLowSuccessfully() {
        mouseHoverToElementAndClick(By.linkText("Laptops & Notebooks")); //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        clickOnElement(By.linkText("Show All Laptops & Notebooks")); //1.2 Click on “Show All Laptops & Notebooks”
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");  //   1.3 Select Sort By "Price (High > Low)"
        verifyExpectedAndActual(By.xpath("//option[contains(text(),'Price (High > Low)')]"), "Price (High > Low)"); // 1.4 Verify the Product price will arrange in High to Low order.

    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverToElementAndClick(By.linkText("Laptops & Notebooks"));

        //2.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.linkText("Show All Laptops & Notebooks"));

        //   2.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");

        // 2.4 Select Product “MacBook”
        clickOnElement(By.linkText("MacBook"));

        // 2.5 Verify the text “MacBook”
        verifyExpectedAndActual(By.xpath("//h1[contains(text(),'MacBook')]"), "MacBook");

        // 2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        Thread.sleep(3000);
        //verifyExpectedAndActual(By.cssSelector("body:nth-child(2) div.container:nth-child(4) > div.alert.alert-success.alert-dismissible"),"Success: You have added MacBook to your shopping cart!");  //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”

        // 2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));

        //2.9 Verify the text "Shopping Cart"
        verifyExpectedAndActual(By.xpath("//h1[contains(text(),' (0.00kg)')]"), "Shopping Cart  (0.00kg)");

        //2.10 Verify the Product name "MacBook"
        verifyExpectedAndActual(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"), "MacBook");

        Thread.sleep(3000);
        clearText(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]"));
        // 2.11 Change Quantity "2"
        sendTextToElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]"), "2");
        Thread.sleep(2000);
        //2.12 Click on “Update” javafx.scene.control.Tab
        clickOnElement(By.xpath("//button[@data-original-title='Update']"));

        verifyExpectedAndActual(By.xpath("//div[@class='alert alert-success alert-dismissible']"), "Success: You have modified your shopping cart!\n" + "×"); // i-Frame??//2.13 Verify the message “Success: You have modified your shopping cart!”
        //2.14 Verify the Total £737.45
        //verifyExpectedAndActual(By.xpath("//tbody/tr[1]/td[6]"),"£737.45");

        //2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//a[contains(text(),'Checkout')]"));

        //2.16 Verify the text “Checkout”
        verifyExpectedAndActual(By.xpath("//h1[contains(text(),'Checkout')]"), "Checkout");
        Thread.sleep(2000);

        // 2.17 Verify the Text “New Customer”
        verifyExpectedAndActual(By.xpath("//h2[contains(text(),'New Customer')]"), "New Customer");

        //2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@type='radio' and @value='guest']"));

        //2.19 Click on “Continue” tab
        clickOnElement(By.xpath("//input[@id='button-account']"));
        Thread.sleep(3000);

        //2.20 Fill the mandatory fields
        sendTextToElement(By.id("input-payment-firstname"), "Krishiv");
        sendTextToElement(By.id("input-payment-lastname"), "vama");
        sendTextToElement(By.id("input-payment-email"), "vama@gmail.com");
        sendTextToElement(By.id("input-payment-telephone"), "07214568758");
        sendTextToElement(By.id("input-payment-company"), "Prime");
        sendTextToElement(By.id("input-payment-address-1"), "Primetesting123");
        sendTextToElement(By.id("input-payment-city"), "Baroda");
        sendTextToElement(By.id("input-payment-postcode"), "gj1");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-payment-country']"), "India");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-payment-zone']"), "Gujarat");

        //2.21 Click on “Continue” Button
        clickOnElement(By.xpath("//input[@id='button-guest']"));

        //2.22 Add Comments About your order into text area
        sendTextToElement(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/p[2]/textarea[1]"), "Please message before delivery                                                       ");

        //2.23 Check the Terms & Conditions check box
        clickOnElement(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/input[1]"));

        //2.24 Click on “Continue” button
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));
        Thread.sleep(2000);

        //2.25 Verify the message “Warning: Payment method required!”
        //verifyExpectedAndActual(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]"),"Warning: No Payment options are available. Please contact us for assistance!");
        //(By.xpath("//div[@class='alert alert-danger alert-dismissible']")));
    }

    @After
    public void tearDown() {
        closeBrowser();

    }
}
