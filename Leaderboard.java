package labmid;

import java.util.Arrays;

public class Leaderboard {
    public static void main(String[] args) {
        int[] scores = new int[10];
        int size = 5;
        scores[0] = 70;
        scores[1] = 85;
        scores[2] = 60;
        scores[3] = 95;
        scores[4] = 50;

        System.out.println("Start: " + arrayString(scores, size));

        int insertIndex = 2;
        int insertValue = 90;
        for (int i = size; i > insertIndex; i--) {
            scores[i] = scores[i - 1];
        }
        scores[insertIndex] = insertValue;
        size++;
        System.out.println("After insert 90 at index 2: " + arrayString(scores, size));

        int deleteIndex = 4;
        for (int i = deleteIndex; i < size - 1; i++) {
            scores[i] = scores[i + 1];
        }
        size--;
        System.out.println("After delete at index 4: " + arrayString(scores, size));

        scores[size] = 100;
        size++;
        System.out.println("After insert 100 at end: " + arrayString(scores, size));

        int highest = scores[0];
        int lowest = scores[0];
        for (int i = 1; i < size; i++) {
            if (scores[i] > highest) highest = scores[i];
            if (scores[i] < lowest) lowest = scores[i];
        }
        System.out.println("Highest: " + highest);
        System.out.println("Lowest: " + lowest);
    }

    static String arrayString(int[] arr, int s) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < s; i++) {
            sb.append(arr[i]);
            if (i < s - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

