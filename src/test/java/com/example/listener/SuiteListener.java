package com.example.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

  private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

  @Override
  public void onStart(ISuite suite) {
    LOGGER.info("Starting test suite [{}]", suite.getName());
  }

  @Override
  public void onFinish(ISuite suite) {
    LOGGER.info("Finished test suite [{}]", suite.getName());
  }
}
