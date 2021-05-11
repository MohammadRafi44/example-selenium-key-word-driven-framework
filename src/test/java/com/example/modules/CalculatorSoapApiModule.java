package com.example.modules;

import com.example.base.ApiHandler;
import com.example.utils.DataReader;
import com.example.utils.Helper;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;

import static com.example.objects.TestCrewObjects.*;

public class CalculatorSoapApiModule {

  private static final Logger LOGGER = LogManager.getLogger(CalculatorSoapApiModule.class);

  private final ITestContext context;
  private final String tcId;

  public CalculatorSoapApiModule(ITestContext context) {
    this.context = context;
    tcId = (String) context.getAttribute("tcId");
  }

  @Step(value = "Perform arithmetic operations")
  public void performArithmeticOperations() {
    // Read file as string
    String body = Helper.fileToString("/soap-requests/calculator.xml")
        .replace("$firstNumber$", "100")
        .replace("$secondNumber$", "10")
        .replace("$scenarioType$", "success");

    // Build request spec
    ApiHandler apiHandler = new ApiHandler();
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    requestSpecBuilder.addHeader("Content-Type", "text/xml");
    requestSpecBuilder.setBody(body);

    // Call api
    Response response = apiHandler.soapRequest(DataReader.getData(tcId).get("Url"), requestSpecBuilder);

    // Validation
    Helper.validateResponseIsNotNull(response);
    Helper.validateApiResponseStatusCode(response, 200);

   String SOAP_ActualResult_Sting= response.xmlPath().getString("Envelope.Body.CalculatorResponse.addition");
    Helper.ValidateResonseBodyForCondition(SOAP_ActualResult_Sting,SOAP_ExpectedResultString);

   /* // Extract log id from soap response
    String extractedLogId = response.xmlPath().getString("Envelope.Body.CalculatorResponse.logId");
    LOGGER.info("Log id extracted from response : [{}]", extractedLogId);

    // Store log id into context
    context.setAttribute("extractedLogId", extractedLogId);*/
  }

}
