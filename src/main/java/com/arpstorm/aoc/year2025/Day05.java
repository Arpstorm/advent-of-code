package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day05 {

    private static Character[][] paperRolls;

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2025/input.day05.txt");

        long part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(List<String> input) {
        List<Range> ranges = getRanges(input);
        List<Long> ingredients = getIngredients(input);
        List<Long> freshIngredients = new ArrayList<>();
        for (Long ingredient : ingredients) {
            for (Range range : ranges) {
                if (ingredient >= range.start && ingredient <= range.end) {
                    freshIngredients.add(ingredient);
                    break;
                }
            }
        }
        return freshIngredients.size();
    }

    public static long solvePart2(List<String> input) {
        List<Range> ranges = getRanges(input);
        boolean updated = true;
        List<Range> newRanges = new ArrayList<>();
        while (updated) {
            updated = false;
            newRanges = reduceRanges(ranges);
            for (int i = 0; i < ranges.size(); i++) {
                if (ranges.size() != newRanges.size()) {
                    updated = true;
                    ranges = newRanges;
                    break;
                }
            }
        }
        long sum = 0;
        for (Range range : newRanges) {
            sum += range.end - range.start + 1;
        }
        return sum;
    }

    private static List<Range> reduceRanges(List<Range> ranges) {
        List<Range> newRanges = new ArrayList<>();
        List<Integer> mergedRanges = new ArrayList<>();
        for (int i = 0; i < ranges.size(); i++) {
            if (mergedRanges.contains(i)) {
                continue;
            }
            Range range = ranges.get(i);
            boolean overlap = false;
            for (int j = i + 1; j < ranges.size(); j++) {
                Range compareRange = ranges.get(j);
                if (range.start <= compareRange.start && range.end >= compareRange.start) {
                    if (range.end <= compareRange.end) {
                        newRanges.add(new Range(range.start, compareRange.end));
                        mergedRanges.add(j);
                        overlap = true;
                        break;
                    } else {
                        newRanges.add(new Range(range.start, range.end));
                        mergedRanges.add(j);
                        overlap = true;
                        break;
                    }
                } else if (range.start >= compareRange.start && range.start <= compareRange.end) {
                    if (range.end <= compareRange.end) {
                        newRanges.add(new Range(compareRange.start, compareRange.end));
                        mergedRanges.add(j);
                        overlap = true;
                        break;
                    } else {
                        newRanges.add(new Range(compareRange.start, range.end));
                        mergedRanges.add(j);
                        overlap = true;
                        break;
                    }
                }
            }
            if (!overlap) {
                newRanges.add(range);
            }
        }
        return newRanges;
    }

    private static List<Long> getIngredients(List<String> input) {
        List<Long> ingredients = new ArrayList<>();
        for (String line : input) {
            if (!line.contains("-") && !line.isEmpty()) {
                ingredients.add(Long.parseLong(line));
            }
        }
        return ingredients;
    }

    private static List<Range> getRanges(List<String> input) {
        List<Range> ranges = new ArrayList<>();
        for (String line : input) {
            String[] rangeCandidate = line.split("-");
            if (rangeCandidate.length > 1) {
                ranges.add(new Range(Long.parseLong(rangeCandidate[0]), Long.parseLong(rangeCandidate[1])));
            }
        }
        return ranges;
    }

    static class Range {
        long start;
        long end;
        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}
