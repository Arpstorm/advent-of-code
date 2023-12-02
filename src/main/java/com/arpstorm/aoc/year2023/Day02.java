package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;

import java.util.List;

public class Day02 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day02.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        return deriveSum(input);
    }

    public static int solvePart2(List<String> input) {
        return deriveSumOfPowers(input);
    }

    private static int deriveSum(List<String> input) {
        int sum = 0;
        for (String line : input) {
            String[] game = line.split(" ");
            if (isPossibleGame(game)) {
                int gameId = Integer.parseInt(game[1].replace(":", ""));
                sum += gameId;
            }
        }
        return sum;
    }

    private static boolean isPossibleGame(String[] game) {
        for (int i = 1; i < game.length; i++) {
            String label = game[i].replace(",", "").replace(";", "");
            if (label.equals("red")) {
                int number = Integer.parseInt(game[i - 1]);
                if (number > 12) return false;
            } else if (label.equals("green")) {
                int number = Integer.parseInt(game[i - 1]);
                if (number > 13) return false;
            } else if (label.equals("blue")) {
                int number = Integer.parseInt(game[i - 1]);
                if (number > 14) return false;
            }
        }
        return true;
    }

    private static int deriveSumOfPowers(List<String> input) {
        int sum = 0;
        for (String line : input) {
            String[] game = line.split(" ");
            int redMax = 0, greenMax = 0, blueMax = 0;
            for (int i = 1; i < game.length; i++) {
                String label = game[i].replace(",", "").replace(";", "");
                if (label.equals("red")) {
                    int number = Integer.parseInt(game[i - 1]);
                    if (redMax < number) redMax = number;
                } else if (label.equals("green")) {
                    int number = Integer.parseInt(game[i - 1]);
                    if (greenMax < number) greenMax = number;
                } else if (label.equals("blue")) {
                    int number = Integer.parseInt(game[i - 1]);
                    if (blueMax < number) blueMax = number;
                }
            }
            sum += redMax * greenMax * blueMax;
        }
        return sum;
    }
}
