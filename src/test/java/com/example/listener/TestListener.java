package com.example.listener;

import com.example.base.UiBaseModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Listener class to log current test details
 */
public class TestListener extends UiBaseModule implements ITestListener {

  private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

  @Override
  public void onTestStart(ITestResult result) {
    LOGGER.info("Executing test method : [{}] in class [{}] | Thread id : [{}]", result.getMethod().getMethodName(),
        result.getTestClass().getName(), Thread.currentThread().getId());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    LOGGER.info("Passed test method : [{}] in class [{}]", result.getMethod().getMethodName(), result.getTestClass().getName());
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    LOGGER.info("Failed test method : [{}] in class [{}]", iTestResult.getMethod().getMethodName(),
        iTestResult.getTestClass().getName());
    //takeScreenshot();
    //LOGGER.info("Auto captured screenshot and attached to allure report");
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    LOGGER.info("Skipped test method : [{}] in class [{}]", result.getMethod().getMethodName(), result.getTestClass().getName());
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }

  @Override
  public void onStart(ITestContext context) {
    LOGGER.info("Executing test flow : [{}] from class [{}]", context.getCurrentXmlTest().getName(),
        context.getCurrentXmlTest().getClass().getName());
  }

  @Override
  public void onFinish(ITestContext context) {
    LOGGER.info("Finished test flow : [{}] from class [{}]", context.getCurrentXmlTest().getName(),
        context.getCurrentXmlTest().getClass().getName());
  }

}
