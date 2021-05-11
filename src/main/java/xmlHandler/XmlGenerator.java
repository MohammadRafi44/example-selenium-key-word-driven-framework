package xmlHandler;

import core.ConfigManager;
import core.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

public class XmlGenerator {

  private static final Logger LOGGER = LogManager.getLogger(XmlGenerator.class);
  private static final ConfigManager configManager = ConfigManager.getInstance();

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    List<DataModel> finalDataModelsList = new LinkedList<>();
    Map<String, List<String>> testRunnerMap = getTestRunnerDataMap();
    Map<String, List<String>> businessFlowDataMap = getBusinessFlowData();
    Map<String, List<String>> generalDataMap = getGeneralData();
    testRunnerMap.forEach((tcId, testRunnerList) -> {
      DataModel dataModel = new DataModel();
      dataModel.setTcId(tcId);
      dataModel.setTestRunnerList(testRunnerList);
      dataModel.setBusinessFlowList(businessFlowDataMap.get(tcId));
      dataModel.setGeneralDataList(generalDataMap.get(tcId));
      finalDataModelsList.add(dataModel);
    });
    LOGGER.debug(finalDataModelsList);
    XmlSuite xmlSuite = new XmlSuite();
    xmlSuite.setName(configManager.getConfigProperty("suite.name"));
    xmlSuite.setVerbose(2);
    xmlSuite.setParallel(ParallelMode.TESTS);
    xmlSuite.setThreadCount(Integer.parseInt(configManager.getConfigProperty("thread.count")));
    xmlSuite.addListener("com.example.listener.TestListener");
    xmlSuite.addListener("com.example.listener.SuiteListener");
    List<XmlTest> xmlTestList = new LinkedList<>();
    for (DataModel model : finalDataModelsList) {
      XmlTest xmlTest = new XmlTest(xmlSuite);
      xmlTest.addParameter("browser", model.getTestRunnerList().get(7));
      String className = "com.example.tests." + model.getBusinessFlowList().get(1);
      XmlClass xmlClass = new XmlClass(Class.forName(className));
      String tcId = model.getTcId();
      LOGGER.debug("tc id : " + tcId);
      xmlTest.setPreserveOrder(true);
      xmlTest.setThreadCount(1);
      xmlTest.setName(model.getTestRunnerList().get(2));
      List<XmlInclude> includedMethods = new ArrayList<>();
      for (int j = 2; j < model.getBusinessFlowList().size(); j++) {
        String methodName = model.getBusinessFlowList().get(j);
        XmlInclude xmlInclude = new XmlInclude(methodName);
        xmlInclude.addParameter("tcId", tcId);
        includedMethods.add(xmlInclude);
      }
      xmlClass.setIncludedMethods(includedMethods);
      xmlTest.setXmlClasses(Collections.singletonList(xmlClass));
      xmlTestList.add(xmlTest);
    }
    xmlSuite.setTests(xmlTestList);
    createXmlFile(xmlSuite);
  }

  public static void createXmlFile(XmlSuite mSuite) {
    try {
      FileWriter writer;
      writer = new FileWriter("target/testng.xml");
      LOGGER.debug(mSuite.toXml());
      writer.write(mSuite.toXml());
      writer.flush();
      writer.close();
      LOGGER.info("Successfully generated testng xml");
      LOGGER.info(new File("target/testng.xml").getAbsolutePath());
    } catch (Exception e) {
      LOGGER.error(e);
    }
  }

  public static Map<String, List<String>> getTestRunnerDataMap() throws IOException {
    Map<String, List<String>> testRunnerDataMap = new LinkedHashMap<>();
    FileInputStream fileInputStream = new FileInputStream(Constants.TEST_RUNNER_FILE);
    Workbook workbook = new XSSFWorkbook(fileInputStream);
    Sheet sheet = workbook.getSheet(Constants.TEST_RUNNER_DATA_SHEET_NAME);
    int rowCount = sheet.getPhysicalNumberOfRows();
    for (int i = 1; i < rowCount; i++) {
      Row row = sheet.getRow(i);
      if (row.getCell(3).getStringCellValue().equalsIgnoreCase("yes")) {
        String tcId = row.getCell(1).getStringCellValue();
        List<String> testRunnerList = new LinkedList<>();
        for (int j = 0; j < row.getLastCellNum(); j++) {
          if (row.getCell(j).getCellType().equals(CellType.STRING)) {
            LOGGER.debug(row.getCell(j).getStringCellValue());
            testRunnerList.add(row.getCell(j).getStringCellValue());
          } else if (row.getCell(j).getCellType().equals(CellType.NUMERIC)) {
            LOGGER.debug(row.getCell(j).getNumericCellValue());
            testRunnerList.add(String.valueOf(row.getCell(j).getNumericCellValue()));
          }
        }
        testRunnerDataMap.put(tcId, testRunnerList);
      }
    }
    return testRunnerDataMap;
  }

  public static Map<String, List<String>> getBusinessFlowData() throws IOException {
    Map<String, List<String>> businessFlowMap = new LinkedHashMap<>();
    FileInputStream fileInputStream = new FileInputStream(Constants.TEST_MODULE_FILE);
    Workbook workbook = new XSSFWorkbook(fileInputStream);
    Sheet sheet = workbook.getSheet(Constants.BUSINESS_FLOW_SHEET_NAME);
    int rowCount = sheet.getPhysicalNumberOfRows();
    for (int i = 1; i < rowCount; i++) {
      Row row = sheet.getRow(i);
      String tcId = row.getCell(0).getStringCellValue();
      List<String> businessFlowList = new LinkedList<>();
      for (int j = 0; j < row.getLastCellNum(); j++) {
        if (row.getCell(j).getCellType().equals(CellType.STRING)) {
          LOGGER.debug(row.getCell(j).getStringCellValue());
          businessFlowList.add(row.getCell(j).getStringCellValue());
        } else if (row.getCell(j).getCellType().equals(CellType.NUMERIC)) {
          LOGGER.debug(row.getCell(j).getNumericCellValue());
          businessFlowList.add(String.valueOf(row.getCell(j).getNumericCellValue()));
        }
      }
      businessFlowMap.put(tcId, businessFlowList);
    }
    return businessFlowMap;
  }

  public static Map<String, List<String>> getGeneralData() throws IOException {
    Map<String, List<String>> generalDataMap = new LinkedHashMap<>();
    FileInputStream fileInputStream = new FileInputStream(Constants.TEST_MODULE_FILE);
    Workbook workbook = new XSSFWorkbook(fileInputStream);
    Sheet sheet = workbook.getSheet(Constants.GENERAL_DATA_SHEET_NAME);
    int rowCount = sheet.getPhysicalNumberOfRows();
    for (int i = 1; i < rowCount; i++) {
      Row row = sheet.getRow(i);
      String tcId = row.getCell(0).getStringCellValue();
      List<String> generalDataList = new LinkedList<>();
      for (int j = 0; j < row.getLastCellNum(); j++) {
        if (row.getCell(j).getCellType().equals(CellType.STRING)) {
          LOGGER.debug(row.getCell(j).getStringCellValue());
          generalDataList.add(row.getCell(j).getStringCellValue());
        } else if (row.getCell(j).getCellType().equals(CellType.NUMERIC)) {
          LOGGER.debug(row.getCell(j).getNumericCellValue());
          generalDataList.add(String.valueOf(row.getCell(j).getNumericCellValue()));
        }
      }
      generalDataMap.put(tcId, generalDataList);
    }
    return generalDataMap;
  }
}
