package com.api.stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Hooks class for Cucumber to perform actions before and after each scenario.
 * Utilizes logging to provide clear demarcation and names of scenarios as they start and finish.
 */
public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class); // Logger for outputting information.

    /**
     * Logs the beginning of each scenario.
     *
     * @param scenario The scenario that is starting.
     */
    @Before
    public void testStart(Scenario scenario) {
        logger.info("*****************************************************************************************");
        logger.info(" Scenario started: " + scenario.getName());
        logger.info("*****************************************************************************************");
    }

    /**
     * Logs the completion of each scenario.
     *
     * @param scenario The scenario that has finished.
     */
    @After
    public void testEnd(Scenario scenario) {
        logger.info("*****************************************************************************************");
        logger.info(" Scenario finished: " + scenario.getName());
        logger.info("*****************************************************************************************");
    }
}
