package oop.finalexam.t3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to load configuration properties from a text file named 'config.txt'.
 * The configuration file should contain key-value pairs (e.g., KEY=VALUE).
 */
public class ConfigLoader {
    private static final String CONFIG_FILE_NAME = "config.txt";
    private final Map<String, String> properties = new HashMap<>();

    /**
     * Constructs a ConfigLoader and attempts to load properties from the default config file.
     * If the file is not found or an error occurs, appropriate messages are printed to stderr.
     */
    public ConfigLoader() {
        loadConfig();
    }

    /**
     * Loads configuration properties from the 'config.txt' file.
     * It reads each line, trims whitespace, ignores empty lines and comments (starting with '#'),
     * and parses key-value pairs separated by an '=' sign.
     */
    private void loadConfig() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) { // Ignore empty lines and comments
                    continue;
                }
                int equalsIndex = line.indexOf('=');
                if (equalsIndex > 0) { // Ensure '=' is not at the beginning and exists
                    String key = line.substring(0, equalsIndex).trim();
                    String value = line.substring(equalsIndex + 1).trim();
                    properties.put(key, value);
                }
            }
            System.out.println("Configuration loaded successfully from " + CONFIG_FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error loading configuration from " + CONFIG_FILE_NAME + ": " + e.getMessage());
            System.err.println("Please ensure '" + CONFIG_FILE_NAME + "' exists in the project root directory " +
                    "(e.g., next to your 'src' folder) and is readable.");
        }
    }

    /**
     * Retrieves a configuration property by its key.
     *
     * @param key The key of the property to retrieve (e.g., "SERVER_URL", "BOT_NAME").
     * @return The value of the property as a String, or null if the key is not found in the configuration.
     */
    public String getProperty(String key) {
        return properties.get(key);
    }
}

