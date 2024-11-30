package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day11 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day11.txt");

        long part1 = solvePart(input, 2);
        System.out.println("Part 1: " + part1);

        long part2 = solvePart(input, 1000000);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart(List<String> input, int n) {
        long result = 0;
        List<List> universe = parseUniverse(input);
        List<Integer> zeroRows = getZeroRows(universe);
        List<Integer> zeroColumns = getZeroColumns(universe);
        List<Point> galaxyPositions = getGalaxyPositions(universe);
        for (Point galaxy1 : galaxyPositions) {
            for (Point galaxy2 : galaxyPositions) {
                int minX = Math.min(galaxy1.getX(), galaxy2.getX());
                int maxX = Math.max(galaxy1.getX(), galaxy2.getX());
                int minY = Math.min(galaxy1.getY(), galaxy2.getY());
                int maxY = Math.max(galaxy1.getY(), galaxy2.getY());
                if (minX != maxX || minY != maxY) {
                    long numberOfZeroColumns = 0;
                    long numberOfZeroRows = 0;
                    for (int i = minX; i < maxX; i++) {
                        if (zeroColumns.get(i) == 1) {
                            numberOfZeroColumns++;
                        }
                    }
                    for (int i = minY; i < maxY; i++) {
                        if (zeroRows.get(i) == 1) {
                            numberOfZeroRows++;
                        }
                    }
                    long distance = maxX - minX + maxY - minY + (n - 1) * (numberOfZeroRows + numberOfZeroColumns);
                    result += distance;
                }
            }
        }
        return result / 2;
    }

    private static List<Integer> getZeroColumns(List<List> universe) {
        List<Integer> zeroColumns = new ArrayList<>();
        for (int j = 0; j < universe.get(0).size(); j++) {
            boolean zeroColumn = true;
            for (int i = 0; i < universe.size(); i++) {
                List<Integer> expandedUniverseRow = universe.get(i);
                if (expandedUniverseRow.get(j) == 1) {
                    zeroColumn = false;
                    break;
                }
            }
            if (zeroColumn) {
                zeroColumns.add(1);
            } else {
                zeroColumns.add(0);
            }
        }
        return zeroColumns;
    }

    private static List<Integer> getZeroRows(List<List> universe) {
        List<Integer> zeroRows = new ArrayList<>();
        for (int i = 0; i < universe.size(); i++) {
            List<Integer> universeRow = universe.get(i);
            if (Collections.frequency(universeRow, 1) == 0) {
                zeroRows.add(1);
            } else {
                zeroRows.add(0);
            }
        }
        return zeroRows;
    }

    private static List<Point> getGalaxyPositions(List<List> universe) {
        List<Point> positions = new ArrayList<>();
        for (int i = 0; i < universe.size(); i++) {
            List<Integer> universeRow = universe.get(i);
            for (int j = 0; j < universeRow.size(); j++) {
                if (universeRow.get(j) == 1) {
                    positions.add(new Point(j, i));
                }
            }
        }
        return positions;
    }

    private static List<List> parseUniverse(List<String> input) {
        List<List> universe = new ArrayList<>();
        for (String line : input) {
            List<Integer> universeRow = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') {
                    universeRow.add(1);
                } else {
                    universeRow.add(0);
                }
            }
            universe.add(universeRow);
        }
        return universe;
    }

    @Data
    static class Point {
        private final int x;
        private final int y;
    }
}

