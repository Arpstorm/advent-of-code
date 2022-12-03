package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day03 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day03.txt");

        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }

    public static int solvePart1(List<String> input) {
        int priority = 0;
        for (String rucksack : input) {
            char item = findEqualItem(rucksack.substring(0, rucksack.length() / 2), rucksack.substring(rucksack.length() / 2));
            priority += getItemPriority(item);
        }
        return priority;
    }

    public static int solvePart2(List<String> input) {
        int priority = 0;
        for (int a = 0; a < input.size() / 3; a++) {
            char badge = getBadgeChar(input.get(3 * a), input.get(3 * a + 1), input.get(3 * a + 2));
            priority += getItemPriority(badge);
        }
        return priority;
    }

    private static char findEqualItem(String comp1, String comp2) {
        Set<Character> group1 = comp1.chars().mapToObj(chr -> (char) chr).collect(Collectors.toSet());
        Set<Character> group2 = comp2.chars().mapToObj(chr -> (char) chr).collect(Collectors.toSet());
        group1.retainAll(group2);
        return group1.iterator().next();
    }

    private static int getItemPriority(char character) {
        if (Character.isLowerCase(character)) {
            return (int) character - (int) 'a' + 1;
        } else {
            return (int) character - (int) 'A' + 27;
        }
    }

    private static char getBadgeChar(String string1, String string2, String string3) {
        Set<Character> group1 = string1.chars().mapToObj(chr -> (char) chr).collect(Collectors.toSet());
        Set<Character> group2 = string2.chars().mapToObj(chr -> (char) chr).collect(Collectors.toSet());
        Set<Character> group3 = string3.chars().mapToObj(chr -> (char) chr).collect(Collectors.toSet());
        group1.retainAll(group2);
        group1.retainAll(group3);
        return group1.iterator().next();
    }
}
