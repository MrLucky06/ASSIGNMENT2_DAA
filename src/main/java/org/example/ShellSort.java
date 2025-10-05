package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShellSort {

    public enum GapSequence {
        SHELL, KNUTH, SEDGEWICK
    }

    public static void sort(int[] arr, GapSequence seq, PerformanceTracker tracker) {
        if (arr == null) throw new IllegalArgumentException("Array is null");
        int n = arr.length;
        if (tracker == null) tracker = new PerformanceTracker();

        int[] gaps = generateGaps(n, seq);
        for (int gap : gaps) {
            for (int i = gap; i < n; i++) {
                tracker.incrementArrayAccesses(); // read arr[i] into temp
                int temp = arr[i];
                int j = i;
                tracker.incrementComparisons(); // first comparison in while check
                while (j >= gap && compare(arr[j - gap], temp, tracker) > 0) {
                    tracker.incrementArrayAccesses(); // arr[j] write
                    arr[j] = arr[j - gap];
                    tracker.incrementSwaps(); // count shift as swap-equivalent
                    j -= gap;
                    if (j >= gap) tracker.incrementComparisons();
                }
                tracker.incrementArrayAccesses(); // arr[j] write
                arr[j] = temp;
            }
        }
    }

    private static int compare(int a, int b, PerformanceTracker tracker) {
        tracker.incrementComparisons();
        return Integer.compare(a, b);
    }

    private static int[] generateGaps(int n, GapSequence seq) {
        if (n <= 1) return new int[]{};
        switch (seq) {
            case KNUTH:
                return knuthGaps(n);
            case SEDGEWICK:
                return sedgewickGaps(n);
            case SHELL:
            default:
                return shellGaps(n);
        }
    }

    private static int[] shellGaps(int n) {
        List<Integer> list = new ArrayList<>();
        for (int gap = n / 2; gap > 0; gap /= 2) list.add(gap);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] knuthGaps(int n) {
        List<Integer> list = new ArrayList<>();
        int k = 1;
        int gap = (int) ((Math.pow(3, k) - 1) / 2);
        while (gap < n) {
            list.add(0, gap);
            k++;
            gap = (int) ((Math.pow(3, k) - 1) / 2);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] sedgewickGaps(int n) {
        List<Integer> list = new ArrayList<>();
        int k = 0;
        while (true) {
            int g1 = (int) (9 * Math.pow(4, k) - 9 * Math.pow(2, k) + 1);
            int g2 = (int) (Math.pow(4, k) - 3 * Math.pow(2, k) + 1);
            if (g1 < n && g1 > 0) list.add(0, g1);
            if (g2 < n && g2 > 0) list.add(0, g2);
            if (g1 >= n && g2 >= n) break;
            k++;
            if (k > 30) break;
        }
        return list.stream().distinct().filter(x -> x > 0).mapToInt(Integer::intValue).toArray();
    }

    public static boolean isSorted(int[] arr) {
        if (arr == null) return true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}
