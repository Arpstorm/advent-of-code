package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.*;

public class Day13 {


    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day13.txt");

        int part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int result = 0;
        List<Machine> machines = parseMachines(input);
        for (Machine machine : machines) {
            result += findCheapestSolution(machine);
        }
        return result;
    }

    public static long solvePart2(List<String> input) {
        long result = 0;
        List<Machine> machines = parseMachines(input);
        for (Machine machine : machines) {
            machine.pricex += 10000000000000L;
            machine.pricey += 10000000000000L;
        }
        for (Machine machine : machines) {
            double nA = (machine.pricex * machine.buttonBy - machine.pricey * machine.buttonBx) / ((double) (machine.buttonAx * machine.buttonBy - machine.buttonAy * machine.buttonBx));
            double nB = (machine.pricey * machine.buttonAx - machine.pricex * machine.buttonAy) / ((double) (machine.buttonAx * machine.buttonBy - machine.buttonAy * machine.buttonBx));
            if (Math.round(nA) == nA && Math.round(nB) == nB && nA >= 0 && nB >= 0) {
                result += 3 * nA + nB;
            }
        }
        return result;
    }

    private static int findCheapestSolution(Machine machine) {
        for (int a = 0; a < 100; a++) {
            for (int b = 0; b < 100; b++) {
                if (a * machine.buttonAx + b * machine.buttonBx == machine.pricex
                        && a * machine.buttonAy + b * machine.buttonBy == machine.pricey) {
                    return 3 * a + b;
                }
            }
        }
        return 0;
    }

    private static List<Machine> parseMachines(List<String> input) {
        List<Machine> machines = new ArrayList<>();
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.contains("Button A")) {
                Machine machine = new Machine();
                machine.buttonAx = Integer.parseInt(line.split(", ")[0].split("\\+")[1]);
                machine.buttonAy = Integer.parseInt(line.split(", ")[1].split("\\+")[1]);
                line = iterator.next();
                machine.buttonBx = Integer.parseInt(line.split(", ")[0].split("\\+")[1]);
                machine.buttonBy = Integer.parseInt(line.split(", ")[1].split("\\+")[1]);
                line = iterator.next();
                machine.pricex = Integer.parseInt(line.split(", ")[0].split("=")[1]);
                machine.pricey = Integer.parseInt(line.split(", ")[1].split("=")[1]);
                machines.add(machine);
            }
        }
        return machines;
    }

    public static class Machine {
        private long buttonAx;
        private long buttonAy;
        private long buttonBx;
        private long buttonBy;
        private long pricex;
        private long pricey;
    }
}
