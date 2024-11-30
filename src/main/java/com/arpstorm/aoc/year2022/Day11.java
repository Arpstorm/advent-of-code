package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day11 {

    private static List<Monkey> monkeys;
    private static List<FancyMonkey> fancyMonkeys;

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day11.txt");

        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }

    public static int solvePart1(List<String> lines) {
        initMonkeys(lines);
        for (int round = 0; round < 20; round++) {
            for (Monkey monkey : monkeys) {
                processItems(monkey, 3);
            }
            if (round == 0 || round == 19) {
                System.out.println("== After Round " + (round + 1) + " ==");
                System.out.println("Monkey 0: " + monkeys.get(0).getInspectCount());
                System.out.println("Monkey 1: " + monkeys.get(1).getInspectCount());
                System.out.println("Monkey 2: " + monkeys.get(2).getInspectCount());
                System.out.println("Monkey 3: " + monkeys.get(3).getInspectCount());
                System.out.println();
            }
        }
        return deriveMonkeyBusiness();
    }

    public static long solvePart2(List<String> lines) {
        initFancyMonkeys(lines);
        for (int round = 0; round < 10000; round++) {
            System.out.println("Round " + round);
            for (FancyMonkey monkey : fancyMonkeys) {
                processFancyItems(monkey);
            }
            if (round == 0 || round == 19 || round == 999 || round == 1999 || round == 2999) {
                System.out.println("== After Round " + (round + 1) + " ==");
                System.out.println("Monkey 0: " + fancyMonkeys.get(0).getInspectCount());
                System.out.println("Monkey 1: " + fancyMonkeys.get(1).getInspectCount());
                System.out.println("Monkey 2: " + fancyMonkeys.get(2).getInspectCount());
                System.out.println("Monkey 3: " + fancyMonkeys.get(3).getInspectCount());
                System.out.println();
            }
        }
        return deriveFancyMonkeyBusiness();
    }

    private static void initFancyMonkeys(List<String> lines) {
        fancyMonkeys = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("Monkey")) {
                List<List<Integer>> startingItems = new ArrayList<>();
                for (String item : lines.get(i + 1).substring(18).split(", ")) {
                    List<Integer> rests = new ArrayList<>();
                    rests.add(Integer.parseInt(item) % 3);
                    rests.add(Integer.parseInt(item) % 11);
                    rests.add(Integer.parseInt(item) % 7);
                    rests.add(Integer.parseInt(item) % 2);
                    rests.add(Integer.parseInt(item) % 19);
                    rests.add(Integer.parseInt(item) % 5);
                    rests.add(Integer.parseInt(item) % 17);
                    rests.add(Integer.parseInt(item) % 13);
                    startingItems.add(rests);
                }
                String operationOperator = lines.get(i + 2).substring(19).split(" ")[1];
                String operationOperand = lines.get(i + 2).substring(19).split(" ")[2];
                int divisor = Integer.parseInt(lines.get(i + 3).substring(21));
                int trueTarget = Integer.parseInt(lines.get(i + 4).substring(29));
                int falseTarget = Integer.parseInt(lines.get(i + 5).substring(30));
                FancyMonkey monkey = new FancyMonkey(startingItems, operationOperator, operationOperand, divisor, trueTarget, falseTarget, 0);
                fancyMonkeys.add(monkey);
            }
        }
    }

    private static void initMonkeys(List<String> lines) {
        monkeys = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("Monkey")) {
                List<Long> startingItems = new ArrayList<>();
                for (String item : lines.get(i + 1).substring(18).split(", ")) {
                    startingItems.add(Long.parseLong(item));
                }
                String operationOperator = lines.get(i + 2).substring(19).split(" ")[1];
                String operationOperand = lines.get(i + 2).substring(19).split(" ")[2];
                int divisor = Integer.parseInt(lines.get(i + 3).substring(21));
                int trueTarget = Integer.parseInt(lines.get(i + 4).substring(29));
                int falseTarget = Integer.parseInt(lines.get(i + 5).substring(30));
                Monkey monkey = new Monkey(startingItems, operationOperator, operationOperand, divisor, trueTarget, falseTarget, 0);
                monkeys.add(monkey);
            }
        }
    }

    private static int deriveMonkeyBusiness() {
        int monkeyBusiness1 = 0;
        int monkeyBusiness2 = 0;
        for (Monkey monkey : monkeys) {
            if (monkey.getInspectCount() > monkeyBusiness1) {
                monkeyBusiness2 = monkeyBusiness1;
                monkeyBusiness1 = monkey.getInspectCount();
            } else if (monkey.getInspectCount() > monkeyBusiness2) {
                monkeyBusiness2 = monkey.getInspectCount();
            }
        }
        return monkeyBusiness1 * monkeyBusiness2;
    }

    private static long deriveFancyMonkeyBusiness() {
        long monkeyBusiness1 = 0;
        long monkeyBusiness2 = 0;
        for (FancyMonkey monkey : fancyMonkeys) {
            if (monkey.getInspectCount() > monkeyBusiness1) {
                monkeyBusiness2 = monkeyBusiness1;
                monkeyBusiness1 = monkey.getInspectCount();
            } else if (monkey.getInspectCount() > monkeyBusiness2) {
                monkeyBusiness2 = monkey.getInspectCount();
            }
        }
        return monkeyBusiness1 * monkeyBusiness2;
    }

    private static void processFancyItems(FancyMonkey monkey) {
        for (int i = 0; i < monkey.getItems().size(); i++) {
            List<Integer> rests = monkey.getItems().get(i);
            monkey.setInspectCount(monkey.getInspectCount() + 1);
            if (monkey.getOperationOperator().equals("+")) {
                if (monkey.getOperationOperand().equals("old")) {
                    rests.set(0, (rests.get(0) + rests.get(0)) % 3);
                    rests.set(1, (rests.get(1) + rests.get(1)) % 11);
                    rests.set(2, (rests.get(2) + rests.get(2)) % 7);
                    rests.set(3, (rests.get(3) + rests.get(3)) % 2);
                    rests.set(4, (rests.get(4) + rests.get(4)) % 19);
                    rests.set(5, (rests.get(5) + rests.get(5)) % 5);
                    rests.set(6, (rests.get(6) + rests.get(6)) % 17);
                    rests.set(7, (rests.get(7) + rests.get(7)) % 13);
                } else {
                    int operand = Integer.parseInt(monkey.getOperationOperand());
                    rests.set(0, (rests.get(0) + operand) % 3);
                    rests.set(1, (rests.get(1) + operand) % 11);
                    rests.set(2, (rests.get(2) + operand) % 7);
                    rests.set(3, (rests.get(3) + operand) % 2);
                    rests.set(4, (rests.get(4) + operand) % 19);
                    rests.set(5, (rests.get(5) + operand) % 5);
                    rests.set(6, (rests.get(6) + operand) % 17);
                    rests.set(7, (rests.get(7) + operand) % 13);
                }
            } else if (monkey.getOperationOperator().equals("*")) {
                if (monkey.getOperationOperand().equals("old")) {
                    rests.set(0, (rests.get(0) * rests.get(0)) % 3);
                    rests.set(1, (rests.get(1) * rests.get(1)) % 11);
                    rests.set(2, (rests.get(2) * rests.get(2)) % 7);
                    rests.set(3, (rests.get(3) * rests.get(3)) % 2);
                    rests.set(4, (rests.get(4) * rests.get(4)) % 19);
                    rests.set(5, (rests.get(5) * rests.get(5)) % 5);
                    rests.set(6, (rests.get(6) * rests.get(6)) % 17);
                    rests.set(7, (rests.get(7) * rests.get(7)) % 13);
                } else {
                    int operand = Integer.parseInt(monkey.getOperationOperand());
                    rests.set(0, (rests.get(0) * operand) % 3);
                    rests.set(1, (rests.get(1) * operand) % 11);
                    rests.set(2, (rests.get(2) * operand) % 7);
                    rests.set(3, (rests.get(3) * operand) % 2);
                    rests.set(4, (rests.get(4) * operand) % 19);
                    rests.set(5, (rests.get(5) * operand) % 5);
                    rests.set(6, (rests.get(6) * operand) % 17);
                    rests.set(7, (rests.get(7) * operand) % 13);
                }
            } else {
                throw new RuntimeException();
            }
        }
        throwFancyItems(monkey);
    }

    private static void processItems(Monkey monkey, int myWorryDivisor) {
        for (int i = 0; i < monkey.getItems().size(); i++) {
            Long item = monkey.getItems().get(i);
            if (item < 0) {
                throw new RuntimeException();
            }
            monkey.setInspectCount(monkey.getInspectCount() + 1);
            if (monkey.getOperationOperator().equals("+")) {
                if (monkey.getOperationOperand().equals("old")) {
                    monkey.getItems().set(i, (item + item) / myWorryDivisor);
                } else {
                    int operand = Integer.parseInt(monkey.getOperationOperand());
                    monkey.getItems().set(i, (item + operand) / myWorryDivisor);
                }
            } else if (monkey.getOperationOperator().equals("*")) {
                if (monkey.getOperationOperand().equals("old")) {
                    monkey.getItems().set(i, (item * item) / myWorryDivisor);
                } else {
                    int operand = Integer.parseInt(monkey.getOperationOperand());
                    monkey.getItems().set(i, (item * operand) / myWorryDivisor);
                }
            } else {
                throw new RuntimeException();
            }
        }
        throwItems(monkey);
    }

    private static void throwItems(Monkey monkey) {
        for (Long item : monkey.getItems()) {
            Monkey targetMonkey;
            if (item % monkey.getDivisor() == 0) {
                targetMonkey = monkeys.get(monkey.getTrueTarget());
            } else {
                targetMonkey = monkeys.get(monkey.getFalseTarget());
            }
            targetMonkey.getItems().add(item);
        }
        monkey.setItems(new ArrayList());
    }

    private static void throwFancyItems(FancyMonkey monkey) {
        for (List<Integer> item : monkey.getItems()) {
            FancyMonkey targetMonkey;
            boolean decision;
            if (monkey.getDivisor() == 3) {
                decision = item.get(0) == 0;
            } else if (monkey.getDivisor() == 11) {
                decision = item.get(1) == 0;
            } else if (monkey.getDivisor() == 7) {
                decision = item.get(2) == 0;
            } else if (monkey.getDivisor() == 2) {
                decision = item.get(3) == 0;
            } else if (monkey.getDivisor() == 19) {
                decision = item.get(4) == 0;
            } else if (monkey.getDivisor() == 5) {
                decision = item.get(5) == 0;
            } else if (monkey.getDivisor() == 17) {
                decision = item.get(6) == 0;
            } else if (monkey.getDivisor() == 13) {
                decision = item.get(7) == 0;
            } else {
                throw new RuntimeException();
            }
            if (decision) {
                targetMonkey = fancyMonkeys.get(monkey.getTrueTarget());
            } else {
                targetMonkey = fancyMonkeys.get(monkey.getFalseTarget());
            }
            targetMonkey.getItems().add(item);
        }
        monkey.setItems(new ArrayList());
    }
}
