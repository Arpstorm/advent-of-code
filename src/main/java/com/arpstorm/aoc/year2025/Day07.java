package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 {

    private static Character[][] paperRolls;

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2025/input.day07.txt");

        //long part1 = solvePart1(input);
        long part2 = solvePart2(input);

        //System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(List<String> input) {
        long sum = 0;
        List<String[][]> frames = new ArrayList<>();
        String[][] grid = initGrid(input);
        for (int y = 0; y < input.size() - 1; y++) {
            int numberOfSplits = advanceTime(y, grid);
            sum += numberOfSplits;
            frames.add(deepCopy(grid));
            //drawGrid(grid);
        }
        try {
            AsciiGifExporter.exportAsciiGridGif(frames, new File("my-animation.gif"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    public static long solvePart2(List<String> input) {
        long sum = 0;
        List<String[][]> frames = new ArrayList<>();
        String[][] grid = initGrid(input);
        for (int y = 0; y < input.size() - 1; y++) {
            advanceQuantumTime(y, grid);
            frames.add(deepCopy(grid));
            //drawGrid(grid);
        }
        try {
            AsciiGifExporter.exportAsciiGridGifWithValueStrings(
                    frames,        // List<String[][]> with ".", "^", or numeric strings
                    new BigDecimal(5),      // BigDecimal
                    new File("ascii-grid-valued.gif")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int x = 0; x < grid[grid.length - 1].length; x++) {
            if (isNumeric(grid[grid.length - 1][x])) {
                sum += Long.parseLong(grid[grid.length - 1][x]);
            }
        }
        return sum;
    }

    private static void advanceQuantumTime(int y, String[][] grid) {
        for (int x = 0; x < grid[y].length; x++) {
            if (grid[y][x].equals("S")) {
                grid[y][x] = "1";
            }
            if (isNumeric(grid[y][x])) {
                long value = Long.parseLong(grid[y][x]);
                if (grid[y + 1][x].equals("^")) {
                    if (isNumeric(grid[y + 1][x - 1])) {
                        long existingLeftValue = Long.parseLong(grid[y + 1][x - 1]);
                        grid[y + 1][x - 1] = String.valueOf(existingLeftValue + value);
                    } else {
                        grid[y + 1][x - 1] = String.valueOf(value);
                    }
                    grid[y + 1][x + 1] = String.valueOf(value);
                } else {
                    if (isNumeric(grid[y + 1][x])) {
                        long existingValue = Long.parseLong(grid[y + 1][x]);
                        grid[y + 1][x] = String.valueOf(existingValue + value);
                    } else {
                        grid[y + 1][x] = String.valueOf(value);
                    }
                }
            }
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static int advanceTime(int y, String[][] grid) {
        int sum = 0;
        for (int x = 0; x < grid[y].length; x++) {
            if (grid[y][x].equals("|") || grid[y][x].equals("S")) {
                if (grid[y + 1][x].equals("^")) {
                    grid[y + 1][x - 1] = "|";
                    grid[y + 1][x + 1] = "|";
                    sum++;
                } else {
                    grid[y + 1][x] = "|";
                }
            }
        }
        return sum;
    }

    private static String[][] initGrid(List<String> input) {
        String[][] grid = new String[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = Character.toString(line.charAt(x));
            }
        }
        return grid;
    }

    private static void drawGrid(String[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static <T> T[][] deepCopy(T[][] matrix) {
        return Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }
}
