package com.example.tests;

import com.example.modules.CalculatorRestApiModule;
import com.example.base.ApiBaseTest;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CalculatorRestApiTest extends ApiBaseTest {

  @Test(description = "Perform arithmetic operations")
  public void performCalculationsRest(ITestContext context) {
    CalculatorRestApiModule calculatorRestApiModule = new CalculatorRestApiModule(context);
    calculatorRestApiModule.
            getArithmeticOperations();
  }

  @Test(description = "Retrieve calculations by logId")
  public void retrieveByIdRest(ITestContext context) {
    CalculatorRestApiModule calculatorRestApiModule = new CalculatorRestApiModule(context);
    calculatorRestApiModule.getCalculationById();
  }
}
