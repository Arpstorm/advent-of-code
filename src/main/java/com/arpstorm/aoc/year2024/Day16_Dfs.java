package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.*;

public class Day16_Dfs {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day16.txt");

        int part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        Grid grid = new Grid(input);
        Point startPoint = new Point(1, grid.getHeight() - 2);
        Point startDirection = new Point(1, 0);
        List<Point> pointsVisited = new ArrayList<>();
        pointsVisited.add(startPoint);
        Map<Integer, List<Point>> pathsByScore = new HashMap<>();

        recursiveSearch(grid, startPoint, startDirection, pointsVisited, false, pathsByScore);

        grid.drawGrid();

        int minScore = Integer.MAX_VALUE;
        for (int score : pathsByScore.keySet()) {
            if (score < minScore) {
                minScore = score;
            }
        }
        return minScore;
    }

    private static void recursiveSearch(Grid grid, Point point, Point direction, List<Point> pointsVisited, boolean justTurned, Map<Integer, List<Point>> pathsByScore) {
        Point target = new Point(point.getX() + direction.getX(), point.getY() + direction.getY());
        String targetValue = grid.getValue(target);
        if (targetValue.equals(".")) {
            if (pointAlreadyVisited(target, pointsVisited)) {
                // Remove the current position, as we start backtracking
                // grid.setValue(point, ".");
                return;
            }
            // Take a step in direction
            takeStepForward(grid, point, direction, target, pointsVisited, pathsByScore);
            // When we reach this point, we are already backtracking and need to check for left and right turns
            if (!justTurned) {
                takeTurn(grid, point, rotateLeft(direction), pointsVisited, pathsByScore);
                takeTurn(grid, point, rotateRight(direction), pointsVisited, pathsByScore);
            }
            // Still backtracking, so we need to clear the target point
            //grid.setValue(target, ".");
        } else if (!justTurned && targetValue.equals("#")) {
            takeTurn(grid, point, rotateLeft(direction), pointsVisited, pathsByScore);
            takeTurn(grid, point, rotateRight(direction), pointsVisited, pathsByScore);
        } else if (targetValue.equals("E")) {
            pointsVisited.add(target);
            //grid.setValue(point, ".");
            int score = deriveScore(pointsVisited);
            pathsByScore.put(score, pointsVisited);
            System.out.println("Found exit! Score: " + score);
        }
    }

    private static void takeTurn(Grid grid, Point point, Point direction, List<Point> pointsVisited, Map<Integer, List<Point>> pathsByScore) {
        List<Point> newPointsVisited = new ArrayList<>(pointsVisited);
        // On a turn, we add the same point a 2nd time, to indicate a turn
        newPointsVisited.add(point);
        recursiveSearch(grid, point, direction, newPointsVisited, true, pathsByScore);
    }

    private static int deriveScore(List<Point> pointsVisited) {
        int score = 0;
        for (int i = 1; i < pointsVisited.size(); i++) {
            if (pointsVisited.get(i).getX() == pointsVisited.get(i - 1).getX()
                    && pointsVisited.get(i).getY() == pointsVisited.get(i - 1).getY()) {
                score = score + 1000;
            } else {
                score = score + 1;
            }
        }
        return score;
    }

    private static void takeStepForward(Grid grid, Point point, Point direction, Point target, List<Point> pointsVisited, Map<Integer, List<Point>> pathsByScore) {
        List<Point> newPointsVisited = new ArrayList<>(pointsVisited);
        newPointsVisited.add(target);
        //grid.setValue(target, "S");
        //grid.setValue(point, ".");
        // grid.drawGrid();
        recursiveSearch(grid, target, direction, newPointsVisited, false, pathsByScore);
    }

    private static boolean pointAlreadyVisited(Point destination, List<Point> pointsAndDirectionsVisited) {
        for (Point pointVisited : pointsAndDirectionsVisited) {
            if (destination.getX() == pointVisited.getX() && destination.getY() == pointVisited.getY()) {
                return true;
            }
        }
        return false;
    }

    private static Point rotateLeft(Point direction) {
        return new Point(direction.getY(), -direction.getX());
    }

    private static Point rotateRight(Point direction) {
        return new Point(-direction.getY(), direction.getX());
    }

    public static int solvePart2(List<String> input) {
        int result = 0;
        return result;
    }

    public static class Grid {
        private List<List<String>> grid;
        public Grid(int width, int height) {
            grid = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                List<String> row = new ArrayList<>();
                for (int x = 0; x < width; x++) {
                    row.add("");
                }
                grid.add(row);
            }
        }
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
        public void setValue(int x, int y, String value) {
            grid.get(y).set(x, value);
        }
        public void setValue(Point point, String value) {
            grid.get(point.getY()).set(point.getX(), value);
        }
        public String getValue(int x, int y) {
            return grid.get(y).get(x);
        }
        public String getValue(Point point) {
            return grid.get(point.getY()).get(point.getX());
        }
        public List<String> getRow(int y) {
            return grid.get(y);
        }
        public void setRow(int y, List<String> row) {
            grid.set(y, row);
        }
        public List<String> getColumn(int x) {
            List<String> column = new ArrayList<>();
            for (int y = 0; y < grid.size(); y++) {
                column.add(grid.get(y).get(x));
            }
            return column;
        }
        public void setColumn(int x, List<String> column) {
            for (int y = 0; y < column.size(); y++) {
                grid.get(y).set(x, column.get(y));
            }
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
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
    }
}
