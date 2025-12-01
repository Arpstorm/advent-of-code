package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;

import java.util.List;

public class Day01 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2025/input.day01.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int pointer = 50;
        int counter = 0;
        for (String line : input) {
            if (line.startsWith("R")) {
                pointer = Math.floorMod(pointer + Integer.parseInt(line.substring(1)), 100);
            } else if (line.startsWith("L")) {
                pointer = Math.floorMod(pointer - Integer.parseInt(line.substring(1)), 100);
            } else {
                throw new RuntimeException("Unexpected Input");
            }
            if (pointer == 0) {
                counter++;
            }
        }
        return counter;
    }

    public static int solvePart2(List<String> input) {
        int pointer = 50;
        int counter = 0;
        for (String line : input) {
            int amount = Integer.parseInt(line.substring(1));
            if (line.startsWith("R")) {
                for (int i = 0; i < amount; i++) {
                    pointer = Math.floorMod(pointer + 1, 100);
                    if (pointer == 0) {
                        counter++;
                    }
                }
            } else if (line.startsWith("L")) {
                for (int i = 0; i < amount; i++) {
                    pointer = Math.floorMod(pointer - 1, 100);
                    if (pointer == 0) {
                        counter++;
                    }
                }
            } else {
                throw new RuntimeException("Unexpected Input");
            }
        }
        return counter;
    }
}
