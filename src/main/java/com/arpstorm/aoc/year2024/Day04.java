package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day04.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int result = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'X') {
                    result += getMatchesAtPosition(input, i, j);
                }
            }
        }
        return result;
    }

    private static int getMatchesAtPosition(List<String> input, int i, int j) {
        int matches = 0;
        if (matchesRight(input, i, j)) matches++;
        if (matchesTopRight(input, i, j)) matches++;
        if (matchesTop(input, i, j)) matches++;
        if (matchesTopLeft(input, i, j)) matches++;
        if (matchesLeft(input, i, j)) matches++;
        if (matchesBottomLeft(input, i, j)) matches++;
        if (matchesBottom(input, i, j)) matches++;
        if (matchesBottomRight(input, i, j)) matches++;
        return matches;
    }

    private static boolean matchesRight(List<String> input, int i, int j) {
        try {
            return (input.get(i).charAt(j + 1) == 'M' && input.get(i).charAt(j + 2) == 'A' && input.get(i).charAt(j + 3) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesTopRight(List<String> input, int i, int j) {
        try {
            return (input.get(i - 1).charAt(j + 1) == 'M' && input.get(i - 2).charAt(j + 2) == 'A' && input.get(i - 3).charAt(j + 3) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesTop(List<String> input, int i, int j) {
        try {
            return (input.get(i - 1).charAt(j) == 'M' && input.get(i - 2).charAt(j) == 'A' && input.get(i - 3).charAt(j) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesTopLeft(List<String> input, int i, int j) {
        try {
            return (input.get(i - 1).charAt(j - 1) == 'M' && input.get(i - 2).charAt(j - 2) == 'A' && input.get(i - 3).charAt(j - 3) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesLeft(List<String> input, int i, int j) {
        try {
            return (input.get(i).charAt(j - 1) == 'M' && input.get(i).charAt(j - 2) == 'A' && input.get(i).charAt(j - 3) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesBottomLeft(List<String> input, int i, int j) {
        try {
            return (input.get(i + 1).charAt(j - 1) == 'M' && input.get(i + 2).charAt(j - 2) == 'A' && input.get(i + 3).charAt(j - 3) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesBottom(List<String> input, int i, int j) {
        try {
            return (input.get(i + 1).charAt(j) == 'M' && input.get(i + 2).charAt(j) == 'A' && input.get(i + 3).charAt(j) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesBottomRight(List<String> input, int i, int j) {
        try {
            return (input.get(i + 1).charAt(j + 1) == 'M' && input.get(i + 2).charAt(j + 2) == 'A' && input.get(i + 3).charAt(j + 3) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static int solvePart2(List<String> input) {
        int result = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'A') {
                    result += getCrossMatchesAtPosition(input, i, j);
                }
            }
        }
        return result;
    }

    private static int getCrossMatchesAtPosition(List<String> input, int i, int j) {
        int matches = 0;
        if ((matchesTopLeftToBottomRight(input, i, j) || matchesBottomRightToTopLeft(input, i, j))
                && (matchesBottomLeftToTopRight(input, i, j) || matchesTopRightToBottomLeft(input, i, j))) {
            matches++;
        }
        return matches;
    }

    private static boolean matchesTopLeftToBottomRight(List<String> input, int i, int j) {
        try {
            return (input.get(i - 1).charAt(j - 1) == 'M' && input.get(i + 1).charAt(j + 1) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesBottomRightToTopLeft(List<String> input, int i, int j) {
        try {
            return (input.get(i + 1).charAt(j + 1) == 'M' && input.get(i - 1).charAt(j - 1) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesBottomLeftToTopRight(List<String> input, int i, int j) {
        try {
            return (input.get(i + 1).charAt(j - 1) == 'M' && input.get(i - 1).charAt(j + 1) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean matchesTopRightToBottomLeft(List<String> input, int i, int j) {
        try {
            return (input.get(i - 1).charAt(j + 1) == 'M' && input.get(i + 1).charAt(j - 1) == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
