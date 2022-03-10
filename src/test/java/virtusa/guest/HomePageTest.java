package virtusa.guest;

import controllers.TestBase;
import virtusa.HomePage;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import virtusa.SearchPage;

public class HomePageTest extends TestBase {

    @Test(groups = { "test"}, priority = 1)
    public void searchTest()  {
        softAssert = new SoftAssert();
        
        HomePage.searchkeyword("test");
        HomePage.EnterSearch();
        //softAssert.assertEquals(HomePage.getgenderOption(),"Male", "Gender value is incorrect");
        softAssert.assertTrue(SearchPage.isDisplayedSearchPage("Virtusa Answers | Locations, Services, FAQs, Solutions | Virtusa"),
                "Search page is not Displayed!");
        softAssert.assertAll();
    }
    
}
