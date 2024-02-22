package com.api.stepdefinition;

import static org.junit.Assert.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.api.utils.TestContext;
import io.cucumber.java.en.*;

/**
 * Step definitions for testing Starship-related endpoints in the API.
 * Utilizes shared test context for managing API response and session data.
 */
public class StarshipsStepdefinition {
	private TestContext context; // Shared context for passing data between steps.
	private static final Logger logger = LogManager.getLogger(StarshipsStepdefinition.class); // Logger for debugging and informational output.

	/**
	 * Constructs a StarshipsStepdefinition instance with a shared test context.
	 * @param context The test context shared across steps.
	 */
	public StarshipsStepdefinition(TestContext context) {
		this.context = context;
	}

	/**
	 * Sets the base endpoint URL for Starship API requests in the shared context.
	 * @param endpoint The base URL of the Starship endpoint.
	 */
	@Given("user has access to endpoint {string}")
	public void userHasAccessToEndpoint(String endpoint) {
		logger.info("Setting base endpoint: " + endpoint);
		context.session.put("endpoint", endpoint);
	}

	/**
	 * Sends an HTTP GET request to retrieve starship data by ID.
	 * @param starshipId The ID of the starship to fetch.
	 */
	@When("user makes a request to starships with {string}")
	public void userMakesARequestToStarshipIDs(String starshipId) {
		logger.info("Fetching Starship with ID: " + starshipId);
		context.response = context.requestSetup().when().get(context.session.get("endpoint") + starshipId);
	}

	/**
	 * Validates the HTTP response code from the starship request matches the expected code.
	 * @param responseCode The expected HTTP response code.
	 */
	@Then("user should get the response code {int}")
	public void userShouldGetTheResponseCode(Integer responseCode) {
		logger.info("Validating response code: " + responseCode);
		assertEquals("Response code mismatch", Long.valueOf(responseCode), Long.valueOf(context.response.getStatusCode()));
	}

	/**
	 * Asserts that the name of the starship in the response matches the expected name.
	 * @param starshipName The expected starship name.
	 */
	@Then("user starship name is {string}")
	public void userStarshipNameIs(String starshipName) {
		logger.info("Validating Starship name: " + starshipName);
		assertEquals("Starship name mismatch", starshipName, context.response.getBody().jsonPath().getString("name"));
	}

	/**
	 * Asserts that the crew count of the starship in the response matches the expected count.
	 * @param crewCount The expected crew count.
	 */
	@Then("user starship crew count is {string}")
	public void userStarshipCrewCountIs(String crewCount) {
		logger.info("Validating Starship crew count: " + crewCount);
		assertEquals("Starship crew count mismatch", crewCount, context.response.getBody().jsonPath().getString("crew"));
	}
}