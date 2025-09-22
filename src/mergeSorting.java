public class mergeSorting {

    private static final int INSERTION_SORT_THRESHOLD = 16;

    public static void sort(int[] arr) {
        int[] buf = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, buf);
    }
    private static void mergeSort(int[] a, int l, int r, int[] buf) {
        if (r - l + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(a, l, r);
            return;
        }
        int m = (l + r) >>> 1;
        mergeSort(a, l, m, buf);
        mergeSort(a, m + 1, r, buf);
        merge(a, l, m, r, buf);
    }
    private static void merge(int[] a, int l, int m, int r, int[] buf) {
        int i = l, j = m + 1, k = l;
        System.arraycopy(a, l, buf, l, r - l + 1);
        while (i <= m && j <= r) {
            a[k++] = (buf[i] <= buf[j]) ? buf[i++] : buf[j++];
        }
        while (i <= m) a[k++] = buf[i++];
        while (j <= r) a[k++] = buf[j++];
    }
    private static void insertionSort(int[] a, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int key = a[i], j = i - 1;
            while (j >= l && a[j] > key) {
                a[j + 1] = a[j--];
            }
            a[j + 1] = key;
        }
    }
}