package com.example.utils;

import com.example.base.DriverManager;
import core.ConfigManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;

public class Helper {

  private static final Logger LOGGER = LogManager.getLogger(Helper.class);

  private Helper() {
  }

  public static void sleep(int sleepInSeconds) {
    try {
      LOGGER.info("Waiting for seconds: " + sleepInSeconds);
      Thread.sleep(sleepInSeconds * 1000L);
    } catch (Exception e) {
      //
    }
  }

  @Attachment(value = "Screenshot", type = "image/png")
  public static byte[] takeScreenshot() {
    return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
  }

  @Attachment(value = "{0}", type = "text/plain")
  public static String log(String attachName, String message) {
    LOGGER.info(message);
    Reporter.log(message);
    return message;
  }

  @Attachment(value = "Log", type = "text/plain")
  public static String log(String message) {
    LOGGER.info(message);
    Reporter.log(message);
    return message;
  }

  @Step(value = "Validate response is not null")
  public static void validateResponseIsNotNull(Response response) {
    Assert.assertNotNull(response);
  }

  @Step(value = "Validate response status code")
  public static void validateApiResponseStatusCode(Response response, int expectedCode) {
    Assert.assertEquals(response.getStatusCode(), expectedCode);
  }

  @Step(value= "Validate Calculator response for Addition")
    public static void ValidateResonseBodyForCondition(String ActualResultString, String ExpectedResultString){

      assert (ActualResultString.equalsIgnoreCase(ExpectedResultString));
  }

  public static String fileToString(String path) {
    try {
      return IOUtils.toString(Helper.class.getResourceAsStream(path), StandardCharsets.UTF_8);
    } catch (Exception e) {
      LOGGER.error(e);
      throw new RuntimeException(e);
    }
  }

  public static String getStringFromContext(ITestContext context, String key) {
    return (String) context.getAttribute("key");
  }

  public static Integer getIntFromContext(ITestContext context, String key) {
    return (Integer) context.getAttribute("key");
  }

  public static String decode(String stringToDecode) {
    byte[] decode = Base64.getDecoder().decode(stringToDecode);
    return new String(decode);
  }

  public static String encode(String stringToEncode) {
    return Base64.getEncoder().encodeToString(stringToEncode.getBytes(StandardCharsets.UTF_8));
  }

}
