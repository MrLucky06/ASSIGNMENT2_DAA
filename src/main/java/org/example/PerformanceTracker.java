package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long startTimeNs = 0;
    private long endTimeNs = 0;

    public void startTimer() { startTimeNs = System.nanoTime(); }
    public void stopTimer() { endTimeNs = System.nanoTime(); }

    public long getElapsedNs() { return endTimeNs - startTimeNs; }
    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }

    public void incrementComparisons() { comparisons++; }
    public void incrementSwaps() { swaps++; }
    public void incrementArrayAccesses() { arrayAccesses++; }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        startTimeNs = 0;
        endTimeNs = 0;
    }

    public String toCsvLine(String algoName, int n, String distribution) {
        return String.format(Locale.ROOT, "%s,%d,%s,%d,%d,%d,%d",
                algoName, n, distribution, getElapsedNs(), getComparisons(), getSwaps(), getArrayAccesses());
    }

    public static void appendCsv(String path, String header, String line) {
        boolean writeHeader = false;
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            if (writeHeader) pw.println(header);
            pw.println(line);
        } catch (IOException e) {
            System.err.println("Failed write CSV: " + e.getMessage());
        }
    }
}
