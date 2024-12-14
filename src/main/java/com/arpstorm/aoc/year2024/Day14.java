package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day14 {

    private static final int SIZE_X = 101;
    private static final int SIZE_Y = 103;

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day14.txt");

        int part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart2(List<String> input) {
        Grid grid = new Grid(input);
        Grid startGrid = new Grid(input);
        int maxSymmetry = 0;
        long iterationsForMaxSymmetry = 0;
        for (int i = 0; i < 100000; i++) {
            grid = iterateGrid(grid);
            int symmetryScore = deriveRobotScore(grid);
            if (symmetryScore > maxSymmetry) {
                maxSymmetry = symmetryScore;
                iterationsForMaxSymmetry = i + 1;
            }
            if (checkIfStartGrid(grid, startGrid)) {
                System.out.println("Found start grid after " + i + " iterations");
                break;
            }
            if (symmetryScore == 1062) {
                grid.drawGrid();
            }
        }
        System.out.println("Maximum Symmetry score: " + maxSymmetry + " after " + iterationsForMaxSymmetry + " iterations");
        return iterationsForMaxSymmetry;
    }

    private static boolean checkIfStartGrid(Grid grid, Grid startGrid) {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.getValue(x, y).size() != startGrid.getValue(x, y).size()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int deriveRobotScore(Grid grid) {
        int robotScore = 0;
        for (int y = 1; y < grid.getHeight() - 1; y++) {
            for (int x = 1; x < grid.getWidth() - 1; x++) {
                if (grid.getValue(x, y).size() != 0) {
                    if (grid.getValue(x - 1, y).size() != 0) robotScore++;
                    if (grid.getValue(x + 1, y).size() != 0) robotScore++;
                    if (grid.getValue(x, y - 1).size() != 0) robotScore++;
                    if (grid.getValue(x, y + 1).size() != 0) robotScore++;
                }
            }
        }
        return robotScore;
    }

    public static int solvePart1(List<String> input) {
        Grid grid = new Grid(input);
        //grid.drawGrid();
        for (int i = 0; i < 100; i++) {
            grid = iterateGrid(grid);
            //grid.drawGrid();
        }
        int quadrant1 = 0;
        int quadrant2 = 0;
        int quadrant3 = 0;
        int quadrant4 = 0;
        for (int y = 0; y < (grid.getHeight() - 1) / 2; y++) {
            for (int x = 0; x < (grid.getWidth() - 1) / 2; x++) {
                quadrant1 += grid.getValue(x, y).size();
                quadrant2 += grid.getValue(x + (grid.getWidth() + 1) / 2, y).size();
                quadrant3 += grid.getValue(x, y + (grid.getHeight() + 1) / 2).size();
                quadrant4 += grid.getValue(x + (grid.getWidth() + 1) / 2, y + (grid.getHeight() + 1) / 2).size();
            }
        }
        return quadrant1 * quadrant2 * quadrant3 * quadrant4;
    }

    private static Grid iterateGrid(Grid grid) {
        Grid newGrid = new Grid();
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                List<Robot> robots = grid.getValue(x, y);
                for (Robot robot : robots) {
                    int newX = Math.floorMod(x + robot.getPx(), SIZE_X);
                    int newY = Math.floorMod(y + robot.getPy(), SIZE_Y);
                    newGrid.getValue(newX, newY).add(robot);
                }
            }
        }
        return newGrid;
    }

    public static class Grid {
        private List<List<List<Robot>>> grid = new ArrayList<>();

        public Grid() {
            for (int y = 0; y < SIZE_Y; y++) {
                grid.add(new ArrayList<>());
                for (int x = 0; x < SIZE_X; x++) {
                    List<Robot> robots = new ArrayList<>();
                    grid.get(y).add(robots);
                }
            }
        }

        public Grid(List<String> input) {
            for (int y = 0; y < SIZE_Y; y++) {
                grid.add(new ArrayList<>());
                for (int x = 0; x < SIZE_X; x++) {
                    List<Robot> robots = new ArrayList<>();
                    grid.get(y).add(robots);
                }
            }
            for (String line : input) {
                int x = Integer.parseInt(line.split(" ")[0].split("=")[1].split(",")[0]);
                int y = Integer.parseInt(line.split(" ")[0].split("=")[1].split(",")[1]);
                int px = Integer.parseInt(line.split(" ")[1].split("=")[1].split(",")[0]);
                int py = Integer.parseInt(line.split(" ")[1].split("=")[1].split(",")[1]);
                Robot robot = new Robot(px, py);
                getValue(x, y).add(robot);
            }
        }
        public int getWidth() {
            return grid.get(0).size();
        }
        public int getHeight() {
            return grid.size();
        }
        public void setValue(int x, int y, List<Robot> value) {
            grid.get(y).set(x, value);
        }
        public void setValue(Day10.Point point, List<Robot> value) {
            grid.get(point.getY()).set(point.getX(), value);
        }
        public List<Robot> getValue(int x, int y) {
            return grid.get(y).get(x);
        }
        public List<Robot> getValue(Day10.Point point) {
            return grid.get(point.getY()).get(point.getX());
        }
        public void drawGrid() {
            for (int y = 0; y < grid.size(); y++) {
                for (int x = 0; x < grid.get(0).size(); x++) {
                    System.out.print(grid.get(y).get(x).size());
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

    public static class Robot {
        private int px;
        private int py;
        public Robot(int px, int py) {
            this.px = px;
            this.py = py;
        }
        public int getPx() {
            return px;
        }
        public int getPy() {
            return py;
        }
    }
}
