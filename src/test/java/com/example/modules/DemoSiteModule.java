package com.example.modules;

import static com.example.objects.DemoSiteObjects.ADD_USER;
import static com.example.objects.DemoSiteObjects.LOGIN_BUTTON;
import static com.example.objects.DemoSiteObjects.LOGIN_LINK;
import static com.example.objects.DemoSiteObjects.MESSAGE;
import static com.example.objects.DemoSiteObjects.PASSWORD;
import static com.example.objects.DemoSiteObjects.SAVE_BUTTON;
import static com.example.objects.DemoSiteObjects.USER_NAME;

import com.example.base.UiBaseModule;
import com.example.utils.Helper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DemoSiteModule extends UiBaseModule {

  @Step(value = "Launch Demo Site")
  public void launchDemoSite(String url) {
    openUrl(url);
    takeScreenshot();
  }

  @Step(value = "Create a new user")
  public void createUser(String userName, String password) {
    highlightElement(ADD_USER);
    click(ADD_USER);
    highlightElement(USER_NAME);
    enterText(USER_NAME, userName);
    highlightElement(PASSWORD);
    enterText(PASSWORD, password);
    takeScreenshot();
    highlightElement(SAVE_BUTTON);
    click(SAVE_BUTTON);
    takeScreenshot();
  }

  @Step(value = "Login")
  public void login(String userName, String password) {
    highlightElement(LOGIN_LINK);
    click(LOGIN_LINK);
    highlightElement(USER_NAME);
    enterText(USER_NAME, userName);
    highlightElement(PASSWORD);
    enterText(PASSWORD, password);
    takeScreenshot();
    highlightElement(LOGIN_BUTTON);
    click(LOGIN_BUTTON);
    takeScreenshot();
  }

  @Step(value = "Validate if login was successful")
  public void validateLogin() {
    String message = getText(MESSAGE);
    takeScreenshot();
    Helper.log(message);
    Assert.assertTrue(message.contains("Successful"), "Login failed : " + message);
  }
}
