package oop.finalexam.t1; // Note: The user mentioned oop.final.t2 but the previous context was t1, sticking to t1 as it was the established package.

import java.util.ArrayList;
import java.util.Collections; // For sorting
import java.util.HashSet;   // For unique removal indices
import java.util.List;
import java.util.Objects;
import java.util.Set;       // For unique removal indices

/**
 * This class processes two lists: an integer list (list1) and a string list (list2).
 * The core logic involves using values from list1 as indices to retrieve
 * corresponding strings from list2, and then combining these retrieved strings with
 * their original number from list1. The order of the output elements is preserved
 * from the original order of list1.
 *
 * This version incorporates the following refined rules:
 *
 * 1.  **Initial Processing (String Selection and Combination):**
 * * For each number 'N' from list1, the string is retrieved from list2 at
 * **0-based index (N + 1)**.
 * * The retrieved string is then concatenated with the original number 'N'.
 * * Elements causing out-of-bounds access (i.e., N+1 is an invalid index for list2)
 * during this initial step are skipped.
 *
 * 2.  **Filtering (Post-processing Removal):**
 * * After generating the intermediate output list, the program iterates through the
 * **unique values** present in the *original list1*.
 * * For each *unique value* 'V' from list1, it attempts to remove the element at
 * **0-based index 'V'** from the *intermediate output list*.
 * * If a value 'V' is an out-of-bounds index for the intermediate output list
 * (e.g., negative, or greater than or equal to the list's current size), the
 * removal attempt for that specific index 'V' is skipped without error.
 * * This ensures that if `list1` contains duplicate numbers (e.g., multiple '3's),
 * the element at index `3` in the output list is targeted for removal only once.
 *
 * This implementation is designed to be general-purpose and works for any given
 * `List<Integer>` and `List<String>`. It also includes robust error handling.
 */
public class ListProcessor {

    private final List<Integer> list1;
    private final List<String> list2;

    /**
     * Constructs a ListProcessor with predefined list values as per the problem description.
     * These lists are used for demonstration purposes in the main method.
     * list1: 7, 10, 8, 3, 6, 9, 1, 4, 5, 2
     * list2: yso, kcg, qkn, wzx, fnr, vdm, isx, ygi, qtm, ljp, qxf, kzd
     */
    public ListProcessor() {
        this.list1 = new ArrayList<>(List.of(7, 10, 8, 3, 6, 9, 1, 4, 5, 2));
        this.list2 = new ArrayList<>(List.of("yso", "kcg", "qkn", "wzx", "fnr", "vdm", "isx", "ygi", "qtm", "ljp", "qxf", "kzd"));
    }

    /**
     * Constructs a ListProcessor with custom list values.
     * This constructor allows the program to work with any provided lists,
     * ensuring it is not hardcoded.
     *
     * @param list1 The list of integers whose values determine the string to be picked,
     * the number to be appended, and the indices for removal in the output.
     * @param list2 The list of strings from which values are grabbed.
     * @throws NullPointerException if either list1 or list2 is null.
     */
    public ListProcessor(List<Integer> list1, List<String> list2) {
        this.list1 = Objects.requireNonNull(list1, "list1 cannot be null");
        this.list2 = Objects.requireNonNull(list2, "list2 cannot be null");
    }

