import java.util.Arrays;

public class DeterministicSelect {

    public static int slct(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Index k is out of bounds");
        }
        return slct(arr, 0, arr.length - 1, k);
    }
    private static int slct(int[] arr, int l, int r, int k) {
        while (l <= r) {
            if (r - l + 1 <= 5) {
                Arrays.sort(arr, l, r + 1);
                return arr[k];
            }
            int pvt = getMedofMed(arr, l, r);
            int pvtIndx = -1;
            for (int i = l; i <= r; i++) {
                if (arr[i] == pvt) {
                    pvtIndx = i;
                    break;
                }
            }

            int pvtPstn = partition(arr, l, r, pvtIndx);

            if (k == pvtPstn) {
                return arr[k];
            } else if (k < pvtPstn) {
                r = pvtPstn - 1;
            } else {
                l = pvtPstn + 1;
            }
        }return -1;
    }
    private static int getMedofMed(int[] arr, int l, int r) {
        int n = r - l + 1;
        int numGrps = (int) Math.ceil((double) n / 5);
        int[] meds = new int[numGrps];

        for (int i = 0; i < numGrps; i++) {
            int grpStrt = l + i * 5;
            int grpEnd = Math.min(grpStrt + 4, r);
            Arrays.sort(arr, grpStrt, grpEnd + 1);
            meds[i] = arr[(grpStrt + grpEnd) / 2];
        }

        return slct(meds, 0, meds.length - 1, meds.length / 2);
    }

    private static int partition(int[] arr, int l, int r, int pvtIndx) {
        int pvtVle = arr[pvtIndx];
        swap(arr, pvtIndx, r);

        int strIndx = l;
        for (int i = l; i < r; i++) {
            if (arr[i] < pvtVle) {
                swap(arr, i, strIndx);
                strIndx++;
            }
        }
        swap(arr, strIndx, r);
        return strIndx;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}