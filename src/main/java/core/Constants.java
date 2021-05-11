package core;

import java.io.File;
import java.nio.file.Paths;

public class Constants {

  public static final String CURRENT_DIR = System.getProperty("user.dir");
  public static final String TEST_RESOURCES_DIR = Paths.get(CURRENT_DIR, "src", "test", "resources").toString();
  public static final File TEST_RUNNER_FILE = Paths.get("src", "test", "resources", "TestRunner.xlsx").toFile();
  public static final File TEST_MODULE_FILE = Paths.get("src", "test", "resources", "Modules.xlsx").toFile();
  public static final String TEST_RUNNER_DATA_SHEET_NAME = "Smoke_Prod";
  public static final String BUSINESS_FLOW_SHEET_NAME = "Business_Flow";
  public static final String GENERAL_DATA_SHEET_NAME = "General_Data";

}
