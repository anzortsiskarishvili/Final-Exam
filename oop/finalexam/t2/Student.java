package oop.finalexam.t2;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student enrolled in the University Management System (UMS).
 * Each student has a name, a unique student ID, and a list of learning courses they are taking.
 */
public class Student {
    private String name;
    private String studentId;
    private List<LearningCourse> learningCourses;

    /**
     * Constructs a new Student object.
     *
     * @param name The name of the student.
     * @param studentId The unique ID of the student.
     */
    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.learningCourses = new ArrayList<>();
    }

    /**
     * Returns the name of the student.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     * @param name The new name for the student.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the student's ID.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student's ID.
     * @param studentId The new ID for the student.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Returns the list of learning courses the student is taking.
     * @return A list of LearningCourse objects.
     */
    public List<LearningCourse> getLearningCourses() {
        return learningCourses;
    }

    /**
     * Sets the list of learning courses for the student.
     * @param learningCourses The new list of LearningCourse objects.
     */
    public void setLearningCourses(List<LearningCourse> learningCourses) {
        this.learningCourses = learningCourses;
    }

    /**
     * Adds a single learning course to the student's list of courses.
     * @param course The LearningCourse object to add.
     */
    public void addLearningCourse(LearningCourse course) {
        if (this.learningCourses == null) {
            this.learningCourses = new ArrayList<>();
        }
        this.learningCourses.add(course);
    }

    /**
     * Provides a string representation of the Student object,
     * including their name and student ID.
     * @return A formatted string containing student details.
     */
    @Override
    public String toString() {
        return "Student Name: " + name + ", Student ID: " + studentId;
    }
}