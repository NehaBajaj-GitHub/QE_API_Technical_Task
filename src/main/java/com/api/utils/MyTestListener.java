package com.api.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.TestCaseFinished;

/**
 * Custom test listener for Cucumber tests that logs the outcome of each test scenario.
 * Implements ConcurrentEventListener to work with the Cucumber event model.
 */
public class MyTestListener implements ConcurrentEventListener {
	private static final Logger logger = LogManager.getLogger(MyTestListener.class); // Logger for logging test results.

	/**
	 * Registers this listener to receive notifications about test case completion events.
	 * @param publisher The event publisher that emits test execution events.
	 */
	@Override
	public void setEventPublisher(EventPublisher publisher) {
		// Register a handler for the TestCaseFinished event.
		publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
	}

	/**
	 * Handles the TestCaseFinished event, logging the result of the test case execution.
	 * @param event The TestCaseFinished event containing details about the test case and its result.
	 */
	private void handleTestCaseFinished(TestCaseFinished event) {
		TestCase testCase = event.getTestCase();
		Result result = event.getResult();
		Status status = result.getStatus();
		Throwable error = result.getError();
		String scenarioName = testCase.getName();

		// Log any errors encountered during the test case execution.
		if (error != null) {
			logger.error("Error in scenario: " + scenarioName, error);
		}

		// Log a summary of the test case result.
		logger.info("*****************************************************************************************");
		logger.info(" Scenario: " + scenarioName + " --> " + status.name());
		logger.info("*****************************************************************************************");
	}
}