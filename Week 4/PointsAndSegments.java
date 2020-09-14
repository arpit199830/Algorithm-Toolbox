import java.util.Scanner;
import java.util.Random;

public class PointsAndSegments {

    
    private static Random random = new Random();

    private static int[] partition3(int[] a, int[] b, int l, int r) {
        int x = a[l];
        for(int i = l+1; i <= r; i++) {
            if(a[i] < x) {
                int temp = a[i];
                a[i] = a[l];
                a[l] = temp;
                temp = b[i];
                b[i] = b[l];
                b[l] = temp;
                l++;
            }
            else if(a[i] > x) {
                int temp = a[i];
                a[i] = a[r];
                a[r] = temp;
                temp = b[i];
                b[i] = b[r];
                b[r] = temp;
                r--;
                i--;
            }
        }
        return new int[]{l, r};
    }

    private static void randomizedQuickSort(int[] a, int[] b, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        t = b[l];
        b[l] = b[k];
        b[k] = t;
        int[] m = partition3(a, b, l, r);
        randomizedQuickSort(a, b, l, m[0]-1);
        randomizedQuickSort(a, b, m[1]+1, r);
    }

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        randomizedQuickSort(starts, ends, 0, starts.length - 1);
        for(int i = 0; i < points.length; i++) {
            int j = 0;
            int c = 0;
            while(j < starts.length && starts[j] <= points[i]) {
                if(points[i] <= ends[j]) {
                    c++;
                }
                j++;
            }
            cnt[i] = c;
        }
        return cnt;
    }
    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

