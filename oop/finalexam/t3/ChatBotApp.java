package oop.finalexam.t3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken; // Important for parsing lists of objects
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

/*
 * ==============================================================================
 * IMPORTANT SETUP NOTE FOR RUNNING THIS APPLICATION:
 * ==============================================================================
 *
 *
 * This application depends on the 'Gson' library for JSON parsing.
 * If you are encountering "package com.google.gson does not exist" or
 * "cannot find symbol class Gson" errors, it means the Gson library
 * has not been correctly added to your project's dependencies.
 *
 * --- How to Add Gson in IntelliJ IDEA: ---
 * 1. Go to 'File' -> 'Project Structure...' (or press Ctrl+Alt+Shift+S / Cmd+;).
 * 2. In the left panel, select 'Modules' under 'Project Settings'.
 * 3. Go to the 'Dependencies' tab.
 * 4. Click the '+' sign at the bottom and choose 'Library...' -> 'From Maven...'.
 * 5. In the search bar, type 'com.google.code.gson:gson:' and select the latest stable version (e.g., 2.10.1).
 * 6. Click 'Add', then 'OK' on the following dialogs, and finally 'Apply' and 'OK' in the Project Structure window.
 * 7. After adding, rebuild your project: 'Build' -> 'Rebuild Project'.
 *
 * ==============================================================================
 */

/**
 * The main class for the ChatBot Console Application.
 * This application provides a user-friendly interface to interact with a REST API
 * for managing blog posts and retrieving site statistics.
 * It loads server URL and bot name from a configuration file.
 */
public class ChatBotApp {
    private static String botName;
    private static ApiClient apiClient;
    private static Scanner scanner;
    private static Gson gson; // Gson instance for JSON parsing

