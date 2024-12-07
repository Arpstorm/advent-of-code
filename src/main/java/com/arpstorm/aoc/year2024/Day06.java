package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class Day06 {

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Guard {
        int x;
        int y;
        int vx;
        int vy;
    }

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day06.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart2(List<String> input) {
        int result = 0;
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(0).length(); x++) {
                Character[][] map = createMap(input);
                Guard guard = initGuard(input);
                List<Guard> guardStates = new ArrayList<>();
                map[x][y] = '#';
                try {
                    while (!detectedLoop(guard, guardStates)) {
                        guardStates.add(new Guard(guard.x, guard.y, guard.vx, guard.vy));
                        moveGuardOnce(guard, map);
                    }
                    result++;
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
        return result;
    }

    private static boolean detectedLoop(Guard guard, List<Guard> guardStates) {
        for (Guard guardState : guardStates) {
            if (guardState.x == guard.x && guardState.y == guard.y
                    && guardState.vx == guard.vx && guardState.vy == guard.vy) {
                return true;
            }
        }
        return false;
    }

    public static int solvePart1(List<String> input) {
        Character[][] map = createMap(input);
        Guard guard = initGuard(input);
        int result = 0;
        Character[][] trace = moveGuard(guard, map);
        //printTrace(trace);

        for (Character[] row : trace) {
            for (Character cell : row) {
                if (cell != null && cell == 'X') {
                    result++;
                }
            }
        }
        return result;
    }

    private static Character[][] createMap(List<String> input) {
        Character[][] map = new Character[input.get(0).length()][input.size()];
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                map[x][y] = line.charAt(x);
            }
        }
        return map;
    }

    private static Guard initGuard(List<String> input) {
        Guard guard = new Guard();
        guard.vx = 0;
        guard.vy = -1;
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '^') {
                    guard.x = x;
                    guard.y = y;
                }
            }
        }
        return guard;
    }

    private static void printTrace(Character[][] trace) {
        for (Character[] row : trace) {
            for (Character cell : row) {
                System.out.print(cell==null?'.':cell);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static Character[][] moveGuard(Guard guard, Character[][] map) {
        Character[][] trace = new Character[map.length][map[0].length];
        try {
            while (true) {
                trace[guard.x][guard.y] = 'X';
                //printTrace(trace);
                moveGuardOnce(guard, map);
            }
        } catch (IndexOutOfBoundsException e) {
            return trace;
        }
    }

    private static void moveGuardOnce(Guard guard, Character[][] map) throws ArrayIndexOutOfBoundsException {
        if (map[guard.x + guard.vx][guard.y + guard.vy] == '#') {
            int vxnew = -guard.vy;
            int vynew = guard.vx;
            guard.vx = vxnew;
            guard.vy = vynew;
        } else {
            guard.x = guard.x + guard.vx;
            guard.y = guard.y + guard.vy;
        }
    }
}
