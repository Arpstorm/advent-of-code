package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.*;

public class Day12 {


    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day12.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int result = 0;
        Day10.Grid grid = new Day10.Grid(input);
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (!grid.getValue(x, y).toLowerCase().equals(grid.getValue(x, y))) {
                    List<Day10.Point> pointsVisited = new ArrayList<>();
                    List<Integer> regionEdges = new ArrayList<>();
                    searchIterator(grid, new Day10.Point(x, y), pointsVisited, regionEdges);
                    int edges = regionEdges.stream().mapToInt(Integer::intValue).sum();
                    result+= pointsVisited.size() * edges;
                    // System.out.println("Filled region " + grid.getValue(x, y) + " of " + pointsVisited.size() + " squares with " + edges + " edges");
                }
            }
        }
        return result;
    }

    private static void searchIterator(Day10.Grid grid, Day10.Point current, List<Day10.Point> pointsVisited, List<Integer> regionEdges) {
        //grid.drawGrid();
        pointsVisited.add(current);
        int edges = 0;
        String currentType = grid.getValue(current);
        grid.setValue(current, grid.getValue(current).toLowerCase());
        Day10.Point up = new Day10.Point(current.getX(), current.getY() - 1);
        Day10.Point down = new Day10.Point(current.getX(), current.getY() + 1);
        Day10.Point left = new Day10.Point(current.getX() - 1, current.getY());
        Day10.Point right = new Day10.Point(current.getX() + 1, current.getY());
        edges = edges + attemptStep(up, grid, pointsVisited, regionEdges, currentType);
        edges = edges + attemptStep(down, grid, pointsVisited, regionEdges, currentType);
        edges = edges + attemptStep(left, grid, pointsVisited, regionEdges, currentType);
        edges = edges + attemptStep(right, grid, pointsVisited, regionEdges, currentType);
        regionEdges.add(edges);
    }

    private static int attemptStep(Day10.Point dest, Day10.Grid grid, List<Day10.Point> pointsVisited, List<Integer> regionEdges, String currentType) {
        int edges = 0;
        if (dest.getX() >= 0 && dest.getY() >= 0 && dest.getX() < grid.getWidth() && dest.getY() < grid.getHeight()) {
            if (grid.getValue(dest.getX(), dest.getY()).equals(currentType)) {
                searchIterator(grid, dest, pointsVisited, regionEdges);
            } else {
                if (!grid.getValue(dest.getX(), dest.getY()).equals(currentType.toLowerCase())) {
                    edges++;
                }
            }
        } else {
            edges++;
        }
        return edges;
    }

    public static int solvePart2(List<String> input) {
        int result = 0;
        Day10.Grid grid = new Day10.Grid(input);
        Map<String, List<Day10.Point>> pointsVisitedPerType = new HashMap<>();
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (!grid.getValue(x, y).toLowerCase().equals(grid.getValue(x, y))) {
                    List<Day10.Point> pointsVisited = new ArrayList<>();
                    List<Integer> regionEdges = new ArrayList<>();
                    searchIterator(grid, new Day10.Point(x, y), pointsVisited, regionEdges);
                    String type = grid.getValue(x, y);
                    for (Day10.Point pointVisited : pointsVisited) {
                        grid.setValue(pointVisited, type + x + y);
                    }
                    pointsVisitedPerType.put(grid.getValue(x, y), pointsVisited);
                }
            }
        }
        // New edge calculation
        for (String type : pointsVisitedPerType.keySet()) {
            int edges = 0;
            for (int y = 0; y < grid.getHeight(); y++) {
                edges += countTopAndBottomEdgesInLine(grid, type, y);
            }
            for (int x = 0; x < grid.getWidth(); x++) {
                edges += countLeftAndRightEdgesInColumn(grid, type, x);
            }
            // System.out.println("Type " + type + " has " + pointsVisitedPerType.get(type).size() + " points and " + edges + " edges");
            result += pointsVisitedPerType.get(type).size() * edges;
        }
        return result;
    }

    private static int countLeftAndRightEdgesInColumn(Day10.Grid grid, String type, int x) {
        int edges = 0;
        boolean leftEdgeCounted = false;
        boolean rightEdgeCounted = false;
        for (int y = 0; y < grid.getHeight(); y++) {
            if (grid.getValue(x, y).equals(type)) {
                if (x == 0 || !grid.getValue(x - 1, y).equals(type)) {
                    if (!leftEdgeCounted) {
                        edges++;
                        leftEdgeCounted = true;
                    }
                } else {
                    leftEdgeCounted = false;
                }
                if (x == grid.getWidth() - 1 || !grid.getValue(x + 1, y).equals(type)) {
                    if (!rightEdgeCounted) {
                        edges++;
                        rightEdgeCounted = true;
                    }
                } else {
                    rightEdgeCounted = false;
                }
            } else {
                leftEdgeCounted = false;
                rightEdgeCounted = false;
            }
        }
        return edges;
    }

    private static int countTopAndBottomEdgesInLine(Day10.Grid grid, String type, int y) {
        int edges = 0;
        boolean topEdgeCounted = false;
        boolean bottomEdgeCounted = false;
        for (int x = 0; x < grid.getWidth(); x++) {
            if (grid.getValue(x, y).equals(type)) {
                if (y == 0 || !grid.getValue(x, y - 1).equals(type)) {
                    if (!topEdgeCounted) {
                        edges++;
                        topEdgeCounted = true;
                    }
                } else {
                    topEdgeCounted = false;
                }
                if (y == grid.getHeight() - 1 || !grid.getValue(x, y + 1).equals(type)) {
                    if (!bottomEdgeCounted) {
                        edges++;
                        bottomEdgeCounted = true;
                    }
                } else {
                    bottomEdgeCounted = false;
                }
            } else {
                topEdgeCounted = false;
                bottomEdgeCounted = false;
            }
        }
        return edges;
    }
}
