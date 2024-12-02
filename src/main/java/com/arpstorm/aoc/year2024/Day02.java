package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day02 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day02.txt");
        List<List<Integer>> reports = initReports(input);

        int part1 = solvePart1(reports);
        int part2 = solvePart2(reports);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static List<List<Integer>> initReports(List<String> input) {
        List<List<Integer>> reports = new ArrayList<>();
        for (String line : input) {
            List<Integer> report = new ArrayList<>();
            for (String level : line.split(" ")) {
                report.add(Integer.parseInt(level));
            }
            if (report.size() < 3) {
                throw new IllegalStateException("Invalid report");
            }
            reports.add(report);
        }
        return reports;
    }

    public static int solvePart1(List<List<Integer>> reports) {
        int safeReports = 0;
        for (List<Integer> report : reports) {
            if (isSafeReport(report)) {
                safeReports++;
            }
        }
        return safeReports;
    }

    public static int solvePart2(List<List<Integer>> reports) {
        int safeReports = 0;
        for (List<Integer> report : reports) {
            if (isSafeReport(report) || isSafeDampenedReport(report)) {
                safeReports++;
            }
        }
        return safeReports;
    }

    private static boolean isSafeDampenedReport(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> newReport = new ArrayList<>(report);
            newReport.remove(i);
            if (isSafeReport(newReport)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSafeReport(List<Integer> report) {
        for (int i = 1; i < report.size(); i++) {
            if (i < report.size() - 1) {
                int product = (report.get(i) - report.get(i - 1)) * (report.get(i + 1) - report.get(i));
                if (product <= 0) {
                    return false;
                }
            }
            int delta = Math.abs(report.get(i) - report.get(i - 1));
            if (delta < 1 || delta > 3) {
                return false;
            }
        }
        return true;
    }
}
