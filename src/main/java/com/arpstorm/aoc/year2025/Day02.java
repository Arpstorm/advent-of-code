package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;

import java.util.List;

public class Day02 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2025/input.day02.txt");

        long part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(List<String> input) {
        long sum = 0;
        String line = input.get(0);
        String[] ranges = line.split(",");
        for (String range : ranges) {
            String[] firstLast = range.split("-");
            long first = Long.parseLong(firstLast[0]);
            long last = Long.parseLong(firstLast[1]);
            for (long id = first; id <= last; id++) {
                String idString = String.valueOf(id);
                int digits = idString.length();
                if (digits % 2 == 0) {
                    String left = idString.substring(0, digits / 2);
                    String right = idString.substring(digits / 2, digits);
                    if (left.equals(right)) {
                        sum = sum + Long.parseLong(idString);
                    }
                }
            }
        }
        return sum;
    }

    public static long solvePart2(List<String> input) {
        long sum = 0;
        String line = input.get(0);
        String[] ranges = line.split(",");
        for (String range : ranges) {
            String[] firstLast = range.split("-");
            long first = Long.parseLong(firstLast[0]);
            long last = Long.parseLong(firstLast[1]);
            for (long id = first; id <= last; id++) {
                String idString = String.valueOf(id);
                int digits = idString.length();
                if (isRepeatedSequence(idString, digits)) {
                    sum = sum + Long.parseLong(idString);
                }
            }
        }
        return sum;
    }

    private static boolean isRepeatedSequence(String idString, int digits) {
        for (int digit = 1; digit < digits; digit++) {
            if (isRepeatedSequenceOfLength(idString, digits, digit)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRepeatedSequenceOfLength(String idString, int nrDigits, int digit) {
        if (nrDigits % digit != 0) {
            return false;
        }
        String startSequence = idString.substring(0, digit);
        for (int n = 1; n < nrDigits / digit; n++) {
            if (!startSequence.equals(idString.substring(n * digit, (n + 1) * digit))) {
                return false;
            }
        }
        return true;
    }
}
