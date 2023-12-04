package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import lombok.Data;

import java.util.*;

public class Day04 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day04.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int sum = 0;
        for (String line : input) {
            int numberOfWins = getNumberOfWins(line);
            int points = (int) Math.pow(2, numberOfWins - 1);
            sum += points;
        }
        return sum;
    }

    private static int getNumberOfWins(String line) {
        int numberOfWins = 0;
        String[] numbers = line.split(":")[1].split("\\|");
        List<String> winningNumbers = new LinkedList<>(Arrays.asList(numbers[0].split(" ")));
        List<String> myNumbers = new LinkedList<>(Arrays.asList(numbers[1].split(" ")));
        winningNumbers.removeIf(String::isEmpty);
        myNumbers.removeIf(String::isEmpty);
        for (String number : myNumbers) {
            if (winningNumbers.contains(number)) {
                numberOfWins++;
            }
        }
        return numberOfWins;
    }

    public static int solvePart2(List<String> input) {
        int sum = 0;
        int[] numberOfCards = new int[input.size()];
        Arrays.fill(numberOfCards, 1);
        for (int i = 0; i < input.size(); i++) {
            int numberOfWins = getNumberOfWins(input.get(i));
            for (int j = 0; j < numberOfWins; j++) {
                numberOfCards[1 + i + j] += numberOfCards[i];
            }
        }
        for (int i = 0; i < input.size(); i++) {
            sum += numberOfCards[i];
        }
        return sum;
    }
}
