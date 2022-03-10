package virtusa;

import controllers.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class SearchPage extends PageBase {

    private static String SearchPageTitle = getDriver().getTitle();

    private static By SearchSubmit = By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/section/div[1]/div[1]/div/div[2]/div/div/form/button[1]"); //js-yext-submit yxt-SearchBar-button




    public static boolean isDisplayedSearchPage(String title) {

        return SearchPageTitle.equals(title);
    }
    
    
    

    


}


