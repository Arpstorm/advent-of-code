package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Day10 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day10.txt");

        long part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        long part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int result = 0;
        char[][] map = parseInput(input);
        Point position = getStartCoordinates(map);
        Point direction = getStartDirection(map, position);
        while (true) {
            result++;
            position = new Point(position.x + direction.x, position.y + direction.y);
            if (map[position.y][position.x] == 'S') {
                break;
            }
            direction = findDirection(map[position.y][position.x], direction);
        }
        return result / 2;
    }

    public static int solvePart2(List<String> input) {
        char[][] map = parseInput(input);
        Point position = getStartCoordinates(map);
        Point direction = getStartDirection(map, position);
        List<Point> loop = new ArrayList<>();
        loop.add(new Point(position.x, position.y));
        while (true) {
            position = new Point(position.x + direction.x, position.y + direction.y);
            if (map[position.y][position.x] == 'S') {
                break;
            }
            loop.add(position);
            direction = findDirection(map[position.y][position.x], direction);
        }
        map[position.y][position.x] = 7;
        int result = 0;
        for (int y = 0; y < map.length; y++) {
            boolean outside = true;
            boolean pipeL = false;
            boolean pipeF = false;
            for (int x = 0; x < map[y].length; x++) {
                if (isLoopPoint(loop, x, y)) {
                    if (map[y][x] == '|')
                        outside = !outside;
                    if (map[y][x] == 'L')
                        pipeL = true;
                    if (map[y][x] == 'F')
                        pipeF = true;
                    if (map[y][x] == 'J') {
                        if (pipeF) {
                            outside = !outside;
                            pipeF = false;
                        }
                        if (pipeL) {
                            pipeL = false;
                        }
                    }
                    if (map[y][x] == '7') {
                        if (pipeL) {
                            outside = !outside;
                            pipeL = false;
                        }
                        if (pipeF) {
                            pipeF = false;
                        }
                    }
                } else {
                    if (!outside) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private static boolean isLoopPoint(List<Point> loop, int x, int y) {
        for (Point loopPoint : loop) {
            if (loopPoint.x == x && loopPoint.y == y) {
                return true;
            }
        }
        return false;
    }

    private static char[][] parseInput(List<String> input) {
        char[][] map = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            char[] row = line.toCharArray();
            map[i] = row;
        }
        return map;
    }

    private static Point findDirection(char pipe, Point incoming) {
        if (incoming.y == 1) {
            if ("J".contains(String.valueOf(pipe))) return new Point(-1, 0);
            if ("|".contains(String.valueOf(pipe))) return new Point(0, 1);
            if ("L".contains(String.valueOf(pipe))) return new Point(1, 0);
        }
        if (incoming.y == -1) {
            if ("7".contains(String.valueOf(pipe))) return new Point(-1, 0);
            if ("|".contains(String.valueOf(pipe))) return new Point(0, -1);
            if ("F".contains(String.valueOf(pipe))) return new Point(1, 0);
        }
        if (incoming.x == 1) {
            if ("J".contains(String.valueOf(pipe))) return new Point(0, -1);
            if ("-".contains(String.valueOf(pipe))) return new Point(1, 0);
            if ("7".contains(String.valueOf(pipe))) return new Point(0, 1);
        }
        if (incoming.x == -1) {
            if ("F".contains(String.valueOf(pipe))) return new Point(0, 1);
            if ("-".contains(String.valueOf(pipe))) return new Point(-1, 0);
            if ("L".contains(String.valueOf(pipe))) return new Point(0, -1);
        }
        return null;
    }

    private static Point getStartDirection(char[][] map, Point position) {
        if ("F-L".contains(String.valueOf(map[position.y][position.x - 1]))) return new Point(-1, 0);
        if ("J-7".contains(String.valueOf(map[position.y][position.x + 1]))) return new Point(1, 0);
        if ("7|F".contains(String.valueOf(map[position.y - 1][position.x]))) return new Point(0, -1);
        if ("J|L".contains(String.valueOf(map[position.y + 1][position.x]))) return new Point(0, 1);
        return null;
    }

    private static Point getStartCoordinates(char[][] map) {
        for (int y = 0; y < map.length; y++) {
            char[] line = map[y];
            for (int x = 0; x < line.length; x++) {
                if (line[x] == 'S') return new Point(x, y);
            }
        }
        return null;
    }

    @Data
    static class Point {
        private final int x;
        private final int y;
    }
}
