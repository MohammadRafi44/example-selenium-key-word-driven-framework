package com.example.base;

import com.example.utils.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public abstract class UiBaseTest {

  private static final Logger LOGGER = LogManager.getLogger(UiBaseTest.class);

  @BeforeSuite(description = "Read test data", alwaysRun = true)
  public void readData() {
    DataReader.getDataMap();
  }

  @BeforeTest(description = "Launch Browser", alwaysRun = true)
  @Parameters(value = "browser")
  public void launchBrowser(String browser) {
    LOGGER.info("Browser : {}", browser);
    DriverManager.setBrowser(Browser.valueOf(browser));
  }

  @BeforeMethod()
  @Parameters(value = "tcId")
  public void beforeMethod(String tcId, ITestContext context){
    context.setAttribute("tcId", tcId);
  }

  @AfterTest(description = "Close Browser", alwaysRun = true)
  public void closeBrowser() {
    DriverManager.quitDriver();
  }

}
