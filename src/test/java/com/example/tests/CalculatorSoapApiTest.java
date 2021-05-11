package com.example.tests;

import com.example.base.ApiBaseTest;
import com.example.modules.CalculatorRestApiModule;
import com.example.modules.CalculatorSoapApiModule;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CalculatorSoapApiTest extends ApiBaseTest {

  @Test(description = "Perform arithmetic operations")
  public void performCalculationsSoap(ITestContext context) {

    CalculatorSoapApiModule calculatorSoapApiModule = new CalculatorSoapApiModule(context);

    calculatorSoapApiModule.
            performArithmeticOperations();
  }

/*  @Test(description = "Retrieve calculations by logId")
  public void retrieveByIdRest(ITestContext context) {
    CalculatorRestApiModule calculatorRestApiModule = new CalculatorRestApiModule(context);
    calculatorRestApiModule.getCalculationById();
  }*/
}
