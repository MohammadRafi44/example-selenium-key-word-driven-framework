package com.example.utils;

import core.Constants;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

  private static final Logger LOGGER = LogManager.getLogger(DataReader.class);
  private static final Map<String, Map<String, String>> testDataMap = new HashMap<>();

  private DataReader() {
  }

  public static void main(String[] args) {
    getDataMap();
  }

  public static Map<String, String> getData(String tcId) {
    return getDataMap().get(tcId);
  }

  public static Map<String, Map<String, String>> getDataMap() {
    if (!(testDataMap.size() > 1)) {
      Workbook workbook = null;
      try {
        FileInputStream fileInputStream = new FileInputStream(Constants.TEST_MODULE_FILE);
        workbook = new XSSFWorkbook(fileInputStream);
      } catch (IOException e) {
        e.printStackTrace();
      }
      Sheet sheet = workbook.getSheet(Constants.GENERAL_DATA_SHEET_NAME);
      int rowCount = sheet.getPhysicalNumberOfRows();
      for (int i = 1; i < rowCount; i++) {
        Map<String, String> innerDataMap = new HashMap<>();
        Row row = sheet.getRow(i);
        String tcId = row.getCell(0).getStringCellValue();
        for (int j = 0; j < row.getLastCellNum(); j++) {
          if (row.getCell(j).getCellType().equals(CellType.STRING)) {
            LOGGER.debug(row.getCell(j).getStringCellValue());
            innerDataMap.put(sheet.getRow(0).getCell(j).getStringCellValue(), row.getCell(j).getStringCellValue());
          } else if (row.getCell(j).getCellType().equals(CellType.NUMERIC)) {
            LOGGER.debug(row.getCell(j).getNumericCellValue());
            innerDataMap.put(String.valueOf(sheet.getRow(0).getCell(j).getStringCellValue()),
                String.valueOf(row.getCell(j).getNumericCellValue()));
          }
        }
        testDataMap.put(tcId, innerDataMap);
      }
      LOGGER.debug("test data map : {}", testDataMap);
    }
    return testDataMap;
  }
}
