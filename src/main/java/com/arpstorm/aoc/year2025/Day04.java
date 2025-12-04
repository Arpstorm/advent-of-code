package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;

import java.util.Arrays;
import java.util.List;

public class Day04 {

    private static Character[][] paperRolls;

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2025/input.day04.txt");

        long part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(List<String> input) {
        long sum = 0;
        paperRolls = initArray(input);
        Character[][] movedPaperRolls = deepCopy(paperRolls);
        //drawGrid(paperRolls);
        for (int y = 0; y < paperRolls.length; y++) {
            for (int x = 0; x < paperRolls[0].length; x++) {
                if (paperRolls[y][x] == '@' &&
                        hasPaperRoll(x - 1, y - 1) + hasPaperRoll(x, y - 1) + hasPaperRoll(x + 1, y - 1)
                                + hasPaperRoll(x - 1, y) + hasPaperRoll(x + 1, y)
                                + hasPaperRoll(x - 1, y + 1) + hasPaperRoll(x, y + 1) + hasPaperRoll(x + 1, y + 1) < 4) {
                    movedPaperRolls[y][x] = 'x';
                    sum++;
                }
            }
        }
        //drawGrid(movedPaperRolls);
        return sum;
    }

    public static long solvePart2(List<String> input) {
        long sum = 0;
        paperRolls = initArray(input);
        //drawGrid(paperRolls);
        long tmpSum = -1;
        while (sum != tmpSum) {
            Character[][] movedPaperRolls = deepCopy(paperRolls);
            tmpSum = sum;
            for (int y = 0; y < paperRolls.length; y++) {
                for (int x = 0; x < paperRolls[0].length; x++) {
                    if (paperRolls[y][x] == '@' &&
                            hasPaperRoll(x - 1, y - 1) + hasPaperRoll(x, y - 1) + hasPaperRoll(x + 1, y - 1)
                                    + hasPaperRoll(x - 1, y) + hasPaperRoll(x + 1, y)
                                    + hasPaperRoll(x - 1, y + 1) + hasPaperRoll(x, y + 1) + hasPaperRoll(x + 1, y + 1) < 4) {
                        movedPaperRolls[y][x] = 'x';
                        sum++;
                    }
                }
            }
            paperRolls = movedPaperRolls;
        }
        //drawGrid(movedPaperRolls);
        return sum;
    }

    private static int hasPaperRoll(int x, int y) {
        if (x >= 0 && y >= 0 && x < paperRolls.length && y < paperRolls[0].length) {
            if (paperRolls[y][x] == '@') {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    public static void drawGrid(Character[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static Character[][] initArray(List<String> input) {
        Character[][] paperRolls = new Character[input.get(0).length()][input.size()];
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                paperRolls[y][x] = line.charAt(x);
            }
        }
        return paperRolls;
    }

    private static <T> T[][] deepCopy(T[][] matrix) {
        return Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }
}
