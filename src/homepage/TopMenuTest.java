package homepage;

import com.google.common.base.Verify;
import javafx.scene.control.Tab;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.awt.*;
import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }


        /*/method with name "selectMenu" it has one parameter name "menu" of type string
This method should click on the menu whatever name is passed as parameter.*/

    public void selectMenu(String menu, By by) {
        List<WebElement> names = driver.findElements(by);
        for (WebElement name : names) {
            if (name.getText().equalsIgnoreCase(menu)) {
                name.click();
                break;
            }

        }
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        mouseHoverToElementAndClick(By.linkText("Desktops"));  //    1.1 Mouse hover on “Desktops” Tab and click
        selectMenu("Show All Desktops", By.linkText("Show All Desktops"));  //  1.2 call selectMenu method and pass the menu = “Show All Desktops”  //a[contains(text(),'Show All Desktops')]
        verifyExpectedAndActual(By.xpath("//div[@class='col-sm-9']/h2"), "Desktops");  //a[text() ='Show All Desktops']  //  1.3 Verify the text ‘Desktops’

    }

    @Test

    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        mouseHoverToElementAndClick(By.linkText("Laptops & Notebooks"));        //  2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        selectMenu("Show All Laptops & Notebooks", By.linkText("Laptops & Notebooks"));   // 2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”  //a[contains(text(),'Show All Laptops & Notebooks')]
        verifyExpectedAndActual(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[2]/a[1]"),"Laptops & Notebooks");
        //verifyExpectedAndActual(By.xpath("//div[@id='content']/h2"), "Laptops & Notebooks");   // 2.3 Verify the text ‘Laptops & Notebooks’  //h2[contains(text(),'Laptops & Notebooks')]
    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        mouseHoverToElementAndClick(By.linkText("Components"));  //  3.1 Mouse hover on “Components” Tab and click
        selectMenu("Show All Components", By.xpath("//a[contains(text(),'Show All Components')]"));   //  3.2 call selectMenu method and pass the menu = “Show All Components”
        verifyExpectedAndActual(By.xpath("//h2[contains(text(),'Components')]"), "Components");   //  3.3 Verify the text ‘Components’
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}

