package xmlHandler;

import java.util.List;

public class DataModel {

  private String tcId;
  private List<String> testRunnerList;
  private List<String> businessFlowList;
  private List<String> generalDataList;

  public String getTcId() {
    return tcId;
  }

  public void setTcId(String tcId) {
    this.tcId = tcId;
  }

  public List<String> getTestRunnerList() {
    return testRunnerList;
  }

  public void setTestRunnerList(List<String> testRunnerList) {
    this.testRunnerList = testRunnerList;
  }

  public List<String> getBusinessFlowList() {
    return businessFlowList;
  }

  public void setBusinessFlowList(List<String> businessFlowList) {
    this.businessFlowList = businessFlowList;
  }

  public List<String> getGeneralDataList() {
    return generalDataList;
  }

  public void setGeneralDataList(List<String> generalDataList) {
    this.generalDataList = generalDataList;
  }

  @Override
  public String toString() {
    return "DataModel{" +
        "tcId='" + tcId + '\'' +
        ", testRunnerList=" + testRunnerList +
        ", businessFlowList=" + businessFlowList +
        ", generalDataList=" + generalDataList +
        '}';
  }
}

