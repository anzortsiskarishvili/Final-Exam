# Final Exam Java Project

This repository contains the solution to a Java-based final exam project structured into three separate tasks under packages:

* `oop.finalexam.t1` — List processing logic
* `oop.finalexam.t2` — University Management System (UMS)
* `oop.finalexam.t3` — REST API-based Chat Bot for Blog Management

---

## 📦 Task 1: List Processor (Package: `oop.finalexam.t1`)

### Description

This module implements a custom list-processing logic involving two lists:

* `list1`: A list of integers
* `list2`: A list of strings

### Rules Applied

1. **String Selection & Combination**:

   * For each value `N` in `list1`, retrieve the string at index `N + 1` from `list2`.
   * Concatenate that string with the original number `N`.
   * Skip any elements that cause out-of-bounds access.

2. **Filtering / Removal**:

   * Remove elements from the intermediate result based on the **unique values** from `list1`, treating them as indices.
   * Removal happens only once per unique value, and skips invalid indices.

### How to Run

No input is required. Run the `main` method of `ListProcessor` to see:

* Predefined list demo
* Custom test cases

---

## 📦 Task 2: University Management System (Package: `oop.finalexam.t2`)

### Description

This task simulates a mini university management system (UMS) that manages students and their enrolled courses.

### Main Classes

* `Student`: Represents a student with name, ID, and a list of `LearningCourse` objects.
* `LearningCourse`: Contains title, prerequisites, and major topics.
* `UMS`: Core system to manage and print all students and their associated learning courses.

### Features

* Add students to the UMS
* Add learning courses to each student
* Display all student information with enrolled courses and topics

### How to Run

Run the `main` method in the `UMS` class to display the demo with three students:

1. Yourself with 5 courses
2. A sample student with 2 courses
3. A student with no enrolled courses

---

## 📦 Task 3: REST API Chat Bot (Package: `oop.finalexam.t3`)

### Description

This is a Java console application acting as a blog management chatbot. It communicates with a REST API using Java's `HttpClient`.

### Features

* 📥 **View All Blog Posts** — via `GET ?api=blogs`
* 📤 **Create New Blog Post** — via `POST ?api=blogs`
* 📊 **View Site Statistics** — via `GET ?api=stats`

### Configuration

The bot reads from `config.txt`:

```
SERVER_URL=http://max.ge/final/t3/84716293/index.php
BOT_NAME=BlogBot3000
```

Place `config.txt` in the **project root directory** (next to `src`).

### Core Classes

* `ApiClient`: Handles `GET` and `POST` requests.
* `BlogPost`: Represents a blog post.
* `ApiResponse<T>`: Handles responses and metadata.
* `ConfigLoader`: Reads the configuration.

### How to Run

* Make sure `config.txt` exists.
* Run your console-based main class (not included in the provided code).
* Choose between the 3 features by interacting with the bot in the terminal.


---

## 🛠️ Technologies Used

* Java 17+
* Java HttpClient (java.net.http)
* Collections Framework
* JSON Processing with manual string manipulation (no external libraries)

---

## 📁 Project Structure

```
src/
 ├── oop/
     ├── finalexam/
          ├── t1/   <-- Task 1: ListProcessor
          ├── t2/   <-- Task 2: UMS
          └── t3/   <-- Task 3: REST API Blog Bot
config.txt        <-- Configuration file for Task 3
```

---

## 📄 License

This project is part of an academic final exam and is intended for educational purposes only.

---

## 👤 Author

Anzor Tsiskarishvili
Student ID: 08601040620
