import java.util.*;
import java.io.*;

public class Partition3 {
     private static int partition3(int W, int n, int[] A) {
        int count = 0;
        int[][] value = new int[W+1][n+1];
        for (int i = 1; i <= W; i++) {
            for (int j = 1; j <= n; j++) {
                value[i][j] = value[i][j-1];
                if (A[j-1] <= i) {
                    int temp = value[i - A[j - 1]][j - 1] + A[j - 1];
                    if (temp > value[i][j]) {
                        value[i][j] = temp;
                    }
                }
                if (value[i][j] == W) {
                    count++;
                }
            }
        }
        if (count<3) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
            sum += A[i];
        }
        if (sum%3 != 0 || n<3) {
            System.out.println(0);
        }
        else {
            System.out.println(partition3(sum/3, n, A));
        }
    }
}
