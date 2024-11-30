package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 {

    private static Integer[] pivotCycles = new Integer[] {20, 60, 100, 140, 180, 220};
    private static List<Integer> signalStrength = new ArrayList<>();

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day10.txt");

        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }

    public static int solvePart2(List<String> lines) {
        int spriteCenter = 1;
        int lineIndex = 0;
        for (int cycle = 1; cycle < Integer.MAX_VALUE; cycle++) {
            if (lineIndex >= lines.size()) {
                break;
            }
            //
            int pixelDrawn = (cycle - 1) % 40;
            if (pixelDrawn == spriteCenter - 1 || pixelDrawn  == spriteCenter || pixelDrawn  == spriteCenter + 1) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }
            if (cycle % 40 == 0) {
                System.out.println();
            }
            if (cycle == 199) {
                System.out.print("");
            }
            String line = lines.get(lineIndex);
            if (line.equals("noop")) {
            } else if (line.startsWith("addx")) {
                cycle += 1;
                //
                pixelDrawn = (cycle - 1) % 40;
                if (pixelDrawn == spriteCenter - 1|| pixelDrawn == spriteCenter || pixelDrawn == spriteCenter + 1) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
                if (cycle % 40 == 0) {
                    System.out.println();
                }
                spriteCenter = spriteCenter + Integer.parseInt(line.split(" ")[1]);
            } else {
                throw new RuntimeException();
            }
            lineIndex++;
        }
        return signalStrength.stream().mapToInt(Integer::intValue).sum();
    }
    public static int solvePart1(List<String> lines) {
        int reg = 1;
        int lineIndex = 0;
        for (int cycle = 1; cycle < Integer.MAX_VALUE; cycle++) {
            if (lineIndex >= lines.size()) {
                break;
            }
            if (Arrays.asList(pivotCycles).contains(cycle)) {
                signalStrength.add(reg * cycle);
            }
            String line = lines.get(lineIndex);
            if (line.equals("noop")) {
            } else if (line.startsWith("addx")) {
                cycle += 1;
                if (Arrays.asList(pivotCycles).contains(cycle)) {
                    signalStrength.add(reg * cycle);
                }
                reg = reg + Integer.parseInt(line.split(" ")[1]);
            } else {
                throw new RuntimeException();
            }
            lineIndex++;
        }
        return signalStrength.stream().mapToInt(Integer::intValue).sum();
    }
}
