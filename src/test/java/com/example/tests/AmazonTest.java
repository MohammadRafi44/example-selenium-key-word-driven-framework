package com.example.tests;

import com.example.base.UiBaseTest;
import com.example.modules.AmazonModule;
import com.example.utils.DataReader;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AmazonTest extends UiBaseTest {

  @Test(description = "Amazon search")
  @Parameters(value = "tcId")
  public void amazonSearch(String tcId) {
    AmazonModule amazonModule = new AmazonModule();
    amazonModule.launchAmazon(DataReader.getData(tcId).get("Url"));
    amazonModule.search(DataReader.getData(tcId).get("KeywordSearch"));
  }

}
