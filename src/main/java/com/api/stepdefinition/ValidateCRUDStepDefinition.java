package com.api.stepdefinition;

import com.api.utils.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Step definitions for validating CRUD operations via HTTP methods on a specified endpoint.
 */
public class ValidateCRUDStepDefinition {
    private TestContext context; // Shared test context for API interactions.
    private static final Logger logger = LogManager.getLogger(ValidateCRUDStepDefinition.class); // Logger for output.

    /**
     * Initializes the class with the shared test context.
     * @param context The test context.
     */
    public ValidateCRUDStepDefinition(TestContext context) {
        this.context = context;
    }

    /**
     * Sends an HTTP request based on the specified method to the stored endpoint URL.
     * Supports GET, PUT, POST, PATCH, and DELETE methods.
     * @param httpMethod The HTTP method to execute.
     */
    @When("user sends {string} HTTP request to endpoint")
    public void userSendsHttpRequestToEndpoint(String httpMethod) {
        String endpointURL = context.session.get("endpoint").toString();
        logger.info("Executing " + httpMethod + " request to: " + endpointURL);

        // Execute the request based on the HTTP method specified.
        switch (httpMethod) {
            case "GET":
                context.response = context.requestSetup().when().get(endpointURL);
                break;
            case "PUT":
                context.response = context.requestSetup().when().put(endpointURL);
                break;
            case "POST":
                context.response = context.requestSetup().when().post(endpointURL);
                break;
            case "PATCH":
                context.response = context.requestSetup().when().patch(endpointURL);
                break;
            case "DELETE":
                context.response = context.requestSetup().when().delete(endpointURL);
                break;
            default:
                logger.warn("Unsupported HTTP method: " + httpMethod);
                break;
        }
    }

    /**
     * Validates that the response code from the HTTP request matches the expected value.
     * @param responseCode The expected HTTP response code.
     */
    @Then("user receives valid HTTP response code {int}")
    public void userReceivesValidHttpResponseCode(Integer responseCode) {
        logger.info("Validating response code: " + responseCode);
        assertEquals("Unexpected HTTP response code.", Long.valueOf(responseCode), Long.valueOf(context.response.getStatusCode()));
    }
}