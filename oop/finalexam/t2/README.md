![mermaid-diagram-2025-07-03-002908](https://github.com/user-attachments/assets/03daf00c-d042-438b-a5ab-abced7020c43)
## 🧱 Class Breakdown

### 👨‍🎓 `Student.java`
- Fields: `name`, `studentId`, `List<LearningCourse>`
- Methods:
  - Getters and setters
  - `addLearningCourse(LearningCourse)`
  - `toString()`: nicely formats name and ID

### 📘 `LearningCourse.java`
- Fields: `courseTitle`, `prerequisites`, `majorTopics`
- Methods:
  - Getters and setters
  - `toString()`: formats course info for display

### 🏫 `UMS.java`
- Manages a list of `Student` objects
- Demonstrates:
  - Adding students
  - Printing each student’s info and their courses
- Contains a sample "yourself" student with real university courses

## ✨ Example Customization

To add your own student, just modify the `main` method in `UMS.java` like so:

```java
Student newStudent = new Student("Your Name", "YourID123");
newStudent.addLearningCourse(new LearningCourse(
    "New Course Title",
    "Some Prerequisite",
    "Course topics go here..."
));
universitySystem.addStudent(newStudent);

