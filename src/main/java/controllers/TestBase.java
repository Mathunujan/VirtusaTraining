package controllers;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import virtusa.HomePage;
import virtusa.LoginPage;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public SoftAssert softAssert;

    public static final Logger LOGGER = Logger.getLogger(TestBase.class);

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        System.out.println("Test Running " + this.getClass().toString());
    }

    @BeforeSuite(alwaysRun = true)
    public void loadBrowser() {

        LOGGER.info("Initiate Browser");
        try {
            PageBase.initiateDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        PageBase.implicitWait(10);
        LOGGER.info("Browser Initiated");


    }
//    @BeforeTest(alwaysRun = true)
//    public void dashboard(){
//
//        PageBase.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        softAssert = new SoftAssert();
//        softAssert.assertTrue(HomePage.isVirtusaDashboardDisplayed("Virtusa: Digital Innovation Engineering  | Virtusa"), "Virtusa Dashboard Page is not Displayed");
//
//        softAssert.assertAll();
//        LOGGER.info("Dashboard here!");
//    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        softAssert = new SoftAssert();
    }

    @BeforeMethod(alwaysRun = true)
    public void nameBefore(Method method) {
        LOGGER.info("Test name: " + method.getName());
    }



    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {

        LOGGER.info("Closing Browser");
         PageBase.closeDriver();
        LOGGER.info("Browser Closed");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method method, ITestResult result) {

        LOGGER.info("Executed test case name:" + method.getName() + " Execution Results : " + result.toString());
    }
    @AfterTest
    public void afterTest(){
        LOGGER.info("Stop Testcase");
    }

}
