package myaccounts;

import com.google.common.base.Verify;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    public void selectMyAccountOptions(String option, By by) {
        List<WebElement> selectOptions = driver.findElements(by);
        for (WebElement opt : selectOptions) {
            if (opt.getText().equalsIgnoreCase(option)) {
                opt.click();
                break;
            }

        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {

        //1.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

//1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register", By.xpath("//span[contains(text(),'My Account')]"));
        clickOnElement(By.linkText("Register"));

// 1.3 Verify the text “Register Account”.
        verifyExpectedAndActual(By.xpath("//h1[contains(text(),'Register Account')]"), "Register Account");
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {

//2.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

//2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login", By.xpath("//span[contains(text(),'My Account')]"));
        clickOnElement(By.linkText("Login"));

//2.3 Verify the text “Returning Customer”.
        verifyExpectedAndActual(By.xpath("//h2[contains(text(),'Returning Customer')]"), "Returning Customer");
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
//  3.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

//  3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register", By.xpath("//span[contains(text(),'My Account')]"));
        clickOnElement(By.linkText("Register"));
//  3.3 Enter First Name
        sendTextToElement(By.xpath("//input[@id='input-firstname']"), "paul");
//  3.4 Enter Last Name
        sendTextToElement(By.id("input-lastname"), "Devis");
//  3.5 Enter Email
        sendTextToElement(By.id("input-email"), "ttting@gmail.com");
//  3.6 Enter Telephone
        sendTextToElement(By.id("input-telephone"), "07645854321");
//  3.7 Enter Password
        sendTextToElement(By.id("input-password"), "123589789");
//  3.8 Enter Password Confirm
        sendTextToElement(By.id("input-confirm"), "123589789");
// 3.9 Select Subscribe Yes radio button
        clickOnElement(By.xpath("//input[@type='radio' and @name = 'newsletter' and @value='1']"));
// 3.10 Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@type='checkbox']"));
//  3.11 Click on Continue button
        clickOnElement(By.xpath("//input[@type='submit']"));

//  3.12 Verify the message “Your Account Has Been Created!”
        verifyExpectedAndActual(By.xpath("//div[@id='content']/h1"), "Your Account Has Been Created!");
        //verifyExpectedAndActual(By.xpath("//h1[@xpath='1']"), "Your Account Has Been Created!");

//  3.13 Click on Continue button
        clickOnElement(By.linkText("Continue"));

//  3.14 Click on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

//  3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout", By.xpath("//body/nav[@id='top']/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[5]/a[1]"));

//  3.16 Verify the text “Account Logout”
        verifyExpectedAndActual(By.xpath("//h1[contains(text(),'Account Logout')]"), "Account Logout");
//  3.17 Click on Continue button
        clickOnElement(By.linkText("Continue"));


    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {

// 4.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

//4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login", By.xpath("//span[contains(text(),'My Account')]"));
        clickOnElement(By.linkText("Login"));

//  4.3 Enter Email address
        sendTextToElement(By.xpath("//input[@id='input-email']"), "tear12@gmail.com");

//  4.5 Enter Password
        sendTextToElement(By.xpath("//input[@id='input-password']"), "1256789");

//  4.6 Click on Login button
        clickOnElement(By.xpath("//input[@class='btn btn-primary']"));

//  4.7 Verify text “My Account”
        verifyExpectedAndActual(By.xpath("//span[contains(text(),'My Account')]"), "My Account");

//  4.8 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

//  4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout", By.xpath("//body/nav[@id='top']/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[5]/a[1]"));

//  4.10 Verify the text “Account Logout”
        verifyExpectedAndActual(By.xpath("//h1[contains(text(),'Account Logout')]"), "Account Logout");
//  4.11 Click on Continue button
        clickOnElement(By.linkText("Continue"));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
