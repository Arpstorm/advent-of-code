package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    public static void main(String[] args) {
        String input = Utils.readInputAsString("/year2024/input.day03.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(String input) {
        int result = 0;
        Pattern pattern = Pattern.compile("(mul\\()(\\d{1,3}),(\\d{1,3})\\).*?");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            int factor1 = Integer.parseInt(matcher.group(2));
            int factor2 = Integer.parseInt(matcher.group(3));
            result+= factor1 * factor2;
        }
        return result;
    }

    public static int solvePart2(String input) {
        int result = 0;
        boolean enabled = true;
        Pattern pattern = Pattern.compile("(mul\\()(\\d{1,3}),(\\d{1,3})\\).*?|(do\\(\\))|don't\\(\\)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            if (matcher.group(0).equals("do()")) {
                enabled = true;
            } else if (matcher.group(0).equals("don't()")) {
                enabled = false;
            } else if (enabled) {
                int factor1 = Integer.parseInt(matcher.group(2));
                int factor2 = Integer.parseInt(matcher.group(3));
                result += factor1 * factor2;
            }
        }
        return result;
    }
}
