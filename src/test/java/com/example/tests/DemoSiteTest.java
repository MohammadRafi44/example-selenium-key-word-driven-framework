package com.example.tests;

import com.example.base.UiBaseTest;
import com.example.utils.DataReader;
import com.example.modules.DemoSiteModule;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoSiteTest extends UiBaseTest {

  @Test(description = "Launch application Rafi")
  @Parameters(value = "tcId")
  public void launchApp(String tcId) {
    DemoSiteModule demoSiteModule = new DemoSiteModule();
    demoSiteModule.
            launchDemoSite(DataReader.getData(tcId).get("Url"));

  }

  @Test(description = "Create User")
  @Parameters(value = "tcId")
  public void createUser(String tcId) {
    DemoSiteModule demoSiteModule = new DemoSiteModule();
    demoSiteModule.
            createUser(DataReader.getData(tcId).get("Username"), DataReader.getData(tcId).get("Password"));
  }

  @Test(description = "Login")
  @Parameters(value = "tcId")
  public void login(String tcId) {
    DemoSiteModule demoSiteModule = new DemoSiteModule();
    demoSiteModule.login(DataReader.getData(tcId).get("Username"), DataReader.getData(tcId).get("Password"));
    demoSiteModule.validateLogin();
  }

}
