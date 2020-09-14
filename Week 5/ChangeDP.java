import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        int[] arr = new int[m+1];
        int[] coins = {1,3,4};
        arr[0] = 0;
        for(int i = 1; i <= m; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        for(int i = 1; i <= m; i++) {
            for(int j : coins) {
                if(j <= i) {
                    int temp = arr[i-j];
                    if(temp != Integer.MAX_VALUE && temp+1 < arr[i]) {
                        arr[i] = temp+1;
                    }
                }
            }
        }
        return arr[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

