package com.example.modules;

import com.example.base.ApiHandler;
import com.example.objects.TestCrewObjects;
import com.example.utils.DataReader;
import com.example.utils.Helper;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;
import org.testng.ITestContext;
import static com.example.objects.TestCrewObjects.*;

public class CalculatorRestApiModule {

  private final ITestContext context;
  private final String tcId;

  public CalculatorRestApiModule(ITestContext context) {
    this.context = context;
    tcId = (String) context.getAttribute("tcId");
  }

  @Step(value = "Perform arithmetic operations")
  public void getArithmeticOperations() {
    // Build request spec
    ApiHandler apiHandler = new ApiHandler();
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    requestSpecBuilder.addHeader("Content-Type", ContentType.JSON.toString());
//    requestSpecBuilder.addParam("firstNumber", RandomUtils.nextInt(10, 50));
//    requestSpecBuilder.addParam("secondNumber", RandomUtils.nextInt(60, 90));

    requestSpecBuilder.addParam("firstNumber", "30");
    requestSpecBuilder.addParam("secondNumber", "50");


    // Call api
    Response response = apiHandler.getRequest(DataReader.getData(tcId).get("Url"), requestSpecBuilder);

    // Validation
    Helper.validateResponseIsNotNull(response);
    Helper.validateApiResponseStatusCode(response, 200);


    String AdditionResult = response.path("answers.addition");
    Helper.ValidateResonseBodyForCondition(AdditionResult, REST_ExpectedResultString);

//    String extractedLogId = response.path("logId");
//
//    // Store logId to context
//    context.setAttribute("extractedLogId", extractedLogId);
  }

  @Step(value = "Get arithmetic operations by logId")
  public void getCalculationById() {
    // Fetch logId from context
    String logId = (String) context.getAttribute("extractedLogId");

    // Build request spec
    ApiHandler apiHandler = new ApiHandler();
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    requestSpecBuilder.addHeader("Content-Type", ContentType.JSON.toString());
    requestSpecBuilder.addPathParam("logId", logId);

    // Call api
    Response response = apiHandler.getRequest(DataReader.getData(tcId).get("Url2") + "/{logId}", requestSpecBuilder);

    // Validation
    Helper.validateResponseIsNotNull(response);
    Helper.validateApiResponseStatusCode(response, 200);
  }
}
