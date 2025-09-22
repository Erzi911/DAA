import java.util.Arrays;
import java.util.Random;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Index k is out of bounds");
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        // Find the pivot value using the median-of-medians algorithm
        int pivotValue = findMedianOfMedians(arr, left, right);

        // Find the index of the pivot value in the current subarray
        int pivotIndex = -1;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }

        // Partition the array around the pivot
        int pivotPosition = partition(arr, left, right, pivotIndex);

        if (k == pivotPosition) {
            return arr[k];
        } else if (k < pivotPosition) {
            return select(arr, left, pivotPosition - 1, k);
        } else {
            return select(arr, pivotPosition + 1, right, k);
        }
    }

    private static int findMedianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[(subLeft + subRight) / 2];
        }

        // Recursively find the median of the new medians array
        return select(medians, 0, medians.length - 1, medians.length / 2);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}