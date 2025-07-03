# ListProcessor – Java Final Exam Project (OOP)

## 📦 Package
`oop.finalexam.t1`

## 📄 Description

`ListProcessor` is a utility class that processes two lists—one of integers (`list1`) and one of strings (`list2`)—based on a two-step algorithm:

1. **Initial Processing (String Selection and Combination)**  
   - For each integer `N` in `list1`, it retrieves the string at **index (N + 1)** from `list2` (0-based).
   - The retrieved string is concatenated with the original number `N` to form an entry in the intermediate output list.
   - If `(N + 1)` is out of bounds for `list2`, that entry is skipped with a warning.

2. **Filtering (Post-processing Removal)**  
   - It removes elements from the intermediate list using the **unique values** in `list1` as **indices**.
   - Only one removal is attempted per unique value.
   - Any removal index that is out-of-bounds is skipped gracefully.

## ✅ Features

- General-purpose solution that supports any `List<Integer>` and `List<String>`.
- Handles errors gracefully (e.g., out-of-bounds access).
- Works with duplicated values in `list1` by removing only once per unique value.
- Provides clear console output for debugging and understanding the steps.

## 📂 Files

- `ListProcessor.java`: The main class implementing the logic.
- `README.md`: This file.

## 🚀 How It Works

```java
ListProcessor processor = new ListProcessor();<img width="1440" alt="Screenshot 2025-07-02 at 22 14 58" src="https://github.com/user-attachments/assets/8142b607-db90-4301-9651-ed50e4115bf1" />

String result = processor.processLists();
System.out.println(result);
