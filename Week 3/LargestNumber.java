import java.util.*;

public class LargestNumber {
    private static boolean bigBoi(String s1, String s2) {
        return (s1+s2).compareTo(s2+s1) > 0;
    }
    private static String largestNumber(String[] a) {
        for(int i = 0; i < a.length-1; i++) {
            int swap = i;
            for(int j = i+1; j < a.length; j++) {
                if(a[swap].charAt(0) < a[j].charAt(0)) {
                    swap = j;
                }
                else if(a[swap].charAt(0) == a[j].charAt(0)) {
                    if(!bigBoi(a[swap],a[j])) {
                        swap = j;
                    }
                }
            }
            if(swap != i) {
                String temp = a[swap];
                a[swap] = a[i];
                a[i] = temp;
            }
        }
        String result = "";
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

