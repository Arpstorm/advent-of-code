package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;

import java.util.List;

public class Day01 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day01.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int sum = deriveSum(input);
        return sum;
    }

    public static int solvePart2(List<String> input) {
        int sum = replaceNumericWords(input);
        return sum;
    }

    private static int deriveSum(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            String justDigits = line.replaceAll("[^0-9]", "");
            if (justDigits.length() == 0) {

            } else if (justDigits.length() == 1) {
                int firstAndLastDigit = Integer.parseInt(String.valueOf(justDigits.charAt(0)) + justDigits.charAt(0));
                sum += firstAndLastDigit;
            } else {
                int firstAndLastDigit = Integer.parseInt(String.valueOf(justDigits.charAt(0)) + justDigits.charAt(justDigits.length() - 1));
                sum += firstAndLastDigit;
            }
        }
        return sum;
    }

    private static int replaceNumericWords(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            String firstDigit = getFirstDigit(line, false);
            String reverseLine = new StringBuilder(line).reverse().toString();
            String lastDigit = getFirstDigit(reverseLine, true);
            sum += Integer.parseInt(firstDigit + lastDigit);
        }
        return sum;
    }

    private static String getFirstDigit(String line, boolean reverse) {
        String digit = "";
        for (int i = 0; i <= line.length(); i++) {
            String subString;
            if (reverse) {
                subString = line.substring(0, i);
            } else {
                subString = new StringBuilder(line).reverse().substring(0, i);
            }
            // if digit
            if (subString.replaceAll("[^0-9]", "") != "") {
                digit = line.replaceAll("[^0-9]", "").substring(0, 1);
                break;
            }
            if (subString.contains("one")) {
                digit = "1";
                break;
            }
            if (subString.contains("two")) {
                digit = "2";
                break;
            }
            if (subString.contains("three")) {
                digit = "3";
                break;
            }
            if (subString.contains("four")) {
                digit = "4";
                break;
            }
            if (subString.contains("five")) {
                digit = "5";
                break;
            }
            if (subString.contains("six")) {
                digit = "6";
                break;
            }
            if (subString.contains("seven")) {
                digit = "7";
                break;
            }
            if (subString.contains("eight")) {
                digit = "8";
                break;
            }
            if (subString.contains("nine")) {
                digit = "9";
                break;
            }
        }
        return digit;
    }
}
