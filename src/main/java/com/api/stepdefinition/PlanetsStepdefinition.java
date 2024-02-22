package com.api.stepdefinition;

import com.api.utils.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static org.junit.Assert.assertNotNull;

/**
 * Step definition class for handling actions and validations related to planets in the API.
 */
public class PlanetsStepdefinition {
    private TestContext context; // Shared context across steps
    private static final Logger logger = LogManager.getLogger(PlanetsStepdefinition.class);

    /**
     * Constructs a PlanetsStepdefinition instance with shared test context.
     * @param context Shared context for API tests.
     */
    public PlanetsStepdefinition(TestContext context) {
        this.context = context;
    }

    /**
     * Sends an HTTP GET request to the planets endpoint with a specific ID and sets the format to Wookiee.
     * Logs the action for debugging purposes.
     * @param planetsId The ID of the planet to fetch from the API.
     */
    @When("user makes a request to planets with {string} with format set to Wookiee")
    public void userMakesARequestToPlanetsWithFormatSetToWookiee(String planetsId) {
        logger.info("Running userMakesARequestToPlanetsWithFormatSetToWookiee with planetsId: " + planetsId);
        context.response = context.requestSetup().queryParam("format", "wookiee").when().get(context.session.get("endpoint") + planetsId);
    }

    /**
     * Validates that the expected Wookiee key is present in the API response body.
     * Converts the response body to a JSONObject for easier key existence checking.
     * @param expectedWookieKey The key expected to be present in the response body.
     */
    @Then("user sees {string} in response body")
    public void userSeesWhrascwoInResponseBody(String expectedWookieKey) {
        logger.info("Running userSeesWhrascwoInResponseBody with expectedWookieKey: " + expectedWookieKey);

        JSONObject jsonResponse = new JSONObject(context.response.getBody().asString());
        // Asserts the presence of the expected key in the response body.
        assertNotNull("The expected wookie key '" + expectedWookieKey + "' was not found in the response body.", jsonResponse.opt(expectedWookieKey));
    }
}