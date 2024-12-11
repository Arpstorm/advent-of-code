package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day10 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day10.txt");

        long part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(List<String> input) {
        return solve(input, false);
    }

    public static long solvePart2(List<String> input) {
        return solve(input, true);
    }

    public static long solve(List<String> input, boolean fancyRating) {
        long result = 0;
        Grid grid = new Grid(input);
        List<Point> trailheads = getTrailheads(grid);
        for (Point trailhead : trailheads) {
            List<Hike> hikes = findHikes(grid, trailhead, fancyRating);
            result += hikes.size();
        }
        return result;
    }

    private static List<Hike> findHikes(Grid grid, Point trailhead, boolean fancyRating) {
        List<Hike> hikes = new ArrayList<>();
        List<Point> pointsVisited = new ArrayList<>();
        pointsVisited.add(trailhead);
        getNextSteps(grid, trailhead, pointsVisited, hikes, fancyRating);
        return hikes;
    }

    private static void getNextSteps(Grid grid, Point current, List<Point> pointsVisited, List<Hike> hikes, boolean fancyRating) {
        int currentHeight = Integer.parseInt(grid.getValue(current));
        if (currentHeight == 9) {
            addHikesDependingOnRating(current, pointsVisited, hikes, fancyRating);
            return;
        }
        Point up = new Point(current.getX(), current.getY() - 1);
        Point down = new Point(current.getX(), current.getY() + 1);
        Point right = new Point(current.getX() + 1, current.getY());
        Point left = new Point(current.getX() - 1, current.getY());
        takeStep(grid, hikes, pointsVisited, currentHeight, up, fancyRating);
        takeStep(grid, hikes, pointsVisited, currentHeight, down, fancyRating);
        takeStep(grid, hikes, pointsVisited, currentHeight, left, fancyRating);
        takeStep(grid, hikes, pointsVisited, currentHeight, right, fancyRating);
    }

    private static void takeStep(Grid grid, List<Hike> hikes, List<Point> pointsVisited, int currentHeight, Point up, boolean fancyRating) {
        try {
            if (Integer.parseInt(grid.getValue(up)) == currentHeight + 1) {
                pointsVisited.add(up);
                getNextSteps(grid, up, pointsVisited, hikes, fancyRating);
            }
        } catch (IndexOutOfBoundsException e) {
            // Can be ignored
        }
    }

    private static void addHikesDependingOnRating(Point current, List<Point> pointsVisited, List<Hike> hikes, boolean fancyRating) {
        if (fancyRating) {
            Hike hike = new Hike(pointsVisited);
            hikes.add(hike);
        } else {
            if (!isSameDestination(current, hikes)) {
                Hike hike = new Hike(pointsVisited);
                hikes.add(hike);
            }
        }
    }

    private static boolean isSameDestination(Point current, List<Hike> hikes) {
        for (Hike hike : hikes) {
            if (hike.getPoints().get(hike.getLength() - 1).getX() == current.getX()
                    && hike.getPoints().get(hike.getLength() - 1).getY() == current.getY()) {
                return true;
            }
        }
        return false;
    }

    private static List<Point> getTrailheads(Grid grid) {
        List<Point> trailheads = new ArrayList<>();
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.getValue(x, y).equals("0")) {
                    trailheads.add(new Point(x, y));
                }
            }
        }
        return trailheads;
    }

    public static class Hike {
        private final List<Point> points;
        private final int length;

        public Hike(List<Point> points) {
            this.points = points;
            this.length = points.size();
        }

        public List<Point> getPoints() {
            return points;
        }

        public int getLength() {
            return length;
        }
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
        public void setValue(int x, int y, String value) {
            grid.get(y).set(x, value);
        }
        public void setValue(Point point, String value) {
            grid.get(point.y).set(point.x, value);
        }
        public String getValue(int x, int y) {
            return grid.get(y).get(x);
        }
        public String getValue(Point point) {
            return grid.get(point.y).get(point.x);
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
