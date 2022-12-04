package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day04 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day04.txt");

        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }

    public static int solvePart1(List<String> input) {
        int contained = 0;
        for (String pairs : input) {
            Set<Integer> sectionsA = getSections(pairs.split(",")[0]);
            Set<Integer> sectionsB = getSections(pairs.split(",")[1]);
            Set<Integer> intersection = new HashSet<>(sectionsA);
            intersection.retainAll(sectionsB);
            if (intersection.equals(sectionsA) || intersection.equals(sectionsB)) {
                contained++;
            }
        }
        return contained;
    }

    public static int solvePart2(List<String> input) {
        int overlapping = 0;
        for (String pairs : input) {
            Set<Integer> sectionsA = getSections(pairs.split(",")[0]);
            Set<Integer> sectionsB = getSections(pairs.split(",")[1]);
            Set<Integer> intersection = new HashSet<>(sectionsA);
            intersection.retainAll(sectionsB);
            if (intersection.size() > 0) {
                overlapping++;
            }
        }
        return overlapping;
    }

    private static Set<Integer> getSections(String assignment) {
        Set<Integer> section = new HashSet<>();
        int startA = Integer.parseInt(assignment.split("-")[0]);
        int endA = Integer.parseInt(assignment.split("-")[1]);
        for (int a = startA; a < endA + 1; a++) {
            section.add(a);
        }
        return section;
    }
}
