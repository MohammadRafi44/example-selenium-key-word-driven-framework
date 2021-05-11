package com.example.tests;

import com.example.base.ApiBaseTest;
import com.example.modules.OauthExampleModule;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class OauthExampleTest extends ApiBaseTest {

  @Test(description = "Get oAuth token")
  public void getOAuthToken(ITestContext context) {
    OauthExampleModule oauthExampleModule = new OauthExampleModule(context);
    oauthExampleModule.generateToken();
  }

  @Test(description = "Call api that is secured using oauth token")
  public void callSecuredApi(ITestContext context) {
    OauthExampleModule oauthExampleModule = new OauthExampleModule(context);
    oauthExampleModule.callSecuredApi();
  }

}
