package com.api.test;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

/**
 * Configures and runs Cucumber tests based on the specified options.
 * This class sets up the environment for executing BDD tests written in Gherkin syntax.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {
				"pretty:target/cucumber/cucumber.txt", // Makes console output more readable.
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", // Integrates Extent Reports.
				"json:target/cucumber/cucumber.json", // Generates JSON report at the specified path.
				"com.api.utils.MyTestListener" // Custom listener for additional test execution logging.
		},
		features = {"src/test/resources/features"}, // Specifies the path to the feature files.
		glue = {"com.api.stepdefinition"}, // Specifies the package containing step definitions and hooks.
		monochrome = true, // Enables more readable console output by removing unnecessary characters.
		snippets = SnippetType.CAMELCASE, // Configures the format for generated code snippets.
		tags = "@SWAPI" // Filters scenarios by tags; only scenarios with @SWAPI will be executed.
)
public class TestRunner {
	// This class does not require additional code. It serves as an entry point for running Cucumber tests.
}
