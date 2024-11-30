package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;

import java.util.*;

public class Day09 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day09.txt");

        long part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        long part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int result = 0;
        for (String line : input) {
            List<Long> elements = getElementsOfInterest(line, true);
            long tmp = 0;
            for (int i = elements.size() - 1; i >= 0; i--) {
                tmp = elements.get(i) + tmp;
            }
            result += tmp;
        }
        return result;
    }

    public static int solvePart2(List<String> input) {
        int result = 0;
        for (String line : input) {
            List<Long> elements = getElementsOfInterest(line, false);
            long tmp = 0;
            for (int i = elements.size() - 1; i >= 0; i--) {
                tmp = elements.get(i) - tmp;
            }
            result += tmp;
        }
        return result;
    }

    private static List<Long> getElementsOfInterest(String line, boolean lastElement) {
        String[] stringSequence = line.split(" ");
        long[] sequence = Arrays.stream(stringSequence).mapToLong(Long::parseLong).toArray();
        List<Long> elements = new ArrayList();
        while (Arrays.stream(sequence).filter(s -> s != 0L).toArray().length != 0) {
            if (lastElement) {
                elements.add(sequence[sequence.length - 1]);
            } else {
                elements.add(sequence[0]);
            }
            sequence = getDifferences(sequence);
        }
        return elements;
    }

    private static long[] getDifferences(long[] sequence) {
        long[] newSequence = new long[sequence.length - 1];
        for (int i = 0; i < sequence.length - 1; i++) {
            newSequence[i] = sequence[i + 1] - sequence[i];
        }
        return newSequence;
    }
}
