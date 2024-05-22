package com.example.stepdefs;

import com.example.utils.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinitions {
    private Response response;
    private String authToken;
    private Response userDetailsResponse;

    @Given("the API is up and running")
    public void the_API_is_up_and_running() {
        Utils.setupBaseURI();
    }

    @When("send post request {string}, {string}")
    public void sendPostRequest(String username, String password) {
        response = Utils.generateAuthToken(username, password);
    }

    @When("the response status code should be {int}")
    public void responseStatusCodeShouldBe(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    @When("get auth token")
    public void getAuthToken() {
        authToken =  response.jsonPath().getString("token");
    }

    @Then("send get request for user info")
    public void sendGetRequest () {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", authToken);
        userDetailsResponse = Utils.getUserDetails(headers);
    }

    @Then("send get request for user info when session is invalid")
    public void sendGetRequestInvalidSession () {
        String randomUUID = UUID.randomUUID().toString();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", randomUUID);
        userDetailsResponse = Utils.getUserDetails(headers);
    }

    @Then("Check if user info data is correct")
    public void checkIfUserInfoDataIsCorrect() {
        userDetailsResponse.then().assertThat()
                .body("data.name", equalTo("John"))
                .body("data.surname", equalTo("Doe"))
                .body("data.age", equalTo("30"))
                .body("data.gender", equalTo(1))
                .body("data.language", equalTo("en"))
                .body("data.status", equalTo("registered"))
                .body("data.isBlocked", equalTo(false));
    }
}
