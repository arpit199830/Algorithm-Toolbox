import java.util.*;
import java.io.*;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
         if(dist <= tank) return 0;
        if(stops == null || stops.length == 0) return -1;
        Arrays.sort(stops);
        if(stops[stops.length - 1]+tank < dist || stops[0] > tank) return -1;
        int refuels = 0;
        int prev = 0, curr = 0;
        for(int i = 0; i < stops.length; i++) {
            if(stops[i] <= tank+curr) {
                prev = stops[i];
            } else {
                if(curr == prev) return -1;
                curr = prev;
                refuels++;
                if(curr+tank >= dist) return refuels;
                i--;
            }
        }
        return refuels+1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
