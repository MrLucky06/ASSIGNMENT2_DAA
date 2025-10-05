package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = new int[]{100, 1000, 10000, 100000};
        String[] distributions = new String[]{"random", "sorted", "reversed", "nearly-sorted"};
        ShellSort.GapSequence[] seqs = new ShellSort.GapSequence[]{
                ShellSort.GapSequence.SHELL,
                ShellSort.GapSequence.KNUTH,
                ShellSort.GapSequence.SEDGEWICK
        };

        String csvPath = "shell_benchmarks.csv";
        try (FileWriter fw = new FileWriter(csvPath)) {
            fw.write("algorithm,n,distribution,time_ns,comparisons,swaps,array_accesses\n");
        } catch (IOException e) {
            System.err.println("Could not create CSV file: " + e.getMessage());
        }

        for (int n : sizes) {
            for (String dist : distributions) {
                int[] base = generateArray(n, dist);
                for (ShellSort.GapSequence seq : seqs) {
                    int[] arr = Arrays.copyOf(base, base.length);
                    PerformanceTracker tracker = new PerformanceTracker();
                    tracker.startTimer();
                    ShellSort.sort(arr, seq, tracker);
                    tracker.stopTimer();

                    if (!ShellSort.isSorted(arr)) {
                        System.err.println("Sorting failed for " + seq + " n=" + n + " dist=" + dist);
                    }

                    String line = tracker.toCsvLine("Shell-" + seq.name(), n, dist);
                    appendLine(csvPath, line);
                    System.out.printf("seq=%s n=%d dist=%s time(ms)=%.3f comps=%d swaps=%d accesses=%d%n",
                            seq.name(), n, dist, tracker.getElapsedNs() / 1e6,
                            tracker.getComparisons(), tracker.getSwaps(), tracker.getArrayAccesses());
                }
            }
        }
        System.out.println("Benchmark complete. CSV: " + csvPath);
    }

    private static int[] generateArray(int n, String distribution) {
        Random rnd = new Random(42);
        int[] a = new int[n];
        switch (distribution) {
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                break;
            case "reversed":
                for (int i = 0; i < n; i++) a[i] = n - i;
                break;
            case "nearly-sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                int swaps = Math.max(1, n / 100);
                for (int k = 0; k < swaps; k++) {
                    int i = rnd.nextInt(n);
                    int j = rnd.nextInt(n);
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
                break;
            case "random":
            default:
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt(n * 10);
                break;
        }
        return a;
    }

    private static void appendLine(String csvPath, String line) {
        try (FileWriter fw = new FileWriter(csvPath, true)) {
            fw.write(line + "\n");
        } catch (IOException e) {
            System.err.println("Failed append CSV: " + e.getMessage());
        }
    }
}
