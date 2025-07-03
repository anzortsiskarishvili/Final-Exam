package oop.finalexam.t3;

/**
 * Represents a single blog post object, mapping to the JSON structure returned by the API.
 * Field names are chosen to directly match the JSON keys for automatic mapping by Gson.
 */
public class BlogPost {
    private String title;
    private String content;
    private String author;
    private String created_at; // Matches JSON key 'created_at'
    private String updated_at; // Matches JSON key 'updated_at'
    private String id;

    // Default constructor is necessary for JSON deserialization by Gson
    public BlogPost() {
    }

    // --- Getters ---
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getId() {
        return id;
    }

    // --- Setters (optional, but good practice for full POJO compliance) ---
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Provides a user-friendly string representation of the blog post.
     * @return A formatted string displaying post details.
     */
    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Author: " + (author != null && !author.isEmpty() ? author : "Anonymous") + "\n" +
                "Content:\n" + content + "\n" +
                "Created: " + created_at + "\n" +
                "ID: " + id + "\n" +
                "------------------------------------";
    }
}
