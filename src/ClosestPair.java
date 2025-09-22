import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

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

    public static double findClosestPair(Point[] points) {
        Arrays.sort(points, new XComparator());
        return closestPair(points, 0, points.length - 1);
    }

    public static double closestPair(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }

        int mid = left + (right - left) / 2;
        Point midPoint = points[mid];

        double dL = closestPair(points, left, mid);
        double dR = closestPair(points, mid + 1, right);
        double d = Math.min(dL, dR);

        Point[] strip = new Point[right - left + 1];
        int j = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < d) {
                strip[j++] = points[i];
            }
        }

        return Math.min(d, stripClosest(strip, j, d));
    }

    private static double bruteForce(Point[] points, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i < right; ++i) {
            for (int j = i + 1; j <= right; ++j) {
                double dist = distance(points[i], points[j]);
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