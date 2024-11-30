package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.List;

public class Day12 {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static int maxX;
    private static int maxY;
    private static int highestRecursion;

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day12.txt");

        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }

    public static int solvePart1(List<String> lines) {
        Integer[][] heightGrid =  new Integer[lines.get(0).length()][lines.size()];
        String[][] trace =  new String[lines.get(0).length()][lines.size()];
        Coordinates start = null;
        Coordinates end = null;
        maxY = lines.size();
        maxX = lines.get(0).length();
        for (int y = 0; y < maxY; y++) {
            String line = lines.get(y);
            for (int x = 0; x < maxX; x++) {
                trace[x][y] = ".";
                String symbol = String.valueOf(line.charAt(x));
                if (symbol.equals("S")) {
                    heightGrid[x][y] = ALPHABET.indexOf("a");
                    start = new Coordinates(x, y);
                } else if (symbol.equals("E")) {
                    heightGrid[x][y] = ALPHABET.indexOf("z");
                    end = new Coordinates(x, y);
                } else {
                    heightGrid[x][y] = ALPHABET.indexOf(symbol);
                }
            }
        }

        takeStep(heightGrid, trace, start, end, 0);
        return 0;
    }

    private static void drawGrid(String[][] trace, int currentStep) {
        System.out.println("== Step " + currentStep + " ==");
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                System.out.print(trace[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void takeStep(Integer[][] path, String[][] trace, Coordinates position, Coordinates end, int currentStep) {
        int x = position.getX();
        int y = position.getY();
        if (currentStep > highestRecursion) {
            highestRecursion = currentStep;
            System.out.println("Highest recursion depth: " + currentStep);
            drawGrid(trace, currentStep);
            System.out.println();
        }
        //drawGrid(trace, currentStep);
        if (x == end.getX() && y == end.getY()) {
            drawGrid(trace, currentStep);
            System.out.println("Finish! " + currentStep + " steps");
            System.out.println();
            return;
        }
        int deltaX = end.getX() - x;
        int deltaY = end.getY() - y;
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                stepRight(x, y, trace, path, end, currentStep);
                if (deltaY > 0) {
                    stepDown(x, y, trace, path, end, currentStep);
                    stepUp(x, y, trace, path, end, currentStep);
                } else {
                    stepUp(x, y, trace, path, end, currentStep);
                    stepDown(x, y, trace, path, end, currentStep);
                }
                stepLeft(x, y, trace, path, end, currentStep);
            } else {
                stepLeft(x, y, trace, path, end, currentStep);
                if (deltaY > 0) {
                    stepDown(x, y, trace, path, end, currentStep);
                    stepUp(x, y, trace, path, end, currentStep);
                } else {
                    stepUp(x, y, trace, path, end, currentStep);
                    stepDown(x, y, trace, path, end, currentStep);
                }
                stepRight(x, y, trace, path, end, currentStep);
            }
        } else {
            if (deltaY > 0) {
                stepDown(x, y, trace, path, end, currentStep);
                if (deltaX > 0) {
                    stepRight(x, y, trace, path, end, currentStep);
                    stepLeft(x, y, trace, path, end, currentStep);
                } else {
                    stepLeft(x, y, trace, path, end, currentStep);
                    stepRight(x, y, trace, path, end, currentStep);
                }
                stepUp(x, y, trace, path, end, currentStep);
            } else {
                stepUp(x, y, trace, path, end, currentStep);
                if (deltaX > 0) {
                    stepRight(x, y, trace, path, end, currentStep);
                    stepLeft(x, y, trace, path, end, currentStep);
                } else {
                    stepLeft(x, y, trace, path, end, currentStep);
                    stepRight(x, y, trace, path, end, currentStep);
                }
                stepDown(x, y, trace, path, end, currentStep);
            }
        }
    }

    private static void stepRight(int x, int y, String[][] trace, Integer[][] path, Coordinates end, int currentStep) {
        if (x + 1 < maxX && trace[x + 1][y].equals(".") && (path[x + 1][y] == path[x][y] || path[x + 1][y] == path[x][y] + 1)) {
            trace[x][y] = ">";
            takeStep(deepCopy(path), deepCopy(trace), new Coordinates(x + 1, y), end, currentStep + 1);
        }
    }

    private static void stepLeft(int x, int y, String[][] trace, Integer[][] path, Coordinates end, int currentStep) {
        if (x - 1 >= 0 && trace[x - 1][y].equals(".") && (path[x - 1][y] == path[x][y] || path[x - 1][y] == path[x][y] + 1)) {
            trace[x][y] = "<";
            takeStep(deepCopy(path), deepCopy(trace), new Coordinates(x - 1, y), end, currentStep + 1);
        }
    }

    private static void stepUp(int x, int y, String[][] trace, Integer[][] path, Coordinates end, int currentStep) {
        if (y - 1 >= 0 && trace[x][y - 1].equals(".") && (path[x][y - 1] == path[x][y] || path[x][y - 1] == path[x][y] + 1)) {
            trace[x][y] = "^";
            takeStep(deepCopy(path), deepCopy(trace), new Coordinates(x, y - 1), end, currentStep + 1);
        }
    }

    private static void stepDown(int x, int y, String[][] trace, Integer[][] path, Coordinates end, int currentStep) {
        if (y + 1 < maxY && trace[x][y + 1].equals(".") && (path[x][y + 1] == path[x][y] || path[x][y + 1] == path[x][y] + 1)) {
            trace[x][y] = "v";
            takeStep(deepCopy(path), deepCopy(trace), new Coordinates(x, y + 1), end, currentStep + 1);
        }
    }

    private static <T> T[][] deepCopy(T[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    public static long solvePart2(List<String> lines) {
        return 0;
    }
}
