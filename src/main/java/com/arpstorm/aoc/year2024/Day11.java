package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Day11 {


    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day11.txt");

        long part1 = solvePart1(input.get(0));
        long part2 = solvePart2(input.get(0));

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(String input) {
        List<String> stones = Arrays.asList(input.split(" "));
        for (int i = 0; i < 25; i++) {
            stones = applyRules(stones);
        }
        return stones.size();
    }

    public static long solvePart2(String input) {
        Map<String, Long> histogram = new HashMap<>();
        for (String stone : input.split(" ")) {
            histogram.put(stone, 1L);
        }
        for (int i = 0; i < 75; i++) {
            applyRulesToHistogram(histogram);
        }
        long result = 0;
        for (String stone : histogram.keySet()) {
            result += histogram.get(stone);
        }
        return result;
    }

    private static void applyRulesToHistogram(Map<String, Long> histogram) {
        Map<String, Long> histogramCopy
                = histogram.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        for (String stone : histogramCopy.keySet()) {
            long oldCount = histogramCopy.get(stone);
            if (stone.equals("0")) {
                if (histogram.containsKey("1")) {
                    histogram.put("1", histogram.get("1") + oldCount);
                } else {
                    histogram.put("1", oldCount);
                }
            } else if (stone.length() % 2 == 0) {
                String newStone1 = String.valueOf(Long.parseLong(stone.substring(0, stone.length() / 2)));
                String newStone2 = String.valueOf(Long.parseLong(stone.substring(stone.length() / 2)));
                if (histogram.containsKey(newStone1)) {
                    histogram.put(newStone1, histogram.get(newStone1) + oldCount);
                } else {
                    histogram.put(newStone1, oldCount);
                }
                if (histogram.containsKey(newStone2)) {
                    histogram.put(newStone2, histogram.get(newStone2) + oldCount);
                } else {
                    histogram.put(newStone2, oldCount);
                }
            } else {
                String newStone = String.valueOf(Long.parseLong(stone) * 2024);
                if (histogram.containsKey(newStone)) {
                    histogram.put(newStone, histogram.get(newStone) + oldCount);
                } else {
                    histogram.put(newStone, oldCount);
                }
            }
            long count = histogram.get(stone) - oldCount;
            if (count == 0) {
                histogram.remove(stone);
            } else {
                histogram.put(stone, count);
            }
        }
    }

    private static List<String> applyRules(List<String> stones) {
        List<String> newStones = new ArrayList<>();
        for (String stone : stones) {
            if (stone.equals("0")) {
                newStones.add("1");
            } else if (stone.length() % 2 == 0) {
                newStones.add(String.valueOf(Long.parseLong(stone.substring(0, stone.length() / 2))));
                newStones.add(String.valueOf(Long.parseLong(stone.substring(stone.length() / 2))));
            } else {
                newStones.add(String.valueOf(Long.parseLong(stone) * 2024));
            }
        }
        return newStones;
    }
}
