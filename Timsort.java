import java.util.Arrays;

/**
 * Implementation of Timsort algorithm for sorting an array of integers.
 */
public class Timsort {
    // Minimum size of a run for insertion sort
    private static final int MIN_RUN = 32;

   //main method 
    public static void main(String[] args) {
        int[] array = {3, 6, 8, 10, 1, 2, 1, 9, 7, 5, 4};
        timsort(array);
        System.out.println("Ouptut: " + Arrays.toString(array)); 
    }

    /**
     * Sorts an array using Timsort.
     * @param array The array to be sorted.
     */
    public static void timsort(int[] array) {
        int n = array.length;

        // Sort small chunks using insertion sort
        for (int i = 0; i < n; i += MIN_RUN) {
            int end = Math.min(i + MIN_RUN - 1, n - 1);
            insertionSort(array, i, end);
        }

        // Merge sorted runs
        for (int size = MIN_RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                // Merge two sorted halves
                if (mid < right) {
                    merge(array, left, mid, right);
                }
            }
        }
    }

    /**
     * Sorts a portion of the array using insertion sort.
     * array: The array to be sorted.
     * left: The starting index of the portion to be sorted.
     * right: The ending index of the portion to be sorted.
     */
    private static void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            // Shift elements greater than key to the right
            while (j >= left && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            // Insert key into the correct position
            array[j + 1] = key;
        }
    }

    /**
     * Merges two sorted subarrays into one sorted array.
     *  array: The array containing the subarrays to be merged.
     *  left: The starting index of the first subarray.
     *  mid: The ending index of the first subarray.
     *  right: The ending index of the second subarray.
     */
    private static void merge(int[] array, int left, int mid, int right) {
        int len1 = mid - left + 1, len2 = right - mid;
        int[] leftArr = new int[len1];
        int[] rightArr = new int[len2];

        // Copy data to temporary arrays
        System.arraycopy(array, left, leftArr, 0, len1);
        System.arraycopy(array, mid + 1, rightArr, 0, len2);

        int i = 0, j = 0, k = left;
        // Merge the temporary arrays back into the original array
        while (i < len1 && j < len2) {
            if (leftArr[i] <= rightArr[j]) {
                array[k] = leftArr[i];
                i++;
            } else {
                array[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr
        while (i < len1) {
            array[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArr
        while (j < len2) {
            array[k] = rightArr[j];
            j++;
            k++;
        }
    }
}
