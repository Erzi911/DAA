import java.util.Random;
import java.util.Arrays;

public class SortVerification {

    private static final Random RANDOM = new Random();

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static double bruteForceClosestPair(ClosestPair.Point[] points) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = ClosestPair.distance(points[i], points[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        // --- Shared Setup ---
        int arraySize = 10000;
        int[] originalIntArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            originalIntArray[i] = RANDOM.nextInt(10000);
        }

        int pointCount = 1000;
        ClosestPair.Point[] originalPointArray = new ClosestPair.Point[pointCount];
        for (int i = 0; i < pointCount; i++) {
            originalPointArray[i] = new ClosestPair.Point(RANDOM.nextDouble() * 1000, RANDOM.nextDouble() * 1000);
        }

        boolean allTestsPassed = true;

        // --- Тест 1: Hybrid Merge Sort ---
        int[] mergeSortArray = Arrays.copyOf(originalIntArray, arraySize);
        HybridMergeSort.sort(mergeSortArray);
        if (!isSorted(mergeSortArray)) {
            allTestsPassed = false;
        }

        // --- Тест 2: Robust Quick Sort ---
        int[] quickSortArray = Arrays.copyOf(originalIntArray, arraySize);
        RobustQuickSort.sort(quickSortArray);
        if (!isSorted(quickSortArray)) {
            allTestsPassed = false;
        }

        // --- Тест 3: Deterministic Select ---
        int[] selectArray = Arrays.copyOf(originalIntArray, arraySize);
        int k = RANDOM.nextInt(arraySize);
        int selectedElement = DeterministicSelect.select(selectArray, k);
        Arrays.sort(originalIntArray);
        int trueElement = originalIntArray[k];
        if (selectedElement != trueElement) {
            allTestsPassed = false;
        }

        // --- Тест 4: Closest Pair of Points ---
        ClosestPair.Point[] closestPairArray = Arrays.copyOf(originalPointArray, pointCount);
        double foundDistance = ClosestPair.findClosestPair(closestPairArray);
        double trueDistance = bruteForceClosestPair(originalPointArray);
        if (Math.abs(foundDistance - trueDistance) > 1e-9) {
            allTestsPassed = false;
        }

        // --- Финальный результат ---
        if (allTestsPassed) {
            System.out.println("All sorts work.");
        } else {
            System.out.println("Some sorts failed.");
        }
    }
}