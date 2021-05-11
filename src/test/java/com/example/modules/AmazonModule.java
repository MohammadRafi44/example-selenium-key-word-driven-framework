package com.example.modules;

import static com.example.objects.AmazonObjects.GO_BUTTON;
import static com.example.objects.AmazonObjects.SEARCH_BOX;

import com.example.base.UiBaseModule;
import io.qameta.allure.Step;

public class AmazonModule extends UiBaseModule {

  @Step
  public void launchAmazon(String url) {
    openUrl(url);
  }

  @Step(value = "Search for given product in amazon")
  public void search(String searchString) {
    highlightElement(SEARCH_BOX);
    enterText(SEARCH_BOX, searchString);
    highlightElement(GO_BUTTON);
    click(GO_BUTTON);
    takeScreenshot();
    sleep(1);
  }

}
