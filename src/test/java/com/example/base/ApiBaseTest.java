package com.example.base;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class ApiBaseTest {

  @BeforeMethod()
  @Parameters(value = "tcId")
  public void beforeMethod(String tcId, ITestContext context){
    context.setAttribute("tcId", tcId);
  }


}
