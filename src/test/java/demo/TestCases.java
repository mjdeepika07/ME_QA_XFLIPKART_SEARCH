package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


//import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Assert;

public class TestCases {
    static ChromeDriver driver;
    static WebDriverWait wait;
    
    

         
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        //NOT NEEDED FOR SELENIUM MANAGER
        //WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */


    //testCase01: Go to www.flipkart.com. Search "Washing Machine". Sort by popularity and print the count of items with rating less than or equal to 4 stars.
    @Test
    public void testCase01(){
        
        try{
            
            System.out.println();
            System.out.println("Go to www.flipkart.com. Search \"Washing Machine\". Sort by popularity and print the count of items with rating less than or equal to 4 stars.\n");
            String url = "https://www.flipkart.com/";
            //Wrapper method to open a url
            Wrappers.wrapper_getUrl(driver, url);
            //Input string
            String searchString = "Washing Machine";

            WebElement weSearchString = driver.findElement(By.xpath("//input[@name='q']"));
            //Wrapper method to search for a given input string
            Wrappers.wrapper_searchString(driver, wait, weSearchString, searchString);
            //Wrapper method used to sort the search results using the 'sort by popularity' filter
            Wrappers.wrapper_sortByPopularity(driver, wait);
            //Wrapper method to count the search items having rating less than or equal to 4
            Wrappers.wrapper_countOfItemsRatingLessThanOrEqualTo4(driver, wait);
            

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    
    }

     
    @Test
    public void testCase02(){
        try{

            System.out.println();
            System.out.println("Search \"iPhone\", print the Titles and discount % of items with more than 17% discount\n");
            //Input search string
            String searchString = "iPhone";

            WebElement weSearchString = driver.findElement(By.xpath("//input[@name='q']"));
            //Wrapper method to search for the input string
            Wrappers.wrapper_searchString(driver, wait, weSearchString, searchString);
            //Wrapper method to get the items with discount more than 17%
            Wrappers.getItemsDiscountMoreThan17(driver, wait);
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
 
    }

    @Test
    public void testCase03(){
        
        try{
            
            System.out.println();
            System.out.println("Search \"Coffee Mug\", select 4 stars and above, and print the Title and image URL of the 5 items with highest number of reviews\n");
            //Input search string
            String searchString = "Coffee Mug";
            WebElement weSearchString = driver.findElement(By.xpath("//input[@name='q']"));
            wait.until(ExpectedConditions.visibilityOf(weSearchString));
            //Wrapper method to search for the input string
            Wrappers.wrapper_searchString(driver, wait, weSearchString, searchString);
            //Input rating value
            String ratingInputString = "4★ & above";
            //Call a Wrapper method to get the Items based on Input Rating value
            Wrappers.getItemsBasedOnRatings(driver, wait, ratingInputString);
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
 
    }

    
    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}