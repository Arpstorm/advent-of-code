package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day12.txt");

        long part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        long part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int result = 0;
        List<ConditionRecord> conditions = parseInput(input);
        for (ConditionRecord condition : conditions) {
            int arrangements = getNumberOfArrangements(condition);
            System.out.println(arrangements + " arrangements");
            result+= arrangements;
        }
        return result;
    }

    public static int solvePart2(List<String> input) {
        int result = 0;
        List<ConditionRecord> conditions = parseInput(input);
        for (ConditionRecord condition : conditions) {
            unfoldInput(condition);
            removeDots(condition);
            int arrangements = getNumberOfArrangements(condition);
            System.out.println(arrangements + " arrangements");
            result+= arrangements;
        }
        return result;
    }

    private static void removeDots(ConditionRecord condition) {
        StringBuilder sb = new StringBuilder();
        boolean dot = false;
        for (char myChar : condition.springs.toCharArray()) {
            if (myChar == '.') {
                if (!dot) {
                    sb.append('.');
                    dot = true;
                }
            } else {
                sb.append(myChar);
                dot = false;
            }
        }
        condition.springs = sb.toString();
    }

    private static void unfoldInput(ConditionRecord condition) {
        condition.springs = condition.springs + "?" + condition.springs + "?" + condition.springs + "?" + condition.springs + "?" + condition.springs;
        condition.groupSizes = Stream.of(condition.groupSizes, condition.groupSizes, condition.groupSizes, condition.groupSizes, condition.groupSizes)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());;
    }

    private static int getNumberOfArrangements(ConditionRecord condition) {
        int arrangements = 0;
        int numberOfUnknowns = (int) condition.springs.codePoints().filter(ch -> ch == '?').count();
        for (int i = 0; i < Math.pow(2, numberOfUnknowns); i++) {
            String pattern = new String(condition.springs);
            String binaryPattern = Long.toBinaryString(i).replace("0", "#").replace("1", ".");
            binaryPattern = StringUtils.leftPad(binaryPattern, numberOfUnknowns, '#');
            for (int j = 0; j < numberOfUnknowns; j++) {
                pattern = pattern.replaceFirst("\\?", Character.toString(binaryPattern.charAt(j)));
            }
            // Attempt to match
            StringBuilder sb = new StringBuilder("^\\.*");
            for (int j = 0; j < condition.groupSizes.size(); j++) {
                int size = condition.groupSizes.get(j);
                sb.append("#{" + size + "}");
                if (j == condition.groupSizes.size() - 1) {
                    sb.append("\\.*$");
                } else {
                    sb.append("\\.+");
                }
            }
            Pattern regex = Pattern.compile(sb.toString());
            if (regex.matcher(pattern).find()) {
                arrangements++;
            }
        }
        return arrangements;
    }

    private static List<ConditionRecord> parseInput(List<String> input) {
        List<ConditionRecord> conditionRecords = new ArrayList<>();
        for (String line : input) {
            ConditionRecord conditionRecord = new ConditionRecord();
            conditionRecord.springs = line.split(" ")[0];
            conditionRecord.groupSizes = Arrays.stream(line.split(" ")[1].split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            conditionRecords.add(conditionRecord);
        }
        return conditionRecords;
    }
    static class ConditionRecord {
        String springs;
        List<Integer> groupSizes;
    }
}
