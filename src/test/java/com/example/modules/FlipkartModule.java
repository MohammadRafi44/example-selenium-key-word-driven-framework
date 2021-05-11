package com.example.modules;


import com.example.base.DriverManager;
import com.example.base.UiBaseModule;
import com.example.utils.DataReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static com.example.objects.FlipkartObjects.FLIPKART_GO_BUTTON;
import static com.example.objects.FlipkartObjects.*;

public class FlipkartModule extends UiBaseModule {

    @Step(value = "Launching Flipkart")
    public void launchFlipkart(String url) {
        openUrl(url);
    }

    @Step(value = "Search for given product in flipkart")
    public void search(String tcId)  {
        sleep(4);
//        waitUntilClickable(FLIPKART_SEARCH_BOX);
        enterText(FLIPKART_SEARCH_BOX, DataReader.getData(tcId).get("KeywordSearch"));
        sleep(2);
        click(FLIPKART_HOMEPAGE_ADHOC_CLICK);
        sleep(2);
        sendEnterFromKeyboard(FLIPKART_SEARCH_BOX);
        sleep(5);
        click(FLIPKART_SEARCHED_RESULT_ITEM);
        sleep(2);

        //        WebDriver driver = DriverManager.getDriver();

//        Actions ac = new Actions(driver);
//        ac.sendKeys(new CharSequence[]{Keys.ENTER}).build().perform();

//        Robot robot = null;
//        try {
//            robot = new Robot();
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }
//        autoSuggestDropdown(FLIPKART_SEARCH_BOX, driver, "Samsung s20 ultra");
       /* sendEscapeFromKeyboard(FLIPKART_SEARCH_BOX);
        sendEnterFromKeyboard(FLIPKART_SEARCH_BOX);
//        sleep(2);
//        click(GO_BUTTON);
//        sleep(5);
        //Capture all the auto suggested elements
        List<WebElement> allElements=driver.findElements(FLIPKART_SEARCH_BOX);
        System.out.println("Size of Auto Suggestions: "+allElements.size());
//Select the item contains "w3schools"
        for(WebElement ele:allElements){
            if(ele.getText().contains("samsung s20 ultra pro")){
                System.out.println(ele);
                ele.click();
                break;
            }
            else{
                System.out.println("item not found "+ele.getText());
            }
        }
        sleep(5);*/
    }
    @Step(value = "if login is asked, enter login info")
    public void flipkartLoginPopUpCheck(String tcID) {

        if (checkIfWebElementExists(FLIPKART_LOGIN_POPUP)){
            enterText(FLIPKART_USERNAME_TEXT,DataReader.getData(tcID).get("FlipkartUsername"));
            enterText(FLIPKART_PASSWORD_TEXT,DataReader.getData(tcID).get("FlipkartPassword"));
            click(FLIPKART_LOGIN_BUTTON);
        }
        else {
            return;
        }

    }
    @Step(value = "Searched item -adding to wishlist")
    public void flipkartSearchedItemAddWishlist(){
        getRightChromeWindow(FLIPKART_RIGHT_CHROME_TITLE_TEXT);
        sleep(3);




    }
/*    @Step(value = "if login is asked, enter login info")
    public void flipkartLoginPopUpCheck(String... searchString) {
        enterText(FLIPKART_USERNAME_TEXT, DataReader.getData(tcId).get("UserName"));

        if (checkIfWebElementExists(FLIPKART_LOGIN_POPUP)){
            enterText(FLIPKART_USERNAME_TEXT, DataReader.getData(tcId).get("UserName"));
            enterText(FLIPKART_USERNAME_TEXT, DataReader.getData(tcId).get("Password"));
        }

    }*/
}
