package oop.finalexam.t2;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the University Management System (UMS).
 * This class manages a collection of students and provides functionality
 * to print detailed information about each student, including their enrolled courses.
 */
public class UMS {
    private List<Student> students;

    /**
     * Constructs a new UMS object, initializing an empty list of students.
     */
    public UMS() {
        this.students = new ArrayList<>();
    }

    /**
     * Adds a student to the UMS.
     * @param student The Student object to be added.
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Returns the list of all students managed by the UMS.
     * @return A list of Student objects.
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the list of students managed by the UMS.
     * @param students The new list of Student objects.
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Prints the detailed information for a given student, including their
     * name, student ID, and all learning courses they are enrolled in.
     * Each learning course's title, prerequisites, and major topics are displayed.
     * The output clearly indicates which courses belong to the student.
     *
     * @param student The Student object whose data is to be printed.
     */
    public void printStudentData(Student student) {
        System.out.println("--- Student Information ---");
        System.out.println(student.toString()); // Prints Student Name and ID

        List<LearningCourse> courses = student.getLearningCourses();
        if (courses.isEmpty()) {
            System.out.println("  No learning courses enrolled for this student.");
        } else {
            System.out.println("  Learning Courses for " + student.getName() + ":");
            for (LearningCourse course : courses) {
                System.out.println(course.toString()); // Prints course details
            }
        }
        System.out.println("---------------------------\n");
    }

    /**
     * Main method to demonstrate the functionality of the UMS.
     * It creates sample students, including a special "myself" student with
     * courses from Argus, adds them to the UMS, and prints their data.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        UMS universitySystem = new UMS();

        // --- Create "Yourself" Student with Argus Courses ---
        Student myselfStudent = new Student("Anzor Tsiskarishvili", "08601040620"); // Placeholder for your name/ID
        myselfStudent.addLearningCourse(new LearningCourse(
                "Object Oriented Programming (ENG)",
                "CS50 Introduction to Programming",
                "Java syntax and data structures; Procedural programming; Classes; Encapsulation, polymorphism, inheritance; Packages; Working with the network; Work with files; Working with text data; Work with the terminal."
        ));
        myselfStudent.addLearningCourse(new LearningCourse(
                "Computer Organization (ENG)",
                "CS50 Introduction to Programming",
                "Representing and manipulating information; Machine-level representations of programs; Optimizing program performance. The memory hierarchy."
        ));
        myselfStudent.addLearningCourse(new LearningCourse(
                "Calculus II (ENG)",
                "MATH151",
                "Antiderivatives. Definite integrals. Techniques and applications of integration. Improper integrals. Infinite series."
        ));
        myselfStudent.addLearningCourse(new LearningCourse(
                "English Language Course C1-2 (ENG)",
                "English Language Course C1-2 (ENG)",
                "Culture; Interpretation; Persuasion; Connection; Technology; Independence"
        ));
        myselfStudent.addLearningCourse(new LearningCourse(
                "Mathematical Foundation of Computing (ENG)",
                "MATH 150 Calculus I, CS50 Introduction to Programming",
                "Mathematical Logic; Elements of Discrete Mathematics; Elements of Sets Theory; Elements of Graph Theory; Elements of Combinatorics; Elements of Digital Systems;"
        ));
        universitySystem.addStudent(myselfStudent);

        // --- Create another Sample Student ---
        Student sampleStudent = new Student("Alice Smith", "S98765432");
        sampleStudent.addLearningCourse(new LearningCourse(
                "Data Structures (ENG)",
                "Object Oriented Programming (ENG)",
                "Arrays, linked lists, trees, graphs, sorting, searching algorithms."
        ));
        sampleStudent.addLearningCourse(new LearningCourse(
                "Database Systems (ENG)",
                "Computer Organization (ENG)",
                "Relational model, SQL, database design, normalization, transactions."
        ));
        universitySystem.addStudent(sampleStudent);

        // --- Create a student with no courses ---
        Student noCourseStudent = new Student("Bob Johnson", "B11223344");
        universitySystem.addStudent(noCourseStudent);


        // --- Print Data for All Students in UMS ---
        System.out.println("--- Printing Data for All Students in UMS ---");
        for (Student student : universitySystem.getStudents()) {
            universitySystem.printStudentData(student);
        }
    }
}