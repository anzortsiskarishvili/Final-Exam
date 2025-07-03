package oop.finalexam.t3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Handles communication with the REST API server.
 * Provides methods for sending GET and POST requests to manage blog posts and retrieve statistics.
 * It uses Java's built-in HttpClient for making HTTP requests.
 */
public class ApiClient {
    private final String baseUrl;
    private final HttpClient httpClient;

    /**
     * Constructs an ApiClient with the given base URL for the REST server.
     * The HttpClient is initialized with a connection timeout.
     *
     * @param baseUrl The base URL of the REST API server (e.g., "http://max.ge/final/t3/84716293/index.php").
     */
    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)) // 10-second connection timeout for network reliability
                .build();
    }

    /**
     * Sends a GET request to the specified endpoint relative to the base URL.
     *
     * @param endpoint The API endpoint (e.g., "?api=blogs", "?api=stats"). Must include leading query parameters if needed.
     * @return The response body as a String if successful (HTTP status 200), or an error message if the request fails or returns a non-200 status.
     */
    public String get(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + endpoint)) // Constructs the full URI
                    .GET() // Specifies GET method
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "Error: API returned status code " + response.statusCode() + "\nResponse: " + response.body();
            }
        } catch (java.net.ConnectException e) {
            // Specific error for network connection issues
            return "Error: Could not connect to the server. Please check the URL and your internet connection. Details: " + e.getMessage();
        } catch (IOException | InterruptedException e) {
            // General I/O errors or thread interruption during request
            return "Error sending GET request: " + e.getMessage();
        }
    }

    /**
     * Sends a POST request with a JSON body to the specified endpoint relative to the base URL.
     *
     * @param endpoint The API endpoint (e.g., "?api=blogs"). Must include leading query parameters if needed.
     * @param jsonBody The JSON string to send in the request body (e.g., "{\"title\": \"My Post\", \"content\": \"Hello\"}").
     * @return The response body as a String if successful (HTTP status 2xx), or an error message if the request fails or returns a non-2xx status.
     */
    public String post(String endpoint, String jsonBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + endpoint)) // Constructs the full URI
                    .header("Content-Type", "application/json") // Sets content type for JSON body
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody)) // Specifies POST method and attaches JSON body
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) { // Check for 2xx success codes
                return response.body();
            } else {
                return "Error: API returned status code " + response.statusCode() + "\nResponse: " + response.body();
            }
        } catch (java.net.ConnectException e) {
            // Specific error for network connection issues
            return "Error: Could not connect to the server. Please check the URL and your internet connection. Details: " + e.getMessage();
        } catch (IOException | InterruptedException e) {
            // General I/O errors or thread interruption during request
            return "Error sending POST request: " + e.getMessage();
        }
    }

    // --- Specific API Call Implementations ---

    /**
     * Fetches all blog posts from the server.
     * Corrected to use the "?api=blogs" endpoint.
     *
     * @return A JSON string representing all posts, or an error message if the request fails.
     */
    public String getAllBlogPosts() {
        return get("?api=blogs"); // Corrected endpoint based on API documentation
    }

    /**
     * Creates a new blog post on the server.
     * Corrected to use the "?api=blogs" endpoint.
     *
     * @param title The title of the post.
     * @param author The author of the post.
     * @param content The content of the post.
     * @return A JSON string indicating success/failure, or an error message.
     */
    public String createBlogPost(String title, String author, String content) {
        // Simple JSON string construction. For complex JSON, a library is recommended.
        String jsonBody = String.format("{\"title\": \"%s\", \"author\": \"%s\", \"content\": \"%s\"}",
                escapeJson(title), escapeJson(author), escapeJson(content));
        return post("?api=blogs", jsonBody); // Corrected endpoint based on API documentation
    }

    /**
     * Fetches general site statistics from the server.
     * Corrected to use the "?api=stats" endpoint.
     * @return A JSON string representing site statistics, or an error message.
     */
    public String getSiteStatistics() {
        return get("?api=stats"); // Corrected endpoint based on API documentation
    }

    /**
     * Helper method to escape double quotes and backslashes in JSON string values.
     * This is a very basic implementation and might not cover all edge cases for complex JSON.
     * For production, use a proper JSON library.
     */
    private String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
