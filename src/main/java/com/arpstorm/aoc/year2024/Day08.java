package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.*;

public class Day08 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day08.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        Grid grid = new Grid(input);
        grid.drawGrid();
        Map<String, List<Point>> antennaMap = buildAntennaMap(grid);
        for (String frequency : antennaMap.keySet()) {
            List<Point> antennas = antennaMap.get(frequency);
            for (int i = 0; i < antennas.size(); i++) {
                for (int j = i + 1; j < antennas.size(); j++) {
                    int deltax = antennas.get(i).getX() - antennas.get(j).getX();
                    int deltay = antennas.get(i).getY() - antennas.get(j).getY();
                    int node1x = antennas.get(i).getX() + deltax;
                    int node1y = antennas.get(i).getY() + deltay;
                    int node2x = antennas.get(j).getX() - deltax;
                    int node2y = antennas.get(j).getY() - deltay;
                    if (insideGrid(node1x, node1y, grid)) {
                        grid.setPoint(node1x, node1y, "#");
                    }
                    if (insideGrid(node2x, node2y, grid)) {
                        grid.setPoint(node2x, node2y, "#");
                    }
                }
            }
        }
        grid.drawGrid();
        return countAntinodes(grid);
    }

    public static int solvePart2(List<String> input) {
        Grid grid = new Grid(input);
        grid.drawGrid();
        Map<String, List<Point>> antennaMap = buildAntennaMap(grid);
        for (String frequency : antennaMap.keySet()) {
            List<Point> antennas = antennaMap.get(frequency);
            for (int i = 0; i < antennas.size(); i++) {
                for (int j = i + 1; j < antennas.size(); j++) {
                    for (int k = 0; k < 100; k++) {
                        int deltax = antennas.get(i).getX() - antennas.get(j).getX();
                        int deltay = antennas.get(i).getY() - antennas.get(j).getY();
                        int node1x = antennas.get(i).getX() + k * deltax;
                        int node1y = antennas.get(i).getY() + k * deltay;
                        int node2x = antennas.get(j).getX() - k * deltax;
                        int node2y = antennas.get(j).getY() - k * deltay;
                        if (node1x >= 0 && node1x < grid.getWidth() && node1y >= 0 && node1y < grid.getHeight()) {
                            grid.setPoint(node1x, node1y, "#");
                        }
                        if (node2x >= 0 && node2x < grid.getWidth() && node2y >= 0 && node2y < grid.getHeight()) {
                            grid.setPoint(node2x, node2y, "#");
                        }
                    }
                }
            }
        }
        grid.drawGrid();
        return countAntinodes(grid);
    }

    private static int countAntinodes(Grid grid) {
        int result = 0;
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.getPoint(x, y).equals("#")) {
                    result++;
                }
            }
        }
        return result;
    }

    private static boolean insideGrid(int nodex, int nodey, Grid grid) {
        return (nodex >= 0 && nodex < grid.getWidth() && nodey >= 0 && nodey < grid.getHeight());
    }

    private static Map<String, List<Point>> buildAntennaMap(Grid grid) {
        Map<String, List<Point>> antennaMap = new HashMap<>();
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (!grid.getPoint(x, y).equals(".")) {
                    Point point = new Point(x, y);
                    if (antennaMap.containsKey(grid.getPoint(x, y))) {
                        antennaMap.get(grid.getPoint(x, y)).add(point);
                    } else {
                        List<Point> points = new ArrayList<>();
                        points.add(point);
                        antennaMap.put(grid.getPoint(x, y), points);
                    }
                }
            }
        }
        return antennaMap;
    }

    public static class Grid {
        private List<List<String>> grid;
        public Grid(List<String> input) {
            grid = new ArrayList<>();
            for (String line : input) {
                List<String> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    row.add(String.valueOf(line.charAt(i)));
                }
                grid.add(row);
            }
        }
        public int getWidth() {
            return grid.get(0).size();
        }
        public int getHeight() {
            return grid.size();
        }
        public void setPoint(int x, int y, String value) {
            grid.get(y).set(x, value);
        }
        public String getPoint(int x, int y) {
            return grid.get(y).get(x);
        }
        public void drawGrid() {
            for (int y = 0; y < grid.size(); y++) {
                for (int x = 0; x < grid.get(0).size(); x++) {
                    System.out.print(grid.get(y).get(x));
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static class Point {
        private int x;
        private int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
}
