import java.util.*;

public class Inversions {
    private static long merge(int[] arr, int l, int m, int r) {
        long numberOfInversions = 0;
        int[] left = Arrays.copyOfRange(arr, l, m + 1); 
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1); 
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) { 
            if (left[i] <= right[j]) 
                arr[k++] = left[i++]; 
            else { 
                arr[k++] = right[j++]; 
                numberOfInversions += (m + 1) - (l + i); 
            } 
        } 
        while (i < left.length) 
            arr[k++] = left[i++];  
        while (j < right.length) 
            arr[k++] = right[j++]; 
  
        return numberOfInversions; 
    }

    private static long getNumberOfInversions(int[] a, int left, int right) {
        long numberOfInversions = 0;
        if(left < right){
            int mid = (left + right) / 2;
            numberOfInversions += getNumberOfInversions(a, left, mid);
            numberOfInversions += getNumberOfInversions(a, mid+1, right);
            numberOfInversions += merge(a, left, mid, right);
        }
        //write your code here
        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(getNumberOfInversions(a, 0, a.length-1));
    }
}

