package desktops;

import com.google.common.base.Verify;
import javafx.scene.control.Tab;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class DesktopsTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        mouseHoverToElementAndClick(By.linkText("Desktops"));  //    1.1 Mouse hover on “Desktops” Tab and click
        clickOnElement(By.xpath("//a[contains(text(),'Show All Desktops')]"));   // 1.2 Click on “Show All Desktops”
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (Z - A)");// 1.3 Select Sort By position "Name: Z to A"
        verifyExpectedAndActual(By.xpath("//option[contains(text(),'Name (Z - A)')]"), "Name (Z - A)");// 1.4 Verify the Product will arrange in Descending order.

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        mouseHoverToElementAndClick(By.linkText("Desktops"));  //    2.1 Mouse hover on “Desktops” Tab and click
        clickOnElement(By.xpath("//a[contains(text(),'Show All Desktops')]"));   // 2.2 Click on “Show All Desktops”
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (A - Z)");  // 2.3 Select Sort By position "Name: A to Z"
        clickOnElement(By.linkText("HP LP3065"));  // 2.4 Select product “HP LP3065”
        verifyExpectedAndActual(By.xpath("//h1[contains(text(),'HP LP3065')]"), "HP LP3065"); // 2.5 Verify the Text "HP LP3065"
        Thread.sleep(5000);
        //sendTextToElement(By.xpath("//input[@value = '2011-04-22']"),"2022-11-30");
        String year = "2022";
        String month = "November";
        String date = "30";
        Thread.sleep(2000);
// 2.6 Select Delivery Date "2022-11-30"
        // opens the date picker
        clickOnElement(By.xpath("//button[@class = 'btn btn-default']/i[@class='fa fa-calendar']"));

        while (true) {
            String monthYear = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[2]")).getText();
// Nov 2022
            String arr[] = monthYear.split(" ");
            String mon = arr[0];
            String yer = arr[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//body[1]/div[4]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[3]"));
            }
        }
        //Select Date
        List<WebElement> allDates = driver.findElements(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[5]/td[3]"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }


        clearText(By.cssSelector("#input-quantity"));
        sendTextToElement(By.cssSelector("#input-quantity"), "1"); // 2.7.Enter Qty "1” using Select class.
        clickOnElement(By.xpath("//button[@id='button-cart']"));// 2.8 Click on “Add to Cart” button
        Thread.sleep(2000);

        // 2.9 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        verifyExpectedAndActual(By.xpath("//div[@class ='alert alert-success alert-dismissible']"), "Success: You have added HP LP3065 to your shopping cart!\n" + "×");
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));// 2.10 Click on link “shopping cart” display into success message
        verifyExpectedAndActual(By.xpath("//a[contains(text(),'Shopping Cart')]"), "Shopping Cart");// 2.11 Verify the text "Shopping Cart"
        //verifyExpectedAndActual(By.xpath("//h1[contains(text(),' (1.00kg)')]"),"Shopping Cart  (1.00kg)");
        verifyExpectedAndActual(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"), "HP LP3065");  // 2.12 Verify the Product name "HP LP3065"
        Thread.sleep(2000);

        verifyExpectedAndActual(By.xpath("//small[contains(text(),'Delivery Date: 2022-11-30')]"), "Delivery Date: 2022-11-30");//2.13 Verify the Delivery Date "2022-11-30"
        verifyExpectedAndActual(By.xpath("//td[contains(text(),'Product 21')]"), "Product 21");//2.14 Verify the Model "Product21"
        //clickOnElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md' and text() ='Currency']"));
        Thread.sleep(3000);

        //Mouse hover on Currency Tab.and click
        mouseHoverToElementAndClick(By.xpath("//span[contains(text(),'Currency')]"));
        // Click on £ Pound Sterling
        clickOnElement(By.xpath("//button[contains(text(),'£ Pound Sterling')]"));
        verifyExpectedAndActual(By.xpath("//tbody/tr[1]/td[6]"), "£74.73");     //2.15 Verify the Todat "£74.73"


    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
