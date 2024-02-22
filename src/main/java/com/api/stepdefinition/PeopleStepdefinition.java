package com.api.stepdefinition;

import com.api.utils.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Step definition class for handling actions and validations related to people in the API.
 */
public class PeopleStepdefinition {
    private final TestContext context; // Holds shared data and state for API tests.
    private static final Logger logger = LogManager.getLogger(PeopleStepdefinition.class); // Logger for debugging and logging test flow.

    /**
     * Initializes a new instance of the PeopleStepdefinition class.
     * @param context Shared test context for maintaining API test state across steps.
     */
    public PeopleStepdefinition(TestContext context) {
        this.context = context;
    }

    /**
     * Sends an HTTP GET request to the '/people' endpoint with a specified people ID.
     * Logs the action and stores the response in the shared test context.
     * @param peopleId The ID of the person to fetch from the API.
     */
    @When("user makes a request to people with {string}")
    public void userMakesARequestToPeopleWith(String peopleId) {
        logger.info("Running userMakesARequestToPeopleWith with peopleId: " + peopleId);
        context.response = context.requestSetup().when().get(context.session.get("endpoint") + peopleId);
    }

    /**
     * Asserts that the 'name' field in the API response matches the expected people name.
     * @param peopleName Expected name to validate against the response.
     */
    @Then("user people name is {string}")
    public void userPeopleNameIs(String peopleName) {
        logger.info("Running userPeopleNameIs: " + peopleName);
        assertEquals(peopleName, context.response.getBody().jsonPath().getString("name"));
    }

    /**
     * Asserts that the 'skin_color' field in the API response matches the expected skin color.
     * @param skinColor Expected skin color to validate against the response.
     */
    @Then("user people skin_color is {string}")
    public void userPeopleSkinColorIs(String skinColor) {
        logger.info("Running userPeopleSkinColorIs: " + skinColor);
        assertEquals(skinColor, context.response.getBody().jsonPath().getString("skin_color"));
    }

    /**
     * Validates that the list of films from the API response matches the expected list of film titles.
     * Films are checked by their titles which are fetched using their URLs from the response.
     * @param films Serialized list of expected film titles (e.g., "film1,film2").
     */
    @Then("user people films are {string}")
    public void userPeopleFilmsAre(String films) {
        boolean filmNamesMatched = true; // Flag to track if all film titles match.

        // Split the expected film titles and prepare for comparison.
        String[] expectedFilms = films.split(",");
        ArrayList<String> filmsList = new ArrayList<>(Arrays.asList(expectedFilms));

        // Fetch the list of film URLs from the response.
        List<String> filmUrls = context.response.getBody().jsonPath().getList("films");

        // Iterate over each film URL to fetch and compare the film title.
        for (String filmUrl : filmUrls) {
            logger.info("Iterating filmUrl:" + filmUrl);
            // If the expected film titles list doesn't contain the title from the response, set flag to false.
            if (!filmsList.contains(context.requestSetup().when().get(filmUrl).getBody().jsonPath().getString("title"))) {
                filmNamesMatched = false;
                break;
            }
        }
        // Assert that all film names matched.
        assertTrue(filmNamesMatched);
    }
}
