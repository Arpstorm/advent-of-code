package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.List;

public class Day09 {

    private static final int gridSize = 1000;
    private static final int startX = 500;
    private static final int startY = 500;
    private static boolean[][] tailTrace;
    private static final boolean debugOutput = false;

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day09.txt");

        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }

    public static int solvePart1(List<String> lines) {
        GridPosition head = new GridPosition(startX, startY);
        GridPosition tail = new GridPosition(startX, startY);
        displayPosition(head, tail, gridSize);
        tailTrace = new boolean[gridSize][gridSize];
        tailTrace[startX][startY] = true;
        for (String line : lines) {
            String direction = line.split(" ")[0];
            int amount = Integer.parseInt(line.split(" ")[1]);
            for (int i = 0; i < amount; i++) {
                moveHead(head, direction);
                moveAlong(head, tail);
                tailTrace[tail.getX()][tail.getY()] = true;
                displayPosition(head, tail, gridSize);
            }
        }
        return getTailVisited();
    }

    public static int solvePart2(List<String> lines) {
        GridPosition head = new GridPosition(startX, startY);
        GridPosition k1 = new GridPosition(startX, startY);
        GridPosition k2 = new GridPosition(startX, startY);
        GridPosition k3 = new GridPosition(startX, startY);
        GridPosition k4 = new GridPosition(startX, startY);
        GridPosition k5 = new GridPosition(startX, startY);
        GridPosition k6 = new GridPosition(startX, startY);
        GridPosition k7 = new GridPosition(startX, startY);
        GridPosition k8 = new GridPosition(startX, startY);
        GridPosition tail = new GridPosition(startX, startY);
        displayPosition(head, tail, gridSize);
        tailTrace = new boolean[gridSize][gridSize];
        tailTrace[startX][startY] = true;
        for (String line : lines) {
            String direction = line.split(" ")[0];
            int amount = Integer.parseInt(line.split(" ")[1]);
            for (int i = 0; i < amount; i++) {
                moveHead(head, direction);
                moveAlong(head, k1);
                moveAlong(k1, k2);
                moveAlong(k2, k3);
                moveAlong(k3, k4);
                moveAlong(k4, k5);
                moveAlong(k5, k6);
                moveAlong(k6, k7);
                moveAlong(k7, k8);
                moveAlong(k8, tail);
                tailTrace[tail.getX()][tail.getY()] = true;
                displayPosition(head, k1, k2, k3, k4, k5, k6, k7, k8, tail, gridSize);
            }
        }
        return getTailVisited();
    }

    private static int getTailVisited() {
        int tailVisited = 0;
        for (int y = gridSize - 1; y >= 0; y--) {
            for (int x = 0; x < gridSize; x++) {
                if (tailTrace[x][y]) {
                    if (debugOutput) {
                        System.out.print("#");
                    }
                    tailVisited++;
                } else {
                    if (debugOutput) {
                        System.out.print(".");
                    }
                }
            }
            if (debugOutput) {
                System.out.println();
            }
        }
        return tailVisited;
    }

    private static void moveAlong(GridPosition head, GridPosition tail) {
        int deltaX = head.getX() - tail.getX();
        int deltaY = head.getY() - tail.getY();
        if (Math.abs(deltaX) > 1) {
            tail.setX(tail.getX() + Integer.signum(deltaX));
            if (Math.abs(deltaY) > 0) {
                tail.setY(tail.getY() + Integer.signum(deltaY));
            }
        } else if (Math.abs(deltaY) > 1) {
            tail.setY(tail.getY() + Integer.signum(deltaY));
            if (Math.abs(deltaX) > 0) {
                tail.setX(tail.getX() + Integer.signum(deltaX));
            }
        }
    }

    private static void displayPosition(GridPosition head, GridPosition tail, int maxSize) {
        if (debugOutput) {
            for (int y = maxSize - 1; y >= 0; y--) {
                for (int x = 0; x < maxSize; x++) {
                    if (head.getX() == x && head.getY() == y) {
                        System.out.print("H");
                    } else if (tail.getX() == x && tail.getY() == y) {
                        System.out.print("T");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private static void displayPosition(GridPosition head, GridPosition k1, GridPosition k2, GridPosition k3, GridPosition k4, GridPosition k5, GridPosition k6, GridPosition k7, GridPosition k8, GridPosition tail, int maxSize) {
        if (debugOutput) {
            for (int y = maxSize - 1; y >= 0; y--) {
                for (int x = 0; x < maxSize; x++) {
                    if (head.getX() == x && head.getY() == y) {
                        System.out.print("H");
                    } else if (k1.getX() == x && k1.getY() == y) {
                        System.out.print("1");
                    } else if (k2.getX() == x && k2.getY() == y) {
                        System.out.print("2");
                    } else if (k3.getX() == x && k3.getY() == y) {
                        System.out.print("3");
                    } else if (k4.getX() == x && k4.getY() == y) {
                        System.out.print("4");
                    } else if (k5.getX() == x && k5.getY() == y) {
                        System.out.print("5");
                    } else if (k6.getX() == x && k6.getY() == y) {
                        System.out.print("6");
                    } else if (k7.getX() == x && k7.getY() == y) {
                        System.out.print("7");
                    } else if (k8.getX() == x && k8.getY() == y) {
                        System.out.print("8");
                    } else if (tail.getX() == x && tail.getY() == y) {
                        System.out.print("T");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private static void moveHead(GridPosition head, String direction) {
        switch (direction) {
            case "R":
                head.setX(head.getX() + 1);
                break;
            case "L":
                head.setX(head.getX() - 1);
                break;
            case "U":
                head.setY(head.getY() + 1);
                break;
            case "D":
                head.setY(head.getY() - 1);
                break;
            default:
                throw new RuntimeException();
        }
        if (head.getX() < 0 || head.getY() < 0) {
            throw new RuntimeException();
        }
    }
}
