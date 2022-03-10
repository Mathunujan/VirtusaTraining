package virtusa;

import controllers.PageBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import javax.swing.*;

public class HomePage extends PageBase {

    private static String dashboardDisplay = getDriver().getTitle();
    private static By searchBar = By.id("yxt-SearchBar-input--HeroBannerSearch");

    private static By SearchSubmit = By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/section/div[1]/div[1]/div/div[2]/div/div/form/button[1]"); //js-yext-submit yxt-SearchBar-button


    public static boolean isVirtusaDashboardDisplayed(String title) {
        System.out.println("Text:-"+dashboardDisplay);
        System.out.println("Text2:-"+title);
        System.out.println(dashboardDisplay.equalsIgnoreCase(title));
        return dashboardDisplay.equals(title);
    }
    public static void searchkeyword(String keyWord ) {
    	getDriver().findElement(searchBar).click();
        getDriver().findElement(searchBar).sendKeys(keyWord);
    }
    public static void ClickSearch() {
        getDriver().findElement(SearchSubmit).click();
    }

    public static void EnterSearch() {
        getDriver().findElement(searchBar).sendKeys(Keys.ENTER);
    }

    
    
    

    


}


