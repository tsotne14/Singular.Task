package com.example.utils;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class Utils {

  private static final String AUTH = "/authorize";
  private static final String GET_USER_INFO = "/info";

    public static void setupBaseURI() {
        String baseURI = ConfigReader.getProperty("baseURI");
        RestAssured.baseURI = baseURI;
    }
    public static Response generateAuthToken(String username, String password) {
        JsonObject requestBody = new JsonObject();
        requestBody.add("username", username);
        requestBody.add("password", password);
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post(AUTH);
    }

    public static Response getUserDetails(Map<String, String> headers) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .headers(headers)
                .get(GET_USER_INFO);
    }
}
