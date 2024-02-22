package com.api.stepdefinition;

import com.api.utils.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import static org.junit.Assert.assertEquals;

/**
 * Defines step definitions for species-related scenarios, handling API requests and assertions for species data.
 */
public class SpeciesStepdefinition {
	private TestContext context; // Context for sharing data and state across test steps.
	private static final Logger logger = LogManager.getLogger(SpeciesStepdefinition.class); // Logger for debugging and informational output.

	/**
	 * Constructs a SpeciesStepdefinition instance with a shared test context.
	 * @param context The test context shared across steps.
	 */
	public SpeciesStepdefinition(TestContext context) {
		this.context = context;
	}

	/**
	 * Sends an HTTP GET request to retrieve species information by species ID.
	 * @param speciesId The ID of the species to fetch.
	 */
	@When("user makes a request to species with {string}")
	public void userMakesARequestToSpeciesWith(String speciesId) {
		logger.info("Running user_makes_a_request_to_species_with with speciesId: " + speciesId);
		context.response = context.requestSetup().when().get(context.session.get("endpoint") + speciesId);
	}

	/**
	 * Asserts that the species name in the response matches the expected name.
	 * @param speciesName The expected species name to validate.
	 */
	@Then("user species name is {string}")
	public void userSpeciesNameIs(String speciesName) {
		logger.info("Running user_species_name_is: " + speciesName);
		assertEquals(speciesName, context.response.getBody().jsonPath().getString("name"));
	}

	/**
	 * Asserts that the species classification in the response matches the expected classification.
	 * @param classification The expected species classification to validate.
	 */
	@Then("user species classification is {string}")
	public void userSpeciesClassificationIs(String classification) {
		logger.info("Running user_species_classification_is: " + classification);
		assertEquals(classification, context.response.getBody().jsonPath().getString("classification"));
	}

	/**
	 * Asserts that the name of the species' homeworld matches the expected homeworld name.
	 * The homeworld information is fetched from the homeworld URL provided in the species response.
	 * @param homeworld The expected name of the species' homeworld to validate.
	 */
	@Then("user species homeworld name is {string}")
	public void userSpeciesHomeworldNameIs(String homeworld) {
		logger.info("Running user_species_homeworld_name_is: " + homeworld);
		String homeworldURL = context.response.getBody().jsonPath().getString("homeworld");
		logger.info("homeworldURL: " + homeworldURL);
		// Fetches the homeworld information and asserts the name.
		assertEquals(homeworld, context.requestSetup().when().get(homeworldURL).getBody().jsonPath().getString("name"));
	}
}