# Assignment 2: Shell Sort Implementation and Analysis

## Overview
This repository contains the Java implementation of Shell Sort with multiple gap sequences (Shell, Knuth, and Sedgewick) for Assignment 2: Algorithmic Analysis and Peer Code Review. The project includes performance tracking, unit tests, and a command-line interface to benchmark sorting on arrays of various sizes and distributions.

## Project Structure
src/main/java/org/example/
│ ├── ShellSort.java # Shell Sort implementation with multiple gap sequences
│ ├── PerformanceTracker.java # Tracks comparisons, swaps, and array accesses
│ └── BenchmarkRunner.java # CLI tool to run benchmarks and generate CSV
├── src/test/java/org/example/
│ └── ShellSortTest.java # Unit tests for correctness and edge cases
├── docs/
│ ├── analysis-report.pdf # Individual analysis report of partner's algorithm
│ └── performance-plots/ # Folder for performance graphs (optional)
├── pom.xml # Maven project file
└── README.md # Project overview and instructions


## Features
- **Multiple Gap Sequences:** Shell, Knuth, and Sedgewick
- **Performance Tracking:** Counts comparisons, swaps, and array accesses
- **Benchmarking:** CLI tool to run tests on arrays of different sizes and distributions (`random`, `sorted`, `reversed`, `nearly-sorted`)
- **Unit Tests:** Covers edge cases, single-element arrays, duplicates, and sorted/reverse-sorted inputs
- **CSV Output:** Benchmark results saved in `shell_benchmarks.csv` for further analysis

## Getting Started
1. Clone the repository:
```bash
git clone https://github.com/your-username/assignment2-shellsort.git
cd assignment2-shellsort

Build the project with Maven:
mvn clean install

Unit tests can be run using:
mvn test

SV Results

Benchmark results are saved in shell_benchmarks.csv, including:

algorithm — Gap sequence used

n — Array size

distribution — Input array type

time_ns — Execution time in nanoseconds

comparisons, swaps, array_accesses — Performance metrics

Notes

The docs/analysis-report.pdf contains the individual analysis of the partner’s algorithm (Heap Sort) as required by the assignment.

Performance plots can be added in docs/performance-plots/ if needed for visualization.