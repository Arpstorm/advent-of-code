package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;

import java.util.*;

public class Day08 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day08.txt");

        long part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        long part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int result = 0;
        char[] instructionArray = input.get(0).toCharArray();
        Map<String, String[]> map = getMap(input);

        String currentPos = "AAA";
        for (int i = 0; i < instructionArray.length; i++) {
            result++;
            if (instructionArray[i] == 'L') {
                currentPos = map.get(currentPos)[0];
            } else {
                currentPos = map.get(currentPos)[1];
            }
            if (currentPos.equals("ZZZ")) {
                break;
            }
            if (i == instructionArray.length - 1) {
                i = -1;
            }
        }
        return result;
    }

    public static long solvePart2(List<String> input) {
        char[] instructionArray = input.get(0).toCharArray();
        Map<String, String[]> map = getMap(input);
        Map<String, String[]> startNodes = getStartNodes(map);
        List<Integer> steps = new ArrayList<>();
        for (String[] startNode : startNodes.values()) {
            int numberOfSteps = 0;
            String[] currentNode = startNode;
            for (int i = 0; i < instructionArray.length; i++) {
                String[] endNode;
                numberOfSteps++;
                if (instructionArray[i] == 'L') {
                    if (currentNode[0].endsWith("Z")) break;
                    endNode = map.get(currentNode[0]);
                } else {
                    if (currentNode[1].endsWith("Z")) break;
                    endNode = map.get(currentNode[1]);
                }
                if (i == instructionArray.length - 1) {
                    i = -1;
                }
                currentNode = endNode;
            }
            steps.add(numberOfSteps);
        }
        long tmp = steps.get(0);
        for (int i = 1; i < steps.size(); i++) {
            tmp = lcm(tmp, steps.get(i));
        }
        return tmp;
    }

    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    private static Map<String, String[]> getStartNodes(Map<String, String[]> map) {
        Map<String, String[]> startNodes = new HashMap<>();
        for (Map.Entry<String, String[]> mapEntry : map.entrySet()) {
            if (mapEntry.getKey().endsWith("A")) {
                startNodes.put(mapEntry.getKey(), mapEntry.getValue());
            }
        }
        return startNodes;
    }

    private static Map<String, String[]> getMap(List<String> input) {
        Map<String, String[]> map = new HashMap<>();
        for (int i = 2; i < input.size(); i++) {
            String location = input.get(i).split(" = ")[0];
            String[] direction = input.get(i).split(" = ")[1].replace("(", "").replace(")", "").split(", ");
            map.put(location, direction);
        }
        return map;
    }
}
