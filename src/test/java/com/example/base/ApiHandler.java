package com.example.base;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiHandler {

  private static final Logger LOGGER = LogManager.getLogger(ApiHandler.class);

  public Response getRequest(String url, RequestSpecBuilder requestSpecBuilder) {
    return getRequest(url, requestSpecBuilder, RestAssuredConfig.newConfig());
  }

  public Response postRequest(String url, RequestSpecBuilder requestSpecBuilder) {
    return postRequest(url, requestSpecBuilder, RestAssuredConfig.newConfig());
  }

  public Response putRequest(String url, RequestSpecBuilder requestSpecBuilder) {
    return putRequest(url, requestSpecBuilder, RestAssuredConfig.newConfig());
  }

  public Response deleteRequest(String url, RequestSpecBuilder requestSpecBuilder) {
    return deleteRequest(url, requestSpecBuilder, RestAssuredConfig.newConfig());
  }

  public Response optionsRequest(String url, RequestSpecBuilder requestSpecBuilder) {
    return optionsRequest(url, requestSpecBuilder, RestAssuredConfig.newConfig());
  }

  public Response patchRequest(String url, RequestSpecBuilder requestSpecBuilder) {
    return getRequest(url, requestSpecBuilder, RestAssuredConfig.newConfig());
  }

  public Response soapRequest(String url, RequestSpecBuilder requestSpecBuilder) {
    return soapRequest(url, requestSpecBuilder, RestAssuredConfig.newConfig());
  }

  @Step(value = "GET Request")
  public Response getRequest(String url, RequestSpecBuilder requestSpecBuilder, RestAssuredConfig restAssuredConfig) {
    requestSpecBuilder.addFilter(new AllureRestAssured());
    Response response = given(requestSpecBuilder.build()).log().all(true)
        .config(restAssuredConfig)
        .urlEncodingEnabled(false)
        .when()
        .get(url)
        .then().log().all(true)
        .and().extract().response();
    LOGGER.debug("Response {} ", response.asString());
    return response;
  }

  @Step(value = "POST Request")
  public Response postRequest(String url, RequestSpecBuilder requestSpecBuilder, RestAssuredConfig restAssuredConfig) {
    requestSpecBuilder.addFilter(new AllureRestAssured());
    Response response = given(requestSpecBuilder.build()).log().all(true)
        .config(restAssuredConfig)
        .urlEncodingEnabled(false)
        .when()
        .post(url)
        .then().log().all(true)
        .and().extract().response();
    LOGGER.debug("Response {} ", response.asString());
    return response;
  }

  @Step(value = "PUT Request")
  public Response putRequest(String url, RequestSpecBuilder requestSpecBuilder, RestAssuredConfig restAssuredConfig) {
    requestSpecBuilder.addFilter(new AllureRestAssured());
    Response response = given(requestSpecBuilder.build()).log().all(true)
        .config(restAssuredConfig)
        .urlEncodingEnabled(false)
        .when()
        .put(url)
        .then().log().all(true)
        .and().extract().response();
    LOGGER.debug("Response {} ", response.asString());
    return response;
  }

  @Step(value = "DELETE Request")
  public Response deleteRequest(String url, RequestSpecBuilder requestSpecBuilder, RestAssuredConfig restAssuredConfig) {
    requestSpecBuilder.addFilter(new AllureRestAssured());
    Response response = given(requestSpecBuilder.build()).log().all(true)
        .config(restAssuredConfig)
        .urlEncodingEnabled(false)
        .when()
        .delete(url)
        .then().log().all(true)
        .and().extract().response();
    LOGGER.debug("Response {} ", response.asString());
    return response;
  }

  @Step(value = "OPTIONS Request")
  public Response optionsRequest(String url, RequestSpecBuilder requestSpecBuilder, RestAssuredConfig restAssuredConfig) {
    requestSpecBuilder.addFilter(new AllureRestAssured());
    Response response = given(requestSpecBuilder.build()).log().all(true)
        .config(restAssuredConfig)
        .urlEncodingEnabled(false)
        .when()
        .options(url)
        .then().log().all(true)
        .and().extract().response();
    LOGGER.debug("Response {} ", response.asString());
    return response;
  }

  @Step(value = "PATCH Request")
  public Response patchRequest(String url, RequestSpecBuilder requestSpecBuilder, RestAssuredConfig restAssuredConfig) {
    requestSpecBuilder.addFilter(new AllureRestAssured());
    Response response = given(requestSpecBuilder.build()).log().all(true)
        .config(restAssuredConfig)
        .urlEncodingEnabled(false)
        .when()
        .patch(url)
        .then().log().all(true)
        .and().extract().response();
    LOGGER.debug("Response {} ", response.asString());
    return response;
  }

  @Step(value = "SOAP Request")
  public Response soapRequest(String url, RequestSpecBuilder requestSpecBuilder, RestAssuredConfig restAssuredConfig) {
    requestSpecBuilder.addFilter(new AllureRestAssured());
    Response response = given(requestSpecBuilder.build()).log().all(true)
        .config(restAssuredConfig)
        .urlEncodingEnabled(false)
        .when()
        .post(url)
        .then().log().all(true)
        .and().extract().response();
    LOGGER.debug("Response {} ", response.asString());
    return response;
  }
}
