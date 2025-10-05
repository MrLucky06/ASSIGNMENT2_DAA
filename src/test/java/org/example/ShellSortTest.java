package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShellSortTest {

    @Test
    public void testEmpty() {
        int[] a = new int[0];
        PerformanceTracker t = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.SHELL, t);
        assertTrue(ShellSort.isSorted(a));
    }

    @Test
    public void testSingle() {
        int[] a = new int[]{5};
        PerformanceTracker t = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.KNUTH, t);
        assertTrue(ShellSort.isSorted(a));
        assertEquals(1, a.length);
        assertEquals(5, a[0]);
    }

    @Test
    public void testDuplicates() {
        int[] a = new int[]{3, 1, 2, 1, 3, 1};
        PerformanceTracker t = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.SEDGEWICK, t);
        assertTrue(ShellSort.isSorted(a));
    }

    @Test
    public void testAlreadySorted() {
        int[] a = new int[]{1,2,3,4,5,6,7,8,9,10};
        PerformanceTracker t = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.SHELL, t);
        assertTrue(ShellSort.isSorted(a));
    }

    @Test
    public void testReverse() {
        int[] a = new int[]{10,9,8,7,6,5,4,3,2,1};
        PerformanceTracker t = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.KNUTH, t);
        assertTrue(ShellSort.isSorted(a));
    }
}
