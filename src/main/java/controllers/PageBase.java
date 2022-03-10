package controllers;



import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.Constants;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;




public class PageBase {
    public SoftAssert softAssert;

    private static final Logger LOGGER = Logger.getLogger(PageBase.class);
    private static WebDriver driver;

    private static String baseUrl = "https://www.virtusa.com/";
    private static String webDriverLocation = "src"+ File.separator+"main"+File.separator+"resources"+File.separator+"drivers"+File.separator;
    protected static String osType = System.getProperty("os.type", Constants.WINDOWS);
    protected static String driverType = System.getProperty("browser.type", Constants.CHROME);

    /**
     * Initialize webdriver, set driver path and maximize chrome browser window
     */
    public static void initiateDriver() throws MalformedURLException {
        staticWait(1);
        switch (driverType) {
            case Constants.CHROME:
                if(osType.equals(Constants.UBUNTU))
                    System.setProperty("webdriver.chrome.driver", webDriverLocation + "chromedriver");
                else
                    System.setProperty("webdriver.chrome.driver", webDriverLocation + "chromedriver.exe");

                driver = new ChromeDriver();
                break;
            case Constants.FIREFOX:
                if(osType.equals(Constants.UBUNTU))
                    System.setProperty("webdriver.gecko.driver", webDriverLocation + "geckodriver");
                else
                    System.setProperty("webdriver.gecko.driver", webDriverLocation + "geckodriver.exe");



                driver = new FirefoxDriver();
                break;
        }
        getDriver().manage().window().maximize();
        getDriver().get(baseUrl);

        LOGGER.info(" WebsiteURL:"+baseUrl);
        LOGGER.info(" Chrome Type :"+driverType);
        LOGGER.info(" Os Type :"+osType);

    }

    /*
     * Get web driver instance
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Close web driver instances
     */
    public static void closeDriver() {
        getDriver().quit();
        staticWait(1);
    }

    /**
     * Refresh web driver instances
     */
    public static void refreshDriver() {
        getDriver().navigate().refresh();
    }

    /**
     * Navigate Back
     */
    public static void navigateBack() {
        getDriver().navigate().back();
    }

    /**
     * GetCurrent Window Details
     */
    public static String getCurrentWindow() {
        return getDriver().getWindowHandle();
    }

    /**
     * Navigate to Window By Title
     * @param windowName
     */
    public static void navigateToWindow(String windowName) {
        getDriver().switchTo().window(windowName);
    }

    /**
     * Static Wait
     */
    public static void staticWait(int seconds) {
        try {
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implicit Wait
     */
    public static void implicitWait(int seconds) {
        getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * Explicit Wait Clickable
     */
    public static void waiTillClickable(By element , int seconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Explicit Wait Visible
     */
    public static void waiTillVisible(By element ,int seconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
// get screenshort
    public static String getScreenshot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/src/test/resources//screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);

        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
