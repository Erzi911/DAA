import java.util.Arrays;
import java.util.Random;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Index k is out of bounds");
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }
        int pvt = MedOfMed(arr, l, r);
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
            return select(arr, l, pvtPstn - 1, k);
        } else {
            return select(arr, pvtPstn + 1, r, k);
        }
    }
    private static int MedOfMed(int[] arr, int l, int r) {
        int n = r - l + 1;
        if (n <= 5) {
            Arrays.sort(arr, l, r + 1);
            return arr[l + n / 2];
        }

        int numMed = (int) Math.ceil((double) n / 5);
        int[] meds = new int[numMed];
        for (int i = 0; i < numMed; i++) {
            int ll = l + i * 5;
            int rr = Math.min(ll + 4, r);
            Arrays.sort(arr, ll, rr + 1);
            meds[i] = arr[(ll + rr) / 2];
        }

        return select(meds, 0, meds.length - 1, meds.length / 2);
    }

    private static int partition(int[] arr, int l, int r, int pvtIndx) {
        int pvt = arr[pvtIndx];
        swap(arr, pvtIndx, r);

        int streIndx = l;
        for (int i = l; i < r; i++) {
            if (arr[i] < pvt) {
                swap(arr, i, streIndx);
                streIndx++;
            }
        }
        swap(arr, streIndx, r);
        return streIndx;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}