package demo.wrappers;
import demo.TestCases;
import dev.failsafe.internal.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    

    //Wrapper method to get the url
    public static void wrapper_getUrl(ChromeDriver driver, String url){
           //If the current url is not equal to the input url, then get the url using the get(url) method
            if(!driver.getCurrentUrl().equals(url))
                driver.get(url);
        
    }

    //Wrapper method used to search for a given input string
    public static void wrapper_searchString(ChromeDriver driver, WebDriverWait wait, WebElement weSearchString, String searchString){
        try{
            //Clear the existing value using the clear() method
            weSearchString.clear();
            //Use the sendKeys() method to send the input string
            weSearchString.sendKeys(searchString);
            //Use the Actions class to create an object

            Actions actions = new Actions(driver);
            //Using the class object use sendKeys(Keys.ENTER) send keys to the active element
            actions.sendKeys(Keys.ENTER);
            //Use the perform() method to perform the action
            actions.perform();

            //wait.until(ExpectedConditions.urlContains("search"));
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    //Wrapper method to sort the search results based on the popularity
    public static void wrapper_sortByPopularity(ChromeDriver driver, WebDriverWait wait){
        try{
            //wait for the webelement to be visible. Locate the 'Popularity' tab using the locator xpath : //div[@class='sHCOk2']//div[@class='zg-M3Z' and text()='Popularity']
            WebElement weSortByPopularity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sHCOk2']//div[@class='zg-M3Z' and text()='Popularity']")));
            //WebElement weSortByPopularity = driver.findElement(By.xpath("//div[@class='sHCOk2']//div[@class='zg-M3Z' and text()='Popularity']"));
            //Click on the 'Popularity tab'
            weSortByPopularity.click();
            //Aseert if the url contains 'Popularity' or not otherwise wait till the user is navigated to the correct url
            Assert.isTrue(wait.until(ExpectedConditions.urlContains("popularity")), "The user is not navigated to the correct url since the url does not contain popularity");
             
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    //Wrapper method to count the search items having rating less than or equal to 4
    public static void wrapper_countOfItemsRatingLessThanOrEqualTo4(ChromeDriver driver, WebDriverWait wait){
        try{    
                //Locate the List of WebElements using the Locator xpath : //div[@class='DOjaWF gdgoEp']//div[@class='XQDdHH' and text()<='4.0']
                //Wait until all the webelements are visible 
                List<WebElement> weItemsRatingLessThanOrEqualTo4 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='DOjaWF gdgoEp']//div[@class='XQDdHH' and text()<='4.0']")));
                //get the size of the List using size() method
                int count = weItemsRatingLessThanOrEqualTo4.size();
                System.out.println();
                //Print the count of elements having rating less than or equal to 4
                System.out.println("The count of items having rating less than or equal to 4 are : " + count);
                for ( WebElement webElement : weItemsRatingLessThanOrEqualTo4) {
                    System.out.println(webElement.getText());
                }
        
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    
    //Wrapper method to get the items with discount more than 17%
    public static void getItemsDiscountMoreThan17(ChromeDriver driver, WebDriverWait wait){

        try{
            Thread.sleep(5000);
            //Create a list of webelements wrt discount percentage using the locator xpath : //div[@class='KzDlHZ']/../following-sibling::div//div[@class='UkUFwK']/span
            //wait till all the elements are visible
            List<WebElement> weListOfItemsBasedOnDiscountPercentage = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='KzDlHZ']/../following-sibling::div//div[@class='UkUFwK']/span")));
            //Create one list for storing discount percentages of items satisfying the condition discount >17%
            //Another list for storing titles of items satisfying the condition discount >17%
            List<WebElement> weListOfItemsDiscountMoreThan17 = new LinkedList<>();
            List<WebElement> weListOfTitlesDiscountMoreThan17 = new LinkedList<>();
            
            //Iterate through the list of items from 0 to size() - 1
            for(int i = 0; i < weListOfItemsBasedOnDiscountPercentage.size(); i++){
                
                //Store the percentage (ex: 20% off) in a string variable
                String discountPercentageStr = weListOfItemsBasedOnDiscountPercentage.get(i).getText();
                //Split the string using '%'
                String[] percentageStr = discountPercentageStr.trim().split("%");
                //Store the resultant string in a variable
                String s = percentageStr[0];
                //Convert the string into Integer because the actual value needs to be compared with another integer( here 17)
                int num = Integer.parseInt(s);
                //If the value is > 17
                if(num > 17){
                       
                    Thread.sleep(3000);
                    //Add the webelement from 'weListOfItemsBasedOnDiscountPercentage' Array into 'weListOfItemsDiscountMoreThan17' Array
                    weListOfItemsDiscountMoreThan17.add(weListOfItemsBasedOnDiscountPercentage.get(i));
                    //Since the index of the webelements in DOM starts with 1 and the index of an array starts with 0, add 1 to index of array
                    int k = i+1;
                    //Locate the title of the web element using the Locator xpath : (//div[@class='KzDlHZ'])["+k+"]  where 'k' is the index of webelement
                    WebElement weTitleNew = driver.findElement(By.xpath("(//div[@class='KzDlHZ'])["+k+"]"));
                    //Add the title to the 'weListOfTitlesDiscountMoreThan17' Array
                    weListOfTitlesDiscountMoreThan17.add(weTitleNew);

                    
                }
            }
            
            //Iterate thru the Titles and Discounts arrays and print from index 0 to size()-1
            for(int j = 0; j < weListOfItemsDiscountMoreThan17.size() && j< weListOfTitlesDiscountMoreThan17.size(); j++){
                
                System.out.println();
                System.out.println(weListOfTitlesDiscountMoreThan17.get(j).getText());
                System.out.println(weListOfItemsDiscountMoreThan17.get(j).getText());
                

            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    //Call a Wrapper method to get the Items based on Input Rating value
    public static void getItemsBasedOnRatings(ChromeDriver driver, WebDriverWait wait, String ratingInputString){

        try{
            //JavascriptExecutor to scroll to the desired webelement
            JavascriptExecutor js = (JavascriptExecutor) driver;
            //Create a TreeMap object 'titlesHashMap' by inhereting the properties of 'SortedMap' Interface to store the <Ratings, Titles>
            SortedMap<Integer, String> titlesHashMap = new TreeMap<>();
            //Create a TreeMap object 'imageUrlsHashMap' by inhereting the properties of 'SortedMap' Interface to store the <Ratings, ImageUrls>
            SortedMap<Integer, String> imageUrlsHashMap = new TreeMap<>();

            Integer reviewsCountInteger=0;
            //Locate the checkbox webelement against the "4★ & above" ratingInputString using the Locator xpath : //div[@title='"+ratingInputString+"']//div[@class='XqNaEv']
            //wait until the element is clickable
            WebElement weRatingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='"+ratingInputString+"']//div[@class='XqNaEv']")));
            
            Thread.sleep(3000);
            //If the desired checkbox webelement is not displayed, then scroll the web page into the view of desired element
            if(!weRatingCheckbox.isDisplayed()){
                js.executeScript("arguments[0].scrollIntoView(true);", weRatingCheckbox);
            }
            //Click the checkbox using click() method
            weRatingCheckbox.click();
            Thread.sleep(3000);

            //Create a List of webelements using the locator className : wjcEIp
            List<WebElement> weListOfTitles = driver.findElements(By.className("wjcEIp"));
            //Iterate thru the list of titles from 0 to size()
            for(int i = 0; i < weListOfTitles.size(); i++){
                //Since the index of the webelements in DOM starts with 1 and the index of an array starts with 0, add 1 to index of array
                int index = i+ 1;
                //If the desired title webelement is not displayed, then scroll the web page into the view of desired element
                if(!weListOfTitles.get(i).isDisplayed()){
                    js.executeScript("arguments[0].scrollIntoView(true);", weListOfTitles.get(i));
                }

                //Locate the webelement to get the no.of reviews using the locator xpath : (//span[@class='Wphh3N'])[index]
                //WebElement weReview = driver.findElement(By.xpath("(//span[@class='Wphh3N'])["+index+"]"));
                WebElement weReview = driver.findElement(By.xpath("(//a[@class='wjcEIp']/following-sibling::div[@class='_5OesEi afFzxY']/span[@class='Wphh3N'])["+index+"]"));
                
                //Locate the webelement to get the image urls using the locator xpath : (//img[@class='DByuf4'])[index]
                WebElement weImageUrl = driver.findElement(By.xpath("(//img[@class='DByuf4'])["+index+"]"));

                //If the desired review webelement is not displayed, then scroll the web page into the view of desired element
                if(!weReview.isDisplayed()){
                    js.executeScript("arguments[0].scrollIntoView(true);", weReview);
                }
                //else If the desired imageurl webelement is not displayed, then scroll the web page into the view of desired element
                else if(!weImageUrl.isDisplayed()){
                    js.executeScript("arguments[0].scrollIntoView(true);", weImageUrl);
                }

                //Get the text of the no.of reviews of a particular item using getText() method(ex: (7,654)   )
                String reviewsStr = weReview.getText();
                //Replace the ')' using empty string (ex: (7,654      )
                reviewsStr = reviewsStr.replace(")", "");
                
                //If the string contains ',' (comma separator) , replace it with empty string(ex: (7654    )
                if(reviewsStr.contains(",")){ 
                    reviewsStr = reviewsStr.replace(",", "");
                }

                //Split the string based on '(' character and the store the divided string into an array of strings
                String[] reviewsCount = reviewsStr.split("\\(");

                //Convert the String into Integer
                reviewsCountInteger = Integer.parseInt(reviewsCount[1]);
                
                //Using put() method put the values of reviewsCountInteger and weListOfTitles.get(i).getAttribute("title") into the titles hashmap
                titlesHashMap.put(reviewsCountInteger , weListOfTitles.get(i).getAttribute("title"));
                
                //Using put() method put the values of reviewsCountInteger and weImageUrl.getAttribute("src") into the Image urls hashmap
                imageUrlsHashMap.put(reviewsCountInteger , weImageUrl.getAttribute("src"));

            }
            Thread.sleep(10000);

            System.out.println();
            //Since 5 web elements needs to be printed, iterate from 0 to 5
            for(int i = 0 ; i < 5 ; i++){

                //Since the SortedMap is used, the results are in sorted order of no.of reviews (asc to desc) 
                // Get the lastKey (highest key value(no.of reviews)) and its corresponding titlefrom titlesHashMap
                System.out.println(titlesHashMap.lastKey() + " -> " + titlesHashMap.get(titlesHashMap.lastKey()));   
                //Since the SortedMap is used, the results are in sorted order of no.of reviews (asc to desc) 
                // Get the lastKey's imageUrl from imageUrlsHashMap
                System.out.println("   -> " + imageUrlsHashMap.get(imageUrlsHashMap.lastKey()));
                System.out.println();

                //Remove the highest no.of reviews <key,value> entry from both the hashmaps so that the next Highest will the most 
                //highest key
                titlesHashMap.remove(titlesHashMap.lastKey());
                imageUrlsHashMap.remove(imageUrlsHashMap.lastKey());   
            
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

}













