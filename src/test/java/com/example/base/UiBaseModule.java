package com.example.base;

import com.example.utils.Helper;
import core.ConfigManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.objects.FlipkartObjects.FLIPKART_SEARCHED_RESULT_ITEM_ADDING_TO_WISHLIST;
import static com.example.objects.TestCrewObjects.TESTCREW_ABOUTUS_EMAIL_TEXT;
import static com.example.objects.TestCrewObjects.TESTCREW_ABOUTUS_EMAIL_TEXT_DATA;

public abstract class UiBaseModule {

  private static final Logger LOGGER = LogManager.getLogger(UiBaseModule.class);
  private final WebDriver driver = DriverManager.getDriver();
  private final ConfigManager configManager = ConfigManager.getInstance();

  @Attachment(value = "Screenshot", type = "image/png")
  public static byte[] takeScreenshot() {
    return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
  }

  @Step
  public void sleep(int sleepInSeconds) {
    try {
      LOGGER.info("Waiting for {} Seconds", sleepInSeconds);
      Thread.sleep(sleepInSeconds * 1000L);
    } catch (Exception e) {
      //
    }
  }

  @Step
  public void openUrl(String url) {
    driver.get(url);
  }

  @Step
  public WebElement find(By by) {
    WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(configManager.getConfigProperty("explicit.wait.time")));
    return wait.until(ExpectedConditions.elementToBeClickable(by));
  }

  @Step
  public WebElement find(WebElement webElement) {
    WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(configManager.getConfigProperty("explicit.wait.time")));
    return wait.until(ExpectedConditions.elementToBeClickable(webElement));
  }

  @Step()
  public void click(By by) {
    find(by).click();
  }

  @Step
  public void click(WebElement webElement) {
    find(webElement).click();
  }

  @Step
  public void waitUntilClickable(By by) {
    WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(configManager.getConfigProperty("explicit.wait.time")));
    wait.until(ExpectedConditions.elementToBeClickable(by));
  }

  @Step
  public void waitUntilClickable(WebElement webElement) {
    WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(configManager.getConfigProperty("explicit.wait.time")));
    wait.until(ExpectedConditions.elementToBeClickable(webElement));
  }

  @Step
  public boolean checkIfWebElementExists(By by) {
    return checkIfWebElementExists(driver.findElement(by));
  }

  @Step
  public boolean checkIfWebElementExists(WebElement webelement) {
    try {
      if (webelement.isDisplayed()) {
        return true;
      }
    } catch (Exception e) {
      //
    }
    return false;
  }

  @Step
  public boolean waitForElementAtIntervals(By by, int interval, int maxWait) {
    boolean elementExists = false;
    try {
      int initialWait = 0;
      while (initialWait <= maxWait) {
        if (checkIfWebElementExists(by)) {
          elementExists = true;
          LOGGER.info("Found element [{}] after waiting for [{}]", by.toString(), initialWait);
          initialWait = maxWait + 1;
        } else {
          sleep(1);
          initialWait = initialWait + interval;
        }
      }
      if (!elementExists) {
        LOGGER.info("WebElement [{}] not found", by.toString());
      }
    } catch (Exception e) {
      //
    }
    return elementExists;
  }

  @Step
  public boolean waitForElementAtIntervalsAndClick(By by, int interval, int maxTime) {
    boolean flag = waitForElementAtIntervals(by, interval, maxTime);
    if (flag) {
      click(by);
    }
    return flag;
  }

  @Step
  public void clickByJS(By by) {
    clickByJS(driver.findElement(by));
  }

  @Step
  public void clickByJS(WebElement webElement) {
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", webElement);
  }

  @Step
  public void enterText(By by, String... value) {
    find(by).sendKeys(value);
  }

  @Step
  public void sendEnterFromKeyboard(By by){
    find(by).sendKeys(Keys.ENTER);
  }

  @Step
  public void sendEscapeFromKeyboard(By by){
    find(by).sendKeys(Keys.ESCAPE);
  }

  @Step
  public void ElementTextContains(By by, String TextToBeVerified) {
    String actualString = driver.findElement(by).getText();
    assert (actualString.contains(TextToBeVerified));

  }

/*
  public void autoSuggestDropdown (By by, WebDriver driver,String flipkartSearchText) {
//Capture all the auto suggested elements
    List<WebElement> allElements=driver.findElements(by);
    System.out.println("Size of Auto Suggestions: "+allElements.size());
//Select the item contains "w3schools"
    for(WebElement ele:allElements){
      if(ele.getText().contains(flipkartSearchText)){
        ele.click();
        break;
      }
      else{
        System.out.println("item not found "+ele.getText());
      }
    }
  }
*/

  public void getRightChromeWindow (String newChromeWindowTitleText){
    Set<String> windowIDs=driver.getWindowHandles();//IDs of multiple window IDs
    List<String> windowIDsList = new ArrayList(windowIDs);
    //Using For Each Loop
/*    for(String winIDs:windowIDsList){
      //System.out.println(winIDs);
      System.out.println("Title: " +driver.switchTo().window(winIDs).getTitle());
    }
    //How to Close window by choice*/
    for(String winIDs:windowIDsList){
      String title = driver.switchTo().window(winIDs).getTitle();
      if(title.equals(newChromeWindowTitleText))
      {

        driver.switchTo().window(winIDs);
        click(FLIPKART_SEARCHED_RESULT_ITEM_ADDING_TO_WISHLIST);

      }

  }
  }
  /*@Step
  public void getWhatsAppChromeWindow (String newWhatsAppWindowTitleText){
    Set<String> windowIDs=driver.getWindowHandles();//IDs of multiple window IDs
    List<String> windowIDsList = new ArrayList(windowIDs);
    //Using For Each Loop
*//*    for(String winIDs:windowIDsList){
      //System.out.println(winIDs);
      System.out.println("Title: " +driver.switchTo().window(winIDs).getTitle());
    }
    //How to Close window by choice*//*
    for(String winIDs:windowIDsList){
      String title = driver.switchTo().window(winIDs).getTitle();
      if(title.equals(newWhatsAppWindowTitleText))
      {

        driver.switchTo().window(winIDs);
        sleep(3);
        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();
        sleep(3);

      }

    }
  }*/

   @Step
   public void enterText(WebElement webElement, String value) {
    webElement.click();
    webElement.sendKeys(value);
  }

  @Step
  public String getText(By by) {
    return find(by).getText();
  }

  @Step
  public String getText(WebElement webElement) {
    return webElement.getText();
  }

  @Step
  public void linkText(By by) {
    find(by).click();
  }

  @Step
  public Object executeJs(String javaScript) {
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    return executor.executeScript(javaScript);
  }

  public void highlightElement(By by) {
    try {
      WebDriver driver = DriverManager.getDriver();
      JavascriptExecutor js = (JavascriptExecutor) driver;
      WebElement elem = driver.findElement(by);
      js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;')", new Object[]{elem});

//      color: yellow; border: 2px solid yellow;
    } catch (Exception var) {
      LOGGER.error("highlightElement method failed for error: " + var.getMessage(), var);
    }

  }

  public boolean ScrollToElement(By by) {
    boolean res = false;

    try {
      WebDriver driver = DriverManager.getDriver();
      JavascriptExecutor js = (JavascriptExecutor) driver;
      WebElement elem = driver.findElement(by);
      js.executeScript("arguments[0].scrollIntoView({block: \"center\"});", new Object[]{elem});
      res = true;
      Thread.sleep(1500L);
    } catch (Exception var2) {
      LOGGER.error("Scrolling to Element method failed for error: " + var2.getStackTrace());
    }
    return res;
  }

}
