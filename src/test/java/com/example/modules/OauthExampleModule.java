package com.example.modules;

import com.example.base.ApiHandler;
import com.example.utils.DataReader;
import com.example.utils.Helper;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;

public class OauthExampleModule {

  private final ITestContext context;
  private final String tcId;

  public OauthExampleModule(ITestContext context) {
    this.context = context;
    tcId = (String) context.getAttribute("tcId");
  }

  @Step(value = "Get oAuth token")
  public void generateToken() {
    // Build request spec
    ApiHandler apiHandler = new ApiHandler();
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    requestSpecBuilder.addHeader("Content-Type", ContentType.URLENC.toString());
    requestSpecBuilder.addHeader("Authorization", "Basic " + DataReader.getData(tcId).get("OAuthServerSecret"));

    // Call api
    Response response = apiHandler.postRequest(DataReader.getData(tcId).get("Url"), requestSpecBuilder);

    // Validation
    Helper.validateResponseIsNotNull(response);
    Helper.validateApiResponseStatusCode(response, 200);

    // Extract token
    String access_token = response.path("access_token");

    // Store token into context
    context.setAttribute("access_token", access_token);
  }

  @Step(value = "Call secured api")
  public void callSecuredApi() {
    // Fetch token from context
    String token = (String) context.getAttribute("access_token");

    // Build request spec
    ApiHandler apiHandler = new ApiHandler();
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    requestSpecBuilder.addHeader("Content-Type", ContentType.JSON.toString());
    requestSpecBuilder.addHeader("Authorization", "Bearer " + token);

    // Call api
    Response response = apiHandler.getRequest(DataReader.getData(tcId).get("Url2"), requestSpecBuilder);

    // Validation
    Helper.validateResponseIsNotNull(response);
    Helper.validateApiResponseStatusCode(response, 200);

    // Extract message from response
    String message = response.path("message");

    // Business validation
    Assert.assertEquals("Congrats, on unlocking the secret!", message);
  }

}
