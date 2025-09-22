import java.util.Arrays;
import java.util.Comparator;

public class clsePair {

    public static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    public static class XComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(p1.x, p2.x);
        }
    }
    public static class YComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(p1.y, p2.y);
        }
    }
    public static double findClosestPair(Point[] pnts) {
        Arrays.sort(pnts, new XComparator());
        return clsePair(pnts, 0, pnts.length - 1);
    }

    public static double clsePair(Point[] pnts, int l, int r) {
        if (r - l <= 3) {
            return bruteForce(pnts, l, r);
        }
        int mid = l + (r - l) / 2;
        Point midPnt = pnts[mid];
        double dL = clsePair(pnts, l, mid);
        double dR = clsePair(pnts, mid + 1, r);
        double d = Math.min(dL, dR);

        Point[] strip = new Point[r - l + 1];
        int j = 0;
        for (int i = l; i <= r; i++) {
            if (Math.abs(pnts[i].x - midPnt.x) < d) {
                strip[j++] = pnts[i];
            }
        }

        return Math.min(d, stripClosest(strip, j, d));
    }
    private static double bruteForce(Point[] pnts, int l, int r) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = l; i < r; ++i) {
            for (int j = i + 1; j <= r; ++j) {
                double dist = distance(pnts[i], pnts[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }
    private static double stripClosest(Point[] strip, int size, double d) {
        double min = d;
        Arrays.sort(strip, 0, size, new YComparator());

        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                double dist = distance(strip[i], strip[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}