    /**
     * The entry point of the chatbot application.
     * Initializes configuration, API client, and starts the chat interaction.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in); // Initialize scanner for user input
        // Use GsonBuilder to enable pretty printing for readable JSON output in console
        gson = new GsonBuilder().setPrettyPrinting().create();

        ConfigLoader config = new ConfigLoader(); // Load configuration

        // Retrieve configurable properties
        String serverUrl = config.getProperty("SERVER_URL");
        botName = config.getProperty("BOT_NAME");

        // Validate configuration: ensure both URL and bot name are present and not empty
        if (serverUrl == null || serverUrl.trim().isEmpty() || botName == null || botName.trim().isEmpty()) {
            System.err.println("Critical Error: Missing or empty SERVER_URL or BOT_NAME in config.txt. Please ensure your configuration file is correctly set up.");
            return; // Exit application if configuration is invalid
        }

        apiClient = new ApiClient(serverUrl); // Initialize API client with the loaded URL

        System.out.println("Hello! I'm " + botName + ", your blog management assistant.");
        startChat(); // Begin the interactive chat session
    }

    /**
     * Manages the main chat loop, displaying menu options and handling user choices.
     * The loop continues until the user chooses to exit (option 4).
     */
    private static void startChat() {
        int choice;
        do {
            displayMenu(); // Show available options to the user
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Read user input as an integer
                handleChoice(choice); // Process the user's choice
            } catch (NumberFormatException e) {
                // Handle cases where user enters non-numeric input
                System.out.println("Invalid input. Please enter a number corresponding to a menu option.");
                choice = -1; // Set to an invalid choice to re-display the menu
            }
            System.out.println(); // Add a newline for better visual separation between interactions
        } while (choice != 4); // Loop until user selects option 4 (Exit)
    }

    /**
     * Displays the main menu options to the console, guiding the user on available actions.
     */
    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Create New Blog Post");
        System.out.println("2. View All Blog Posts");
        System.out.println("3. View Site Statistics");
        System.out.println("4. Exit");
        System.out.println("-----------------");
    }

    /**
     * Handles the user's menu choice by delegating to the appropriate functionality method.
     *
     * @param choice The integer choice made by the user.
     */
    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createNewBlogPost();
                break;
            case 2:
                viewAllBlogPosts();
                break;
            case 3:
                viewSiteStatistics();
                break;
            case 4:
                System.out.println("Goodbye! Thanks for chatting with " + botName + ".");
                scanner.close(); // Close the scanner to release system resources when exiting
                break;
            default:
                System.out.println("Invalid choice. Please select an option from 1 to 4.");
        }
    }

    /**
     * Prompts the user for details (title, author, content) for a new blog post
     * and sends a POST request to the server to create it. It then parses
     * the server's response to confirm success or report failure.
     */
    private static void createNewBlogPost() {
        System.out.println("\n--- Create New Blog Post ---");
        System.out.print("Enter post title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter post content: ");
        String content = scanner.nextLine();

        System.out.println("Attempting to create post...");
        String rawResponse = apiClient.createBlogPost(title, author, content);
        System.out.println("Server Raw Response: " + rawResponse); // Always show raw response for debugging

        try {
            // Parse the raw JSON response into a generic ApiResponse to check the 'success' flag
            // The data payload for creation might be a simple message or the created object itself.
            ApiResponse<Object> response = gson.fromJson(rawResponse, new TypeToken<ApiResponse<Object>>(){}.getType());

            if (response != null && response.isSuccess()) {
                System.out.println("Post created successfully!");
                // Optionally, you could parse the 'data' field further if it contains the new post's details
                // For example: BlogPost createdPost = gson.fromJson(gson.toJson(response.getData()), BlogPost.class);
                // System.out.println("New Post ID: " + createdPost.getId());
            } else {
                System.out.println("Failed to create post.");
                if (response != null && response.getData() != null) {
                    System.out.println("Details: " + gson.toJson(response.getData()));
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing post creation response: " + e.getMessage());
        }
    }

    /**
     * Fetches all existing blog posts from the server via a GET request
     * and displays them in a readable format using Gson parsing and the BlogPost's toString method.
     */
    private static void viewAllBlogPosts() {
        System.out.println("\n--- All Blog Posts ---");
        System.out.println("Fetching posts...");
        String rawResponse = apiClient.getAllBlogPosts();

        // Define the specific type for Gson to parse: ApiResponse containing a List of BlogPost objects
        Type blogListType = new TypeToken<ApiResponse<List<BlogPost>>>(){}.getType();
        ApiResponse<List<BlogPost>> response = null;

        try {
            response = gson.fromJson(rawResponse, blogListType);
        } catch (Exception e) {
            System.err.println("Error parsing blog posts response: " + e.getMessage());
            System.out.println("Raw Server Response (for debugging):\n" + rawResponse);
            return;
        }

        if (response != null && response.isSuccess() && response.getData() != null) {
            if (response.getData().isEmpty()) {
                System.out.println("No blog posts found.");
            } else {
                System.out.println("Found " + response.getMeta().getTotal() + " posts:");
                for (BlogPost post : response.getData()) {
                    System.out.println("\n" + post.toString()); // Uses the overridden toString() in BlogPost for clean display
                }
                System.out.println("------------------------------------");
            }
        } else {
            System.out.println("Failed to retrieve blog posts. Server response might indicate an error or be empty.");
            System.out.println("Raw Server Response (for debugging):\n" + rawResponse);
        }
    }

    /**
     * Fetches general site statistics from the server via a GET request
     * and displays them in a readable format using Gson parsing and the StatsData's toString method.
     */
    private static void viewSiteStatistics() {
        System.out.println("\n--- Site Statistics ---");
        System.out.println("Fetching statistics...");
        String rawResponse = apiClient.getSiteStatistics();

        // Define the specific type for Gson to parse: ApiResponse containing a StatsData object
        Type statsType = new TypeToken<ApiResponse<ApiResponse.StatsData>>(){}.getType();
        ApiResponse<ApiResponse.StatsData> response = null;

        try {
            response = gson.fromJson(rawResponse, statsType);
        } catch (Exception e) {
            System.err.println("Error parsing statistics response: " + e.getMessage());
            System.out.println("Raw Server Response (for debugging):\n" + rawResponse);
            return;
        }

        if (response != null && response.isSuccess() && response.getData() != null) {
            System.out.println(response.getData().toString()); // Uses the overridden toString() in StatsData for clean display
        } else {
            System.out.println("Failed to retrieve site statistics. Server response might indicate an error or be empty.");
            System.out.println("Raw Server Response (for debugging):\n" + rawResponse);
        }
    }
}