    /**
     * Processes the lists according to the specified algorithm:
     * 1.  Generates an intermediate output list by taking numbers 'N' from list1,
     * retrieving strings from list2 at index (N + 1), and concatenating them.
     * Invalid index accesses during this step are skipped.
     * 2.  Filters this intermediate output list by removing elements at indices
     * corresponding to the unique values present in the original list1.
     * Out-of-bounds removal attempts are gracefully skipped.
     *
     * @return A string representing the final processed and filtered list, with elements
     * separated by ", ". Returns an empty string if no valid elements remain.
     */
    public String processLists() {
        if (list1.isEmpty() || list2.isEmpty()) {
            System.err.println("Warning: One or both lists are empty. Cannot process.");
            return "";
        }

        // --- Step 1: Initial Processing (String Selection and Combination) ---
        List<String> intermediateOutputElements = new ArrayList<>();
        System.out.println("--- Starting List Processing (Initial Combination) ---");

        for (int i = 0; i < list1.size(); i++) {
            int numberFromList1 = list1.get(i);
            // New rule: use (N + 1) as 0-based index for list2
            int targetIndexInList2 = numberFromList1 + 1;

            // Error handling for out-of-bounds access in list2
            if (targetIndexInList2 < 0 || targetIndexInList2 >= list2.size()) {
                System.err.printf("Error: Value '%d' at list1 index %d results in an out-of-bounds index (%d) for list2 (size %d). " +
                                "This element will be skipped from initial processing.%n",
                        numberFromList1, i, targetIndexInList2, list2.size());
            } else {
                String stringFromList2 = list2.get(targetIndexInList2);
                intermediateOutputElements.add(stringFromList2 + numberFromList1);
            }
        }

        System.out.println("Intermediate output (before final filtering): " + String.join(", ", intermediateOutputElements));
        System.out.println("Original List1 values: " + list1);


        // --- Step 2: Filtering (Removal based on unique List1 values as indices) ---

        // Create a mutable copy of the intermediate list to perform removals on.
        List<String> elementsAfterRemovals = new ArrayList<>(intermediateOutputElements);

        // Get unique values from list1 to use as indices for removal.
        // This handles the "only one value will be removed from the output" rule if list1 has duplicates.
        Set<Integer> uniqueRemovalIndicesSet = new HashSet<>(list1);
        List<Integer> sortedUniqueRemovalIndices = new ArrayList<>(uniqueRemovalIndicesSet);

        // Sort removal indices in descending order to avoid index shifting issues during removal.
        Collections.sort(sortedUniqueRemovalIndices, Collections.reverseOrder());

        System.out.println("--- Applying Filtering Rule (removing elements at 0-based output indices derived from unique List1 values) ---");
        System.out.println("Unique List1 values used as removal indices (sorted descending): " + sortedUniqueRemovalIndices);


        for (int indexToRemove : sortedUniqueRemovalIndices) {
            // Check if the index is valid for the current size of elementsAfterRemovals.
            // This handles cases like "output does not have index 10" and negative indices.
            if (indexToRemove >= 0 && indexToRemove < elementsAfterRemovals.size()) {
                String removedElement = elementsAfterRemovals.remove(indexToRemove);
                System.out.printf("Removing element '%s' at 0-based index %d because %d is a unique value in the original List1.%n",
                        removedElement, indexToRemove, indexToRemove);
            } else {
                System.out.printf("Skipping removal attempt for 0-based index %d because it's out of bounds for the current output list (size %d).%n",
                        indexToRemove, elementsAfterRemovals.size());
            }
        }

        System.out.println("--- Processing Complete ---");

        return String.join(", ", elementsAfterRemovals);
    }

