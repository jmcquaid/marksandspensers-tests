package com.metapack.dmlabelregression.bdd.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ShoppingBagSteps {

    WebDriver driver;

    // Category page (men->casual shirts)
    String SHOPPING_PAGE_URL = "http://www.marksandspencer.com/l/men/casual-shirts";
    String ITEM_LIST_LOCATOR = "//ol[@class='grid-view']";
    String ITEM_DETAILS_LINK_LOCATOR = "//ol[@class='grid-view']//li//img";

    //Details page:
    String ITEM_DESCRIPTION_LOCATOR =  "//div[@class='information']//h1";
    String COLOR_SELECTION_LINK_LOCATOR = "//ul[@class='custom-wrap swatches']//li//input";
    String SIZE_SELECTION_LINK_LOCATOR = "//div[@class='size-table-wrapper']//table//td//label";
    String ADD_TO_BAG_LINK_LOCATOR = "//input[@value='add to bag']";
    String VIEW_BASKET_LINK_LOCATOR = "//a[@title='View basket']";

    // Basket page
    String BASKET_ITEM_DESCRIPTION_LOCATOR = "//tbody[@class='basket-list']//div[@class='product-info-wrap']//a";

    String selectedItemDescription;
    List<String> basketItemDescriptions = new ArrayList<String>();
    
    @Before
    public void setup() throws Exception {
        //start webdriver

            System.out.println("Setting the FF driver");
            driver = new FirefoxDriver();
        
    }

    @After
    public void cleanup() throws Exception {
        driver.quit();
    }

    @Given("^I have added a shirt to my bag$")
    public void i_have_added_a_shirt_to_my_bag() throws Throwable {

        //Go to page shirt page
        driver.get(SHOPPING_PAGE_URL);
        Thread.sleep(1000);


        //Click on item for details
        driver.findElement(By.xpath(ITEM_DETAILS_LINK_LOCATOR)).click();
        Thread.sleep(10000);

        //Get description of shirt for testing
        selectedItemDescription = driver.findElement(By.xpath(ITEM_DESCRIPTION_LOCATOR)).getText();

        // Select a size
        driver.findElement(By.xpath(COLOR_SELECTION_LINK_LOCATOR)).click();
        Thread.sleep(1000);
        
        // Select a colour
        driver.findElement(By.xpath(SIZE_SELECTION_LINK_LOCATOR)).click();
        Thread.sleep(1000);

        // Click add to basket
        driver.findElement(By.xpath(ADD_TO_BAG_LINK_LOCATOR)).click();
        Thread.sleep(5000);
    }

    @When("^I view the contents of my bag$")
    public void i_view_the_contents_of_my_bag() throws Throwable {    
        //Go to thebag/basket page
        driver.findElement(By.xpath(VIEW_BASKET_LINK_LOCATOR)).click();
        
        //Get all item descriptions
        List<WebElement> allItemElements = driver.findElements(By.xpath(BASKET_ITEM_DESCRIPTION_LOCATOR));
        System.out.println("Items in basket: ");
        for( WebElement ele : allItemElements){
            String itemDescription = ele.getText();
            System.out.println(itemDescription);
            basketItemDescriptions.add(itemDescription);
        }

    }

    @Then("^I can see the contents of the bag include a shirt$")
    public void i_can_see_the_contents_of_the_bag_include_a_shirt() throws Throwable {
        if (!basketItemDescriptions.contains(selectedItemDescription)){
            throw new Exception("Could not find item: " + selectedItemDescription + 
                                " in bag!  (Contents of bag: " +  basketItemDescriptions + ")");
        }
    }
}
