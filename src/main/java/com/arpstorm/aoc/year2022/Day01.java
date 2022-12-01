package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day01.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        List<Integer> sums = deriveSums(input);
        return Collections.max(sums);
    }

    public static int solvePart2(List<String> input) {
        List<Integer> sums = deriveSums(input);
        sums.sort(Collections.reverseOrder());
        return sums.get(0) + sums.get(1) + sums.get(2);
    }

    private static List<Integer> deriveSums(List<String> lines) {
        List<Integer> sums = new ArrayList<>();
        int sum = 0;
        for (String line : lines) {
            if (line.isBlank()) {
                sums.add(sum);
                sum = 0;
            } else {
                sum = sum + Integer.parseInt(line);
            }
        }
        return sums;
    }
}
