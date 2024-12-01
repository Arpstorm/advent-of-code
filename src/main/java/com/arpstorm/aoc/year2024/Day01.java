package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 {

    private static final List<Integer> group1 = new ArrayList<>();
    private static final List<Integer> group2 = new ArrayList<>();

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day01.txt");
        initGroups(input);

        int part1 = solvePart1();
        int part2 = solvePart2();

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static void initGroups(List<String> input) {
        for (String line : input) {
            String[] number = line.split(" {3}");
            group1.add(Integer.parseInt(number[0]));
            group2.add(Integer.parseInt(number[1]));
        }
    }

    public static int solvePart1() {
        List<Integer> distance = new ArrayList<>();
        group1.sort(Integer::compareTo);
        group2.sort(Integer::compareTo);
        for (int i = 0; i < group1.size(); i++) {
            distance.add(Math.abs(group2.get(i) - group1.get(i)));
        }
        return distance.stream().mapToInt(Integer::intValue).sum();
    }

    public static int solvePart2() {
        List<Integer> similarity = new ArrayList<>();
        for (Integer integer : group1) {
            similarity.add(Collections.frequency(group2, integer) * integer);
        }
        return similarity.stream().mapToInt(Integer::intValue).sum();
    }
}
