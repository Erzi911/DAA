import java.util.Random;

public class RobustQuickSort {

    private static final int INSERTION_SORT_THRESHOLD = 16;
    private static final Random RANDOM = new Random();

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        while (l < r) {
            if (r - l + 1 <= INSERTION_SORT_THRESHOLD) {
                insertionSort(arr, l, r);
                return;
            }

            int p = partition(arr, l, r);

            if (p - l < r - p) {
                quickSort(arr, l, p - 1);
                l = p + 1;
            } else {
                quickSort(arr, p + 1, r);
                r = p - 1;
            }
        }
    }

    private static int partition(int[] arr, int l, int r) {
        swap(arr, l + RANDOM.nextInt(r - l + 1), r);
        int pivot = arr[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void insertionSort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= l && arr[j] > key) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = key;
        }
    }
}