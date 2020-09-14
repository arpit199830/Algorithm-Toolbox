import java.util.*;

public class Knapsack {
    static private void printArray(int[][] a) {
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int optimalWeight(int W, int[] w) {
        int[][] arr = new int[w.length+1][W+1];
        for (int i = 0; i <= w.length; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    arr[i][j] = 0;
                }
                else {
                    arr[i][j] = arr[i-1][j];
                    if (w[i-1] <= j) {
                        arr[i][j] = Math.max(arr[i][j], arr[i-1][j - w[i-1]] + w[i-1]);
                    }
                }
            }
        }
        //printArray(arr);
        return arr[w.length][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

