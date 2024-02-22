package com.api.utils;

import java.util.HashMap;
import java.util.Map;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Manages the context for API tests, including request configurations, responses, and session data.
 */
public class TestContext {

	// Holds the last response object for use in subsequent steps or validations.
	public Response response;
	// A session map to hold and pass data between steps.
	public Map<String, Object> session = new HashMap<>();
	// Content type for the requests, fetched from the properties file.
	private static final String CONTENT_TYPE = PropertiesFile.getProperty("content.type");

	/**
	 * Prepares and returns a RestAssured request specification with predefined configuration.
	 * This includes setting the base URI, content type, and accept header based on properties,
	 * and configuring cURL logging for the requests.
	 *
	 * @return A RestAssured RequestSpecification ready for further customization and execution.
	 */
	public RequestSpecification requestSetup() {
		RestAssured.reset(); // Reset RestAssured's static state before configuring.
		Options options = Options.builder().logStacktrace().build(); // Configure cURL logging options.
		RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options); // Apply cURL logging config.
		RestAssured.baseURI = PropertiesFile.getProperty("baseURL"); // Set the base URI for API requests.

		// Return a request specification with the configured settings.
		return RestAssured.given()
				.config(config)
				.contentType(CONTENT_TYPE)
				.accept(CONTENT_TYPE);
	}
}
