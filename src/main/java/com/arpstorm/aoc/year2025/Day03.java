package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;

import java.util.List;

public class Day03 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2025/input.day03.txt");

        long part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(List<String> input) {
        long sum = 0;
        for (String line : input) {
            BankJoltage bankJoltage = getBankJoltage(line);
            String result = String.valueOf(bankJoltage.first * 10 + bankJoltage.second);
            sum += Integer.parseInt(result);
        }
        return sum;
    }

    public static long solvePart2(List<String> input) {
        long sum = 0;
        for (String line : input) {
            BankJoltage2 bankJoltage2 = getBankJoltage2(line);
            StringBuilder result = new StringBuilder();
            for (int joltage : bankJoltage2.joltages) {
                result.append(joltage);
            }
            sum += Long.parseLong(result.toString());
        }
        return sum;
    }

    private static BankJoltage getBankJoltage(String line) {
        int max = 0;
        int index = -1;
        // Last battery must not become the maximum
        for (int i = 0; i < line.length() - 1; i++) {
            int joltage = Integer.parseInt(Character.toString(line.charAt(i)));
            if (joltage > max) {
                max = joltage;
                index = i;
            }
        }
        // Start from the max battery and look for the second-max to its right
        int sec = 0;
        for (int i = index + 1; i < line.length(); i++) {
            int joltage = Integer.parseInt(Character.toString(line.charAt(i)));
            if (joltage > sec) {
                sec = joltage;
            }
        }
        return new BankJoltage(max, sec);
    }

    private static BankJoltage2 getBankJoltage2(String line) {
        int nrOfBatteries = 12;
        int[] joltages = new int[nrOfBatteries];
        int index = 0;
        int startidx = 0;
        while (index < nrOfBatteries) {
            int stopIndex = line.length() - nrOfBatteries + index + 1;
            for (int i = startidx; i < line.length(); i++) {
                if (i == stopIndex) {
                    break;
                }
                int joltage = Integer.parseInt(Character.toString(line.charAt(i)));
                if (joltage > joltages[index]) {
                    joltages[index] = joltage;
                    startidx = i + 1;
                }
            }
            index++;
        }
        return new BankJoltage2(joltages);
    }

    static class BankJoltage {
        public int first;
        public int second;
        public BankJoltage(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class BankJoltage2 {
        public int[] joltages;
        public BankJoltage2(int... joltages) {
            this.joltages = joltages;
        }
    }
}
