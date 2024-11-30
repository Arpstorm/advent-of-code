package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day05 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day05.txt");
        List<List<String>> stacks = getInitialStacks(input);
        List<List<Integer>> operations = getOperations(input);

        System.out.println("Part 1: " + solvePart1(stacks, operations));

        stacks = getInitialStacks(input);
        System.out.println("Part 2: " + solvePart2(stacks, operations));
    }

    static List<List<Integer>> getOperations(List<String> input) {
        List<List<Integer>> operations = new ArrayList<>();
        for (String line : input) {
            if (line.startsWith("move")) {
                List<Integer> stackOps = new ArrayList<>();
                String[] split = line.split(" ");
                stackOps.add(Integer.parseInt(split[1]));
                stackOps.add(Integer.parseInt(split[3]));
                stackOps.add(Integer.parseInt(split[5]));
                operations.add(stackOps);
            }
        }
        return operations;
    }

    static List<List<String>> getInitialStacks(List<String> input) {
        int nrStacks = 0;
        for (String line : input) {
            int nrOfChars = line.length() / 4 + 1;
            if (nrOfChars > nrStacks) {
                nrStacks = nrOfChars;
            }
            if (!line.contains("[")) {
                break;
            }
        }
        List<List<String>> stacks = new ArrayList<>();
        for (String line : input) {
            if (!line.contains("[")) {
                break;
            }
            for (int i = 0; i < nrStacks; i++) {
                stacks.add(new ArrayList<>());
                if (stacks.get(i) == null) {
                    stacks.add(new ArrayList<>());
                }
                String stackChar = Character.toString(line.charAt(4 * i + 1));
                stacks.get(i).add(stackChar);
            }
        }
        stacks.removeIf(List::isEmpty);
        for (List<String> stack : stacks) {
            stack.removeAll(Arrays.asList(null," "));
        }
        for (List<String> stack : stacks) {
            Collections.reverse(stack);
        }
        return stacks;
    }

    public static String solvePart1(List<List<String>> stacks, List<List<Integer>> operations) {
        for (List<Integer> operation : operations) {
            for (int j = 0; j < operation.get(0); j++) {
                int stackToRemoveFrom = operation.get(1) - 1;
                int stackToAddTo = operation.get(2) - 1;
                stacks.get(stackToAddTo).add(stacks.get(stackToRemoveFrom).remove(stacks.get(stackToRemoveFrom).size() - 1));
            }
        }
        StringBuilder result = new StringBuilder();
        for (List<String> stack : stacks) {
            result.append(stack.get(stack.size() - 1));
        }
        return result.toString();
    }

    public static String solvePart2(List<List<String>> stacks, List<List<Integer>> operations) {
        for (List<Integer> operation : operations) {
            for (int j = operation.get(0); j > 0; j--) {
                int stackToRemoveFrom = operation.get(1) - 1;
                int stackToAddTo = operation.get(2) - 1;
                stacks.get(stackToAddTo).add(stacks.get(stackToRemoveFrom).remove(stacks.get(stackToRemoveFrom).size() - j));
            }
        }
        StringBuilder result = new StringBuilder();
        for (List<String> stack : stacks) {
            result.append(stack.get(stack.size() - 1));
        }
        return result.toString();
    }
}
