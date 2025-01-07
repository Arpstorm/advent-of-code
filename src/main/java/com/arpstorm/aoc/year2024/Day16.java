package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.*;

public class Day16 {

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
        List<PathElement> pointsVisited = new ArrayList<>();
        pointsVisited.add(new PathElement(startPoint, startDirection, 0));

        List<Integer> scores = iterativeSearch(grid, pointsVisited);

        grid.drawGrid();

        int minScore = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score < minScore) {
                minScore = score;
            }
        }
        return minScore;
    }

    private static List<Integer> iterativeSearch(Grid grid, List<PathElement> pathElements) {
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < pathElements.size(); i++) {
            PathElement pathElement = pathElements.get(i);
            attemptStepForward(grid, pathElements, scores, pathElement);
            Point leftDirection = new Point(pathElement.direction.y, -pathElement.direction.x);
            attemptTurn(grid, pathElements, scores, pathElement, leftDirection);
            Point rightDirection = new Point(-pathElement.direction.y, pathElement.direction.x);
            attemptTurn(grid, pathElements, scores, pathElement, rightDirection);
            //grid.drawGrid();
        }
        return scores;
    }

    private static void attemptStepForward(Grid grid, List<PathElement> pathElements, List<Integer> scores, PathElement pathElement) {
        Point target = new Point(pathElement.point.x + pathElement.direction.x, pathElement.point.y + pathElement.direction.y);
        for (PathElement element : pathElements) {
            if (element.point.x == target.x && element.point.y == target.y
                    && element.direction.x == pathElement.direction.x && element.direction.y == pathElement.direction.y
                    && element.score <= pathElement.score + 1) {
                return;
            }
        }
        if (grid.getValue(target).equals(".")) {
            //grid.setValue(pathElement.point, "*");
            pathElements.add(new PathElement(target, pathElement.direction, pathElement.score + 1));
        } else if (grid.getValue(target).equals("E")) {
            scores.add(pathElement.score + 1);
            System.out.println("Found exit! Score: " + (pathElement.score + 1));
        }
    }

    private static void attemptTurn(Grid grid, List<PathElement> pathElements, List<Integer> scores, PathElement pathElement, Point direction) {
        Point target = new Point(pathElement.point.x + direction.x, pathElement.point.y + direction.y);
        for (PathElement element : pathElements) {
            if (element.point.x == target.x && element.point.y == target.y
                    && element.direction.x == direction.x && element.direction.y == direction.y
                    && element.score <= pathElement.score + 1001) {
                return;
            }
        }
        if (grid.getValue(target).equals(".")) {
            //grid.setValue(pathElement.point, "*");
            pathElements.add(new PathElement(target, direction, pathElement.score + 1001));
        } else if (grid.getValue(target).equals("E")) {
            scores.add(pathElement.score + 1001);
            System.out.println("Found exit! Score: " + (pathElement.score + 1001));
        }
    }

    public static class PathElement {
        Point point;
        Point direction;
        int score;
        public PathElement(Point point, Point direction, int score) {
            this.point = point;
            this.direction = direction;
            this.score = score;
        }
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
