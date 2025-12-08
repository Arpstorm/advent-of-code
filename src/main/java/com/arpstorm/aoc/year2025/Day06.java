package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day06 {

    private static Character[][] paperRolls;

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2025/input.day06.txt");

        long part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(List<String> input) {
        List<String> operators = new ArrayList<>();
        List<List<String>> operandsList = new ArrayList<>();
        for (String line : input) {
            List<String> lineOperands = new ArrayList<>();
            boolean isLastLine = false;
            for (String tmp : line.split(" ", 0)) {
                if (tmp.isEmpty()) continue;
                if (tmp.equals("+") || tmp.equals("*")) {
                    operators.add(tmp);
                    isLastLine = true;
                } else {
                    lineOperands.add(tmp);
                }
            }
            if (!isLastLine) {
                operandsList.add(lineOperands);
            }
        }
        long sum = 0;
        for (int i = 0; i < operators.size(); i++) {
            long result;
            String operator = operators.get(i);
            if (operator.equals("+")) {
                result = 0;
            } else {
                result = 1;
            }
            for (List<String> operands : operandsList) {
                String operand = operands.get(i);
                if (operator.equals("+")) {
                    result += Long.parseLong(operand);
                } else if (operator.equals("*")) {
                    result *= Long.parseLong(operand);
                }
            }
            sum += result;
        }
        return sum;
    }

    public static long solvePart2(List<String> input) {
        long sum = 0;
        long result = 0;
        char operator = 0;
        for (int x = 0; x < input.get(0).length(); x++) {
            if (input.get(input.size() - 1).charAt(x) != ' ') {
                operator = input.get(input.size() - 1).charAt(x);
                result = (operator == '+') ? 0 : 1;
            }
            String number = "";
            for (int y = 0; y < input.size() - 1; y++) {
                if (input.get(y).charAt(x) != ' ') {
                    number += input.get(y).charAt(x);
                }
            }
            if (number.equals("")) {
                sum += result;
            } else {
                result = (operator == '+') ? result + Long.parseLong(number) : result * Long.parseLong(number);
            }
        }
        sum += result;
        return sum;
    }
}
