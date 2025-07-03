
package oop.finalexam.t2;

/**
 * Represents a learning course within the University Management System (UMS).
 * Each course has a title, acceptance prerequisites, and major topics.
 * All fields are of String type and have mandatory setters and getters.
 */
public class LearningCourse {
    private String title;
    private String acceptancePrerequisites;
    private String majorTopics;

    /**
     * Constructs a new LearningCourse object.
     *
     * @param title The title of the course.
     * @param acceptancePrerequisites The prerequisites required for acceptance into the course.
     * @param majorTopics A summary of the main topics covered in the course.
     */
    public LearningCourse(String title, String acceptancePrerequisites, String majorTopics) {
        this.title = title;
        this.acceptancePrerequisites = acceptancePrerequisites;
        this.majorTopics = majorTopics;
    }

    /**
     * Returns the title of the learning course.
     * @return The course title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the learning course.
     * @param title The new title for the course.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the acceptance prerequisites for the learning course.
     * @return The acceptance prerequisites.
     */
    public String getAcceptancePrerequisites() {
        return acceptancePrerequisites;
    }

    /**
     * Sets the acceptance prerequisites for the learning course.
     * @param acceptancePrerequisites The new acceptance prerequisites.
     */
    public void setAcceptancePrerequisites(String acceptancePrerequisites) {
        this.acceptancePrerequisites = acceptancePrerequisites;
    }

    /**
     * Returns the major topics covered in the learning course.
     * @return The major topics.
     */
    public String getMajorTopics() {
        return majorTopics;
    }

    /**
     * Sets the major topics covered in the learning course.
     * @param majorTopics The new major topics.
     */
    public void setMajorTopics(String majorTopics) {
        this.majorTopics = majorTopics;
    }

    /**
     * Provides a string representation of the LearningCourse object,
     * including its title, prerequisites, and major topics.
     * @return A formatted string containing course details.
     */
    @Override
    public String toString() {
        return "  - Course Title: " + title + "\n" +
                "    Prerequisites: " + acceptancePrerequisites + "\n" +
                "    Major Topics: " + majorTopics;
    }
}