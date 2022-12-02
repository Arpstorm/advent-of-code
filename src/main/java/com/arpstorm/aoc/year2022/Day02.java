package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.List;

public class Day02 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day02.txt");

        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }

    public static int solvePart1(List<String> input) {
        int score = 0;
        for (String line : input) {
            if (line.charAt(2) == 'X') {
                score += 1;
                if (line.charAt(0) == 'A') {
                    score += 3;
                } else if (line.charAt(0) == 'C') {
                    score += 6;
                }
            } else if (line.charAt(2) == 'Y') {
                score += 2;
                if (line.charAt(0) == 'A') {
                    score += 6;
                } else if (line.charAt(0) == 'B') {
                    score += 3;
                }
            } else if (line.charAt(2) == 'Z') {
                score += 3;
                if (line.charAt(0) == 'B') {
                    score += 6;
                } else if (line.charAt(0) == 'C') {
                    score += 3;
                }
            }
        }
        return score;
    }

    public static int solvePart2(List<String> input) {
        int score = 0;
        for (String line : input) {
            if (line.charAt(2) == 'X') {
                if (line.charAt(0) == 'A') {
                    score += 3;
                } else if (line.charAt(0) == 'B') {
                    score += 1;
                } else if (line.charAt(0) == 'C') {
                    score += 2;
                }
            } else if (line.charAt(2) == 'Y') {
                score += 3;
                if (line.charAt(0) == 'A') {
                    score += 1;
                } else if (line.charAt(0) == 'B') {
                    score += 2;
                } else if (line.charAt(0) == 'C') {
                    score += 3;
                }
            } else if (line.charAt(2) == 'Z') {
                score += 6;
                if (line.charAt(0) == 'A') {
                    score += 2;
                } else if (line.charAt(0) == 'B') {
                    score += 3;
                } else if (line.charAt(0) == 'C') {
                    score += 1;
                }
            }
        }
        return score;
    }
}
