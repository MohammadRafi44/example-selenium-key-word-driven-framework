package com.example.base;

import com.example.listener.SeleniumListener;
import core.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

public class DriverManager {

  private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
  private static final ConfigManager configManager = ConfigManager.getInstance();

  private DriverManager() {
  }

  public static WebDriver getDriver() {
    return webDriverThreadLocal.get();
  }

  private static void setDriver(WebDriver driver) {
    webDriverThreadLocal.set(driver);
  }

  public static void quitDriver() {
    WebDriver driver = webDriverThreadLocal.get();
    if (driver != null) {
      driver.quit();
    }
  }

  public static void setBrowser(Browser browser) {
    EventFiringWebDriver eventFiringWebDriver = null;
    if (browser.toString().equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver(new ChromeOptions());
      eventFiringWebDriver = new EventFiringWebDriver(driver);
      eventFiringWebDriver.register(new SeleniumListener());
    } else if (browser.toString().equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      FirefoxOptions firefoxOptions = new FirefoxOptions();
      Assert.assertNotNull(configManager.getConfigProperty("firefox.browser.path"),
              "Please set firefox browser installation path in config.properties");
      firefoxOptions.setBinary(configManager.getConfigProperty("firefox.browser.path"));
      System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
      System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
      WebDriver driver = new FirefoxDriver(firefoxOptions);
      eventFiringWebDriver = new EventFiringWebDriver(driver);
      eventFiringWebDriver.register(new SeleniumListener());
    }
    assert eventFiringWebDriver != null;
    eventFiringWebDriver.manage().timeouts()
            .implicitlyWait(Integer.parseInt(configManager.getConfigProperty("implicit.wait.time")), TimeUnit.SECONDS);
    eventFiringWebDriver.manage().timeouts()
            .pageLoadTimeout(Integer.parseInt(configManager.getConfigProperty("page.load.wait.time")), TimeUnit.SECONDS);
    eventFiringWebDriver.manage().timeouts()
            .setScriptTimeout(Integer.parseInt(configManager.getConfigProperty("page.load.wait.time")), TimeUnit.SECONDS);
    eventFiringWebDriver.manage().window().maximize();
    DriverManager.setDriver(eventFiringWebDriver);
  }
}
