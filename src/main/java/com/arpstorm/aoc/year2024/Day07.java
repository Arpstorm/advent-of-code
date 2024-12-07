package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day07 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day07.txt");

        long part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(List<String> input) {
        long result = 0;
        for (String line : input) {
            long testValue = Long.parseLong(line.split(": ")[0]);
            String equation = line.split(": ")[1];
            String[] operands = equation.split(" ");
            List<Long> possibleResults = new ArrayList<>();
            possibleResults.add(Long.parseLong(operands[0]));
            for (int i = 1; i < operands.length; i++) {
                int currentSize = possibleResults.size();
                for (int j = 0; j < currentSize; j++) {
                    long oldResult = possibleResults.get(j);
                    possibleResults.set(j, oldResult + Long.parseLong(operands[i]));
                    possibleResults.add(oldResult * Long.parseLong(operands[i]));
                }
            }
            for (long possibleResult : possibleResults) {
                if (possibleResult == testValue) {
                    result+= testValue;
                    break;
                }
            }
        }
        return result;
    }

    public static long solvePart2(List<String> input) {
        long result = 0;
        for (String line : input) {
            long testValue = Long.parseLong(line.split(": ")[0]);
            String equation = line.split(": ")[1];
            String[] operands = equation.split(" ");
            List<Long> possibleResults = new ArrayList<>();
            possibleResults.add(Long.parseLong(operands[0]));
            for (int i = 1; i < operands.length; i++) {
                int currentSize = possibleResults.size();
                for (int j = 0; j < currentSize; j++) {
                    long oldResult = possibleResults.get(j);
                    possibleResults.set(j, oldResult + Long.parseLong(operands[i]));
                    possibleResults.add(oldResult * Long.parseLong(operands[i]));
                    possibleResults.add(Long.parseLong(oldResult + operands[i]));
                }
            }
            for (long possibleResult : possibleResults) {
                if (possibleResult == testValue) {
                    result+= testValue;
                    break;
                }
            }
        }
        return result;
    }
}
