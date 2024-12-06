package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 {

    private static final List<String> incorrectlyOrderedUpdates = new ArrayList<>();

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day05.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static List<String> getLinesContaining(List<String> input, String pattern) {
        List<String> lines = new ArrayList<>();
        for (String line : input) {
            if (line.contains(pattern)) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static int solvePart1(List<String> input) {
        List<String> rules = getLinesContaining(input, "|");
        List<String> updates = getLinesContaining(input, ",");
        int result = 0;
        for (String update : updates) {
            String[] pages = update.split(",");
            if (updateFollowsRules(pages, rules)) {
                result+= Integer.parseInt(pages[(pages.length - 1) / 2]);
            } else {
                incorrectlyOrderedUpdates.add(update);
            }
        }
        return result;
    }

    public static int solvePart2(List<String> input) {
        List<String> rules = getLinesContaining(input, "|");
        int result = 0;
        for (String update : incorrectlyOrderedUpdates) {
            String[] pages = update.split(",");
            String[] orderedPages = orderUpdate(pages, rules);
            result+= Integer.parseInt(orderedPages[(orderedPages.length - 1) / 2]);
        }
        return result;
    }

    private static String[] orderUpdate(String[] pages, List<String> rules) {
        String[] orderedPages = new String[pages.length];
        for (int i = 0; i < pages.length; i++) {
            int numberOfMatchingRules = 0;
            for (int j = 0; j < pages.length; j++) {
                if (i == j) {
                    continue;
                }
                if (hasMatchingRule(pages[i], pages[j], rules)) {
                    numberOfMatchingRules++;
                }
            }
            orderedPages[pages.length - numberOfMatchingRules - 1] = pages[i];
        }
        for (String page : pages) {
            if (!Arrays.asList(orderedPages).contains(page)) {
                orderedPages[orderedPages.length - 1] = page;
            }
        }
        return orderedPages;
    }

    private static boolean updateFollowsRules(String[] pages, List<String> rules) {
        for (int i = 0; i < pages.length; i++) {
            String page = pages[i];
            String[] others = Arrays.copyOfRange(pages, i + 1, pages.length);
            for (String other : others) {
                if (!hasMatchingRule(page, other, rules)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasMatchingRule(String page, String other, List<String> rules) {
        for (String rule : rules) {
            String ruleCurrent = rule.split("\\|")[0];
            String ruleNext = rule.split("\\|")[1];
            if (page.equals(ruleCurrent) && other.equals(ruleNext)) {
                return true;
            }
        }
        return false;
    }
}
