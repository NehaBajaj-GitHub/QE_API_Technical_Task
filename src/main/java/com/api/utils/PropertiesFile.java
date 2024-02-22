package com.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Utility class for loading and accessing properties from a configuration file.
 */
public class PropertiesFile {

	private static final Logger logger = LogManager.getLogger(PropertiesFile.class); // Logger for logging errors.
	private static Properties prop = new Properties(); // Properties object to hold the loaded properties.

	/**
	 * Retrieves a property value by its key from the configuration properties file.
	 * @param property The key of the property to retrieve.
	 * @return The value of the specified property, or {@code null} if not found.
	 */
	public static String getProperty(String property) {
		FileInputStream fis = null; // Local variable to avoid static misuse
		try {
			// Specify the path to your properties file
			fis = new FileInputStream(new File("config.properties"));
			// Load properties from the file
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			logger.error("Properties File Not Found", fnfe);
		} catch (IOException ioe) {
			logger.error("IO Exception while loading Properties File", ioe);
		} finally {
			if (fis != null) {
				try {
					fis.close(); // Ensure the FileInputStream is closed
				} catch (IOException e) {
					logger.error("IO Exception while closing file input stream", e);
				}
			}
		}
		// Retrieve the property value, trimming any leading or trailing whitespace
		return prop.getProperty(property) != null ? prop.getProperty(property).trim() : null;
	}
}
