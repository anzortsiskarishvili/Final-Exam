package oop.finalexam.t3;

import java.util.List;

/**
 * Generic class to represent the common structure of API responses.
 * It contains a success flag, a data payload (which can be of any type T), and metadata.
 *
 * @param <T> The type of the data payload (e.g., BlogPost, List<BlogPost>, StatsData).
 */
public class ApiResponse<T> {
    private boolean success;
    private T data; // Generic type to hold different data structures (e.g., single object, list of objects)
    private MetaData meta;

    // --- Getters ---
    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public MetaData getMeta() {
        return meta;
    }

    // --- Nested Class for Metadata ---
    /**
     * Represents the metadata section of the API response, providing information
     * about the total items, limits, etc.
     */
    public static class MetaData {
        private int total;
        private int limit;
        private boolean can_add_more; // Matches JSON key 'can_add_more'

        // --- Getters ---
        public int getTotal() {
            return total;
        }

        public int getLimit() {
            return limit;
        }

        public boolean isCan_add_more() {
            return can_add_more;
        }

        // --- Setters (optional) ---
        public void setTotal(int total) {
            this.total = total;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public void setCan_add_more(boolean can_add_more) {
            this.can_add_more = can_add_more;
        }
    }

    // --- Nested Class for Statistics Data ---
    /**
     * Represents the specific data structure for site statistics,
     * mapping to the JSON returned by the '?api=stats' endpoint.
     */
    public static class StatsData {
        private int total_posts;     // Matches JSON key 'total_posts'
        private int max_posts;       // Matches JSON key 'max_posts'
        private int remaining_posts; // Matches JSON key 'remaining_posts'
        private int percentage_used; // Matches JSON key 'percentage_used'
        private boolean can_add_more; // Matches JSON key 'can_add_more'

        // --- Getters ---
        public int getTotal_posts() {
            return total_posts;
        }

        public int getMax_posts() {
            return max_posts;
        }

        public int getRemaining_posts() {
            return remaining_posts;
        }

        public int getPercentage_used() {
            return percentage_used;
        }

        public boolean isCan_add_more() {
            return can_add_more;
        }

        // --- Setters (optional) ---
        public void setTotal_posts(int total_posts) {
            this.total_posts = total_posts;
        }

        public void setMax_posts(int max_posts) {
            this.max_posts = max_posts;
        }

        public void setRemaining_posts(int remaining_posts) {
            this.remaining_posts = remaining_posts;
        }

        public void setPercentage_used(int percentage_used) {
            this.percentage_used = percentage_used;
        }

        public void setCan_add_more(boolean can_add_more) {
            this.can_add_more = can_add_more;
        }

        /**
         * Provides a user-friendly string representation of the site statistics.
         * @return A formatted string displaying statistics.
         */
        @Override
        public String toString() {
            return "Total Posts: " + total_posts + "\n" +
                    "Max Posts Allowed: " + max_posts + "\n" +
                    "Remaining Posts: " + remaining_posts + "\n" +
                    "Percentage Used: " + percentage_used + "%\n" +
                    "Can Add More Posts: " + (can_add_more ? "Yes" : "No");
        }
    }
}

