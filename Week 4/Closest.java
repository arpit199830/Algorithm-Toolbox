import java.io.*;
import java.util.*;
import java.util.Random;
import static java.lang.Math.*;

public class Closest {
    private static Random random = new Random();

    private static int[] partition3(Point[] a, int l, int r, boolean xsort) {
        if (xsort) {
            int x = a[l]*x;
            for (int i = l + 1; i <= r; i++) {
                if (a[i].x < x) {
                    Point temp = a[i];
                    a[i] = a[l];
                    a[l] = temp;
                    l++;
                } else if (a[i].x > x) {
                    Point temp = a[i];
                    a[i] = a[r];
                    a[r] = temp;
                    r--;
                    i--;
                }
            }
        }
        else {
            int y = a[l]*y;
            for (int i = l + 1; i <= r; i++) {
                if (a[i].y < y) {
                    Point temp = a[i];
                    a[i] = a[l];
                    a[l] = temp;
                    l++;
                } else if (a[i].y > y) {
                    Point temp = a[i];
                    a[i] = a[r];
                    a[r] = temp;
                    r--;
                    i--;
                }
            }
        }
        return new int[]{l, r};
    }
    private static void randomizedQuickSort(Point[] a, int l, int r, boolean x) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        Point t = a[l];
        a[l] = a[k];
        a[k] = t;
        int[] m = partition3(a, l, r, x);
        randomizedQuickSort(a, l, m[0]-1, x);
        randomizedQuickSort(a, m[1]+1, r, x);
    }

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }
    static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }

    static double brute(Point[] points, int lo, int hi) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = lo; i < hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                double dist = distance(points[i], points[j]);
                if(min > dist) {
                    min = dist;
                }
            }
        }
        return min;
    }

    static double centerMin(Point[] points, double gap, double midLine) {
        int mid = points.length/2;
        int l = mid, r = mid;
        for(int i = 0; i <= mid; i++) {
            if(Math.abs(points[i].x - midLine) <= gap) {
                l = i;
                break;
            }
        }
        for(int i = points.length-1; i > mid; i--) {
            if(Math.abs(points[i].x - mid) <= gap) {
                r = i;
                break;
            }
        }
        return brute(points, l, r);
    }

    static double divConq(Point[] points, int lo, int hi) {
        if((hi - lo) <= 3) {
            return brute(points, lo, hi);
        }
        int mid = lo + (hi - lo)/2;
        double leftMin = divConq(points, lo, mid);
        double rightMin = divConq(points, mid+1, hi);
        double partitionMin = Math.min(leftMin, rightMin);
        double midLine = (points[mid].x + points[mid + 1].x)/2;
        double centerRegionMin = centerMin(points, partitionMin, midLine);
        return Math.min(partitionMin, centerRegionMin);
    }

    static double minimalDistance(Point[] points) {
        //Arrays.sort(points);
        randomizedQuickSort(points, 0, points.length - 1, true);
        return divConq(points, 0, points.length - 1);
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
