import java.util.Random;
import java.util.Arrays;

public class SortVerification {

    private static final Random RANDOM = new Random();

    public static boolean sorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    public static double bruteforcecp(clsePair.Point[] points) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = clsePair.distance(points[i], points[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }return min;
    }
    public static void main(String[] args) {

        int arrsze = 10000;
        int[] origarr = new int[arrsze];
        for (int i = 0; i < arrsze; i++) {
            origarr[i] = RANDOM.nextInt(10000);
        }
        int pntcnt = 1000;
        clsePair.Point[] origpntarr = new clsePair.Point[pntcnt];
        for (int i = 0; i < pntcnt; i++) {
            origpntarr[i] = new clsePair.Point(RANDOM.nextDouble() * 1000, RANDOM.nextDouble() * 1000);
        }
        boolean testpass = true;
        //Тест merge sort
        int[] mergesrtarr = Arrays.copyOf(origarr, arrsze);
        mergeSorting.sort(mergesrtarr);
        if (!sorted(mergesrtarr)) {
            testpass = false;
        }
        // Тест quick sort
        int[] quicksrtarr = Arrays.copyOf(origarr, arrsze);
        quickSorting.sort(quicksrtarr);
        if (!sorted(quicksrtarr)) {
            testpass = false;
        }
        // Тест deterministic seletc
        int[] selectarr = Arrays.copyOf(origarr, arrsze);
        int k = RANDOM.nextInt(arrsze);
        int slctElmnt = DeterministicSelect.select(selectarr, k);
        Arrays.sort(origarr);
        int truElmnt = origarr[k];
        if (slctElmnt != truElmnt) {
            testpass = false;
        }
        // Тест close pair
        clsePair.Point[] closestPairArray = Arrays.copyOf(origpntarr, pntcnt);
        double fnddist = clsePair.findClosestPair(closestPairArray);
        double truDist = bruteforcecp(origpntarr);
        if (Math.abs(fnddist - truDist) > 1e-9) {
            testpass = false;
        }
        if (testpass) {
            System.out.println("All sorts work.");
        } else {
            System.out.println("Some sorts failed.");
        }
    }
}