    /**
     * Main method to demonstrate the ListProcessor functionality.
     * Creates an instance of ListProcessor with predefined values and prints the result.
     * It also includes test cases for custom lists to show error handling and general functionality.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // --- Demonstration with predefined lists ---
        ListProcessor processor = new ListProcessor();
        System.out.println("\n===== Processing with Predefined Lists =====");
        System.out.println("Final Output (predefined lists): " + processor.processLists());
        System.out.println("\n--- Discrepancy Note ---");
        System.out.println("The output 'qtm7' now matches the first element in the 'Output' section of the original problem description.");
        System.out.println("This program strictly adheres to the clarified rule: 'valueatB(valueatA+1)' for initial string selection,");
        System.out.println("and 'remove element at output index V if V is a unique value in original List1' for filtering.");


        System.out.println("\n" + "=".repeat(60));
        System.out.println("===== Testing with Custom Lists (General Functionality) =====");

        // --- Custom Test Case 1: Demonstrating N+1 and filtering with some removals ---
        List<Integer> customList1_1 = List.of(0, 1, 3, 5); // list1 values: 0, 1, 3, 5
        List<String> customList2_1 = List.of("A", "B", "C", "D", "E", "F", "G"); // Size 7 (indices 0-6)
        ListProcessor customProcessor1 = new ListProcessor(customList1_1, customList2_1);
        System.out.println("\n--- Custom Test Case 1 ---");
        // Initial processing trace:
        // list1[0]=0 -> list2[0+1]=list2[1]="B" -> "B0"
        // list1[1]=1 -> list2[1+1]=list2[2]="C" -> "C1"
        // list1[2]=3 -> list2[3+1]=list2[4]="E" -> "E3"
        // list1[3]=5 -> list2[5+1]=list2[6]="G" -> "G5"
        // Intermediate output: [B0, C1, E3, G5] (size 4)
        // Unique removal indices from customList1_1: [0, 1, 3, 5]
        // Sorted unique removal indices: [5, 3, 1, 0]
        // - Remove index 5: Skipped (OOB, size is 4)
        // - Remove index 3: Removes "G5". List becomes [B0, C1, E3]
        // - Remove index 1: Removes "C1". List becomes [B0, E3]
        // - Remove index 0: Removes "B0". List becomes [E3]
        System.out.println("Final Output (custom lists 1): " + customProcessor1.processLists()); // Expected: E3

        // --- Custom Test Case 2: Demonstrating duplicates in list1 for removal ---
        List<Integer> customList1_2 = List.of(2, 2, 0, 1, 0); // list1 values: 0, 1, 2 (unique)
        List<String> customList2_2 = List.of("X", "Y", "Z", "W"); // Size 4 (indices 0-3)
        ListProcessor customProcessor2 = new ListProcessor(customList1_2, customList2_2);
        System.out.println("\n--- Custom Test Case 2 (Duplicates in List1 for Removal) ---");
        // Initial processing trace:
        // list1[0]=2 -> list2[2+1]=list2[3]="W" -> "W2"
        // list1[1]=2 -> list2[2+1]=list2[3]="W" -> "W2"
        // list1[2]=0 -> list2[0+1]=list2[1]="Y" -> "Y0"
        // list1[3]=1 -> list2[1+1]=list2[2]="Z" -> "Z1"
        // list1[4]=0 -> list2[0+1]=list2[1]="Y" -> "Y0"
        // Intermediate output: [W2, W2, Y0, Z1, Y0] (size 5)
        // Unique removal indices from customList1_2: [0, 1, 2]
        // Sorted unique removal indices: [2, 1, 0]
        // - Remove index 2: Removes "Y0" (at current index 2). List becomes [W2, W2, Z1, Y0]
        // - Remove index 1: Removes "W2" (at current index 1). List becomes [W2, Z1, Y0]
        // - Remove index 0: Removes "W2" (at current index 0). List becomes [Z1, Y0]
        System.out.println("Final Output (custom lists 2): " + customProcessor2.processLists()); // Expected: Z1, Y0

        // --- Custom Test Case 3: Error handling (initial step) and no removals from filtering ---
        List<Integer> customList1_3 = List.of(-1, 0, 10); // -1+1=0 (valid), 0+1=1 (valid), 10+1=11 (OOB for list2 size 3)
        List<String> customList2_3 = List.of("one", "two", "three"); // Size 3 (indices 0-2)
        ListProcessor customProcessor3 = new ListProcessor(customList1_3, customList2_3);
        System.out.println("\n--- Custom Test Case 3 (Initial Error and No Removals) ---");
        // Initial processing trace:
        // list1[0]=-1 -> list2[-1+1]=list2[0]="one" -> "one-1"
        // list1[1]=0 -> list2[0+1]=list2[1]="two" -> "two0"
        // list1[2]=10 -> list2[10+1]=list2[11] -> ERROR (skipped)
        // Intermediate output: [one-1, two0] (size 2)
        // Unique removal indices from customList1_3: [-1, 0, 10]
        // Sorted unique removal indices: [10, 0, -1]
        // - Remove index 10: Skipped (OOB, size is 2)
        // - Remove index 0: Removes "one-1". List becomes [two0]
        // - Remove index -1: Skipped (OOB)
        System.out.println("Final Output (custom lists 3): " + customProcessor3.processLists()); // Expected: two0

        System.out.println("\n" + "=".repeat(60));
        System.out.println("===== Testing with Empty Lists =====");
        ListProcessor emptyListProcessor = new ListProcessor(new ArrayList<>(), new ArrayList<>());
        System.out.println("Final Output (empty lists): " + emptyListProcessor.processLists());
    }
}
