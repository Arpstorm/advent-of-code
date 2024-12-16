package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day15 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day15.txt");

        int part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        Grid grid = parseGrid(input);
        Point robot = findRobot(grid);
        List<String> moves = parseMoves(input);
        for (String move : moves) {
            moveRobot(robot, grid, move);
        }
        int result = 0;
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.getValue(x, y).equals("O")) {
                    result+= x + 100 * y;
                }
            }
        }
        return result;
    }

    public static int solvePart2(List<String> input) {
        Grid grid = parseGrid(input);
        grid = extendGrid(grid);
        Point robot = findRobot(grid);
        List<String> moves = parseMoves(input);
        for (String move : moves) {
            moveSecondRobot(robot, grid, move);
        }
        int result = 0;
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.getValue(x, y).equals("[")) {
                    result += x + 100 * y;
                }
            }
        }
        return result;
    }

    private static void moveSecondRobot(Point robot, Grid grid, String move) {
        if (move.equals("<")) {
            String target = grid.getValue(robot.getX() - 1, robot.getY());
            if (target.equals(".")) {
                grid.setValue(robot.getX() - 1, robot.getY(), "@");
                grid.setValue(robot.getX(), robot.getY(), ".");
                robot.setX(robot.getX() - 1);
            } else if (target.equals("[") || target.equals("]")) {
                for (int xPos = robot.getX() - 1; xPos > 0; xPos--) {
                    if (grid.getValue(xPos, robot.getY()).equals(".")) {
                        for (int shiftIdx = xPos + 1; shiftIdx < robot.getX(); shiftIdx++) {
                            grid.setValue(shiftIdx - 1, robot.getY(), grid.getValue(shiftIdx, robot.getY()));
                        }
                        grid.setValue(robot.getX() - 1, robot.getY(), "@");
                        grid.setValue(robot.getX(), robot.getY(), ".");
                        robot.setX(robot.getX() - 1);
                        break;
                    } else if (grid.getValue(xPos, robot.getY()).equals("#")) {
                        break;
                    }
                }
            }
        } else if (move.equals(">")) {
            String target = grid.getValue(robot.getX() + 1, robot.getY());
            if (target.equals(".")) {
                grid.setValue(robot.getX() + 1, robot.getY(), "@");
                grid.setValue(robot.getX(), robot.getY(), ".");
                robot.setX(robot.getX() + 1);
            } else if (target.equals("[") || target.equals("]")) {
                for (int xPos = robot.getX() + 1; xPos < grid.getWidth(); xPos++) {
                    if (grid.getValue(xPos, robot.getY()).equals(".")) {
                        for (int shiftIdx = xPos - 1; shiftIdx > robot.getX(); shiftIdx--) {
                            grid.setValue(shiftIdx + 1, robot.getY(), grid.getValue(shiftIdx, robot.getY()));
                        }
                        grid.setValue(robot.getX() + 1, robot.getY(), "@");
                        grid.setValue(robot.getX(), robot.getY(), ".");
                        robot.setX(robot.getX() + 1);
                        break;
                    } else if (grid.getValue(xPos, robot.getY()).equals("#")) {
                        break;
                    }
                }
            }
        } else if (move.equals("^")) {
            String target = grid.getValue(robot.getX(), robot.getY() - 1);
            List<Point> boxPoints = new ArrayList<>();
            if (target.equals(".")) {
                grid.setValue(robot.getX(), robot.getY() - 1, "@");
                grid.setValue(robot.getX(), robot.getY(), ".");
                robot.setY(robot.getY() - 1);
            }
            if (target.equals("[")) {
                try {
                    addBoxPoints(boxPoints, new Point(robot.getX(), robot.getY() - 1), new Point(robot.getX() + 1, robot.getY() - 1), grid, -1);
                    moveBoxPoints(boxPoints, grid, -1);
                    grid.setValue(robot.getX(), robot.getY() - 1, "@");
                    grid.setValue(robot.getX(), robot.getY(), ".");
                    robot.setY(robot.getY() - 1);
                } catch (Exception e) {
                    // Box is blocked from above
                    boxPoints.clear();
                }
            }
            if (target.equals("]")) {
                try {
                    addBoxPoints(boxPoints, new Point(robot.getX() - 1, robot.getY() - 1), new Point(robot.getX(), robot.getY() - 1), grid, -1);
                    moveBoxPoints(boxPoints, grid, -1);
                    grid.setValue(robot.getX(), robot.getY() - 1, "@");
                    grid.setValue(robot.getX(), robot.getY(), ".");
                    robot.setY(robot.getY() - 1);
                } catch (Exception e) {
                    // Box is blocked from above
                    boxPoints.clear();
                }
            }
        } else if (move.equals("v")) {
            String target = grid.getValue(robot.getX(), robot.getY() + 1);
            List<Point> boxPoints = new ArrayList<>();
            if (target.equals(".")) {
                grid.setValue(robot.getX(), robot.getY() + 1, "@");
                grid.setValue(robot.getX(), robot.getY(), ".");
                robot.setY(robot.getY() + 1);
            }
            if (target.equals("[")) {
                try {
                    addBoxPoints(boxPoints, new Point(robot.getX(), robot.getY() + 1), new Point(robot.getX() + 1, robot.getY() + 1), grid, 1);
                    moveBoxPoints(boxPoints, grid, 1);
                    grid.setValue(robot.getX(), robot.getY() + 1, "@");
                    grid.setValue(robot.getX(), robot.getY(), ".");
                    robot.setY(robot.getY() + 1);
                } catch (Exception e) {
                    // Box is blocked from above
                    boxPoints.clear();
                }
            }
            if (target.equals("]")) {
                try {
                    addBoxPoints(boxPoints, new Point(robot.getX() - 1, robot.getY() + 1), new Point(robot.getX(), robot.getY() + 1), grid, 1);
                    moveBoxPoints(boxPoints, grid, 1);
                    grid.setValue(robot.getX(), robot.getY() + 1, "@");
                    grid.setValue(robot.getX(), robot.getY(), ".");
                    robot.setY(robot.getY() + 1);
                } catch (Exception e) {
                    // Box is blocked from above
                    boxPoints.clear();
                }
            }
        }
    }

    private static void moveBoxPoints(List<Point> boxPoints, Grid grid, int direction) {
        if (direction == -1) {
            for (int x = 0; x < grid.getWidth(); x++) {
                for (int y = 0; y < grid.getHeight(); y++) {
                    for (Point boxPoint : boxPoints) {
                        if (boxPoint.getX() == x && boxPoint.getY() == y) {
                            grid.setValue(x, y - 1, grid.getValue(x, y));
                            grid.setValue(x, y, ".");
                        }
                    }
                }
            }
        } else {
            for (int x = 0; x < grid.getWidth(); x++) {
                for (int y = grid.getHeight() - 1; y >= 0; y--) {
                    for (Point boxPoint : boxPoints) {
                        if (boxPoint.getX() == x && boxPoint.getY() == y) {
                            grid.setValue(x, y + 1, grid.getValue(x, y));
                            grid.setValue(x, y, ".");
                        }
                    }
                }
            }

        }
    }

    private static void addBoxPoints(List<Point> boxPoints, Point leftBoxPt, Point rightBoxPt, Grid grid, int direction) {
        boolean containsLeft = false;
        boolean containsRight = false;
        for (Point boxPoint : boxPoints) {
            if (boxPoint.getX() == leftBoxPt.getX() && boxPoint.getY() == leftBoxPt.getY()) {
                containsLeft = true;
            }
            if (boxPoint.getX() == rightBoxPt.getX() && boxPoint.getY() == rightBoxPt.getY()) {
                containsRight = true;
            }
        }
        if (containsLeft && containsRight) {
            return;
        }
        if (!containsLeft) {
            boxPoints.add(leftBoxPt);
        }
        if (!containsRight) {
            boxPoints.add(rightBoxPt);
        }
        if (direction == -1) {
            if (grid.getValue(leftBoxPt.getX(), leftBoxPt.getY() - 1).equals("#")
                    || grid.getValue(rightBoxPt.getX(), rightBoxPt.getY() - 1).equals("#")) {
                throw new IllegalStateException("Box is blocked from above");
            }
            if (grid.getValue(leftBoxPt.getX(), leftBoxPt.getY() - 1).equals("[")) {
                addBoxPoints(boxPoints, new Point(leftBoxPt.getX(), leftBoxPt.getY() - 1), new Point(rightBoxPt.getX(), rightBoxPt.getY() - 1), grid, direction);
            }
            if (grid.getValue(leftBoxPt.getX(), leftBoxPt.getY() - 1).equals("]")) {
                addBoxPoints(boxPoints, new Point(leftBoxPt.getX() - 1, leftBoxPt.getY() - 1), new Point(leftBoxPt.getX(), rightBoxPt.getY() - 1), grid, direction);
            }
            if (grid.getValue(rightBoxPt.getX(), rightBoxPt.getY() - 1).equals("[")) {
                addBoxPoints(boxPoints, new Point(rightBoxPt.getX(), rightBoxPt.getY() - 1), new Point(rightBoxPt.getX() + 1, rightBoxPt.getY() - 1), grid, direction);
            }
        } else {
            if (grid.getValue(leftBoxPt.getX(), leftBoxPt.getY() + 1).equals("#")
                    || grid.getValue(rightBoxPt.getX(), rightBoxPt.getY() + 1).equals("#")) {
                throw new IllegalStateException("Box is blocked from below");
            }
            if (grid.getValue(leftBoxPt.getX(), leftBoxPt.getY() + 1).equals("[")) {
                addBoxPoints(boxPoints, new Point(leftBoxPt.getX(), leftBoxPt.getY() + 1), new Point(rightBoxPt.getX(), rightBoxPt.getY() + 1), grid, direction);
            }
            if (grid.getValue(leftBoxPt.getX(), leftBoxPt.getY() + 1).equals("]")) {
                addBoxPoints(boxPoints, new Point(leftBoxPt.getX() - 1, leftBoxPt.getY() + 1), new Point(leftBoxPt.getX(), rightBoxPt.getY() + 1), grid, direction);
            }
            if (grid.getValue(rightBoxPt.getX(), rightBoxPt.getY() + 1).equals("[")) {
                addBoxPoints(boxPoints, new Point(rightBoxPt.getX(), rightBoxPt.getY() + 1), new Point(rightBoxPt.getX() + 1, rightBoxPt.getY() + 1), grid, direction);
            }
        }
    }

    private static Grid extendGrid(Grid grid) {
        Grid newGrid = new Grid(2 * grid.getWidth(), grid.getHeight());
        for (int x = 0; x < 2 * grid.getWidth(); x = x + 2) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.getValue(x / 2, y).equals("O")) {
                    newGrid.setValue(x, y, "[");
                    newGrid.setValue(x + 1, y, "]");
                } else if (grid.getValue(x / 2, y).equals("@")) {
                    newGrid.setValue(x, y, "@");
                    newGrid.setValue(x + 1, y, ".");
                } else {
                    newGrid.setValue(x, y, grid.getValue(x / 2, y));
                    newGrid.setValue(x + 1, y, grid.getValue(x / 2, y));
                }
            }
        }
        return newGrid;
    }

    private static Point findRobot(Grid grid) {
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.getValue(x, y).equals("@")) {
                    return new Point(x, y);
                }
            }
        }
        return null;
    }

    private static void moveRobot(Point robot, Grid grid, String move) {
        if (move.equals("<")) {
            String target = grid.getValue(robot.getX() - 1, robot.getY());
            if (target.equals(".")) {
                grid.setValue(robot.getX() - 1, robot.getY(), "@");
                grid.setValue(robot.getX(), robot.getY(), ".");
                robot.setX(robot.getX() - 1);
            } else if (target.equals("O")) {
                for (int xPos = robot.getX() - 1; xPos > 0; xPos--) {
                    if (grid.getValue(xPos, robot.getY()).equals(".")) {
                        grid.setValue(xPos, robot.getY(), "O");
                        grid.setValue(robot.getX() - 1, robot.getY(), "@");
                        grid.setValue(robot.getX(), robot.getY(), ".");
                        robot.setX(robot.getX() - 1);
                        break;
                    } else if (grid.getValue(xPos, robot.getY()).equals("#")) {
                        break;
                    }
                }
            }
        } else if (move.equals(">")) {
            String target = grid.getValue(robot.getX() + 1, robot.getY());
            if (target.equals(".")) {
                grid.setValue(robot.getX() + 1, robot.getY(), "@");
                grid.setValue(robot.getX(), robot.getY(), ".");
                robot.setX(robot.getX() + 1);
            } else if (target.equals("O")) {
                for (int xPos = robot.getX() + 1; xPos < grid.getWidth(); xPos++) {
                    if (grid.getValue(xPos, robot.getY()).equals(".")) {
                        grid.setValue(xPos, robot.getY(), "O");
                        grid.setValue(robot.getX() + 1, robot.getY(), "@");
                        grid.setValue(robot.getX(), robot.getY(), ".");
                        robot.setX(robot.getX() + 1);
                        break;
                    } else if (grid.getValue(xPos, robot.getY()).equals("#")) {
                        break;
                    }
                }
            }
        } else if (move.equals("^")) {
            String target = grid.getValue(robot.getX(), robot.getY() - 1);
            if (target.equals(".")) {
                grid.setValue(robot.getX(), robot.getY() - 1, "@");
                grid.setValue(robot.getX(), robot.getY(), ".");
                robot.setY(robot.getY() - 1);
            } else if (target.equals("O")) {
                for (int yPos = robot.getY() - 1; yPos > 0; yPos--) {
                    if (grid.getValue(robot.getX(), yPos).equals(".")) {
                        grid.setValue(robot.getX(), yPos, "O");
                        grid.setValue(robot.getX(), robot.getY() - 1, "@");
                        grid.setValue(robot.getX(), robot.getY(), ".");
                        robot.setY(robot.getY() - 1);
                        break;
                    } else if (grid.getValue(robot.getX(), yPos).equals("#")) {
                        break;
                    }
                }
            }
        } else if (move.equals("v")) {
            String target = grid.getValue(robot.getX(), robot.getY() + 1);
            if (target.equals(".")) {
                grid.setValue(robot.getX(), robot.getY() + 1, "@");
                grid.setValue(robot.getX(), robot.getY(), ".");
                robot.setY(robot.getY() + 1);
            } else if (target.equals("O")) {
                for (int yPos = robot.getY() + 1; yPos < grid.getHeight(); yPos++) {
                    if (grid.getValue(robot.getX(), yPos).equals(".")) {
                        grid.setValue(robot.getX(), yPos, "O");
                        grid.setValue(robot.getX(), robot.getY() + 1, "@");
                        grid.setValue(robot.getX(), robot.getY(), ".");
                        robot.setY(robot.getY() + 1);
                        break;
                    } else if (grid.getValue(robot.getX(), yPos).equals("#")) {
                        break;
                    }
                }
            }
        }
    }

    private static List<String> parseMoves(List<String> input) {
        List<String> moves = new ArrayList<>();
        boolean movesStart = false;
        for (int i = 0; i < input.size(); i++) {
            if (movesStart) {
                for (int j = 0; j < input.get(i).length(); j++) {
                    moves.add(input.get(i).substring(j, j + 1));
                }
            }
            if (input.get(i).isEmpty()) {
                movesStart = true;
            }
        }
        return moves;
    }

    private static Grid parseGrid(List<String> input) {
        Grid grid = null;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).isEmpty()) {
                grid = new Grid(input.subList(0, i));
                return grid;
            }
        }
        return grid;
    }

    public static class Grid {
        private List<List<String>> grid;

        public Grid(int width, int height) {
            grid = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                List<String> row = new ArrayList<>();
                for (int x = 0; x < width; x++) {
                    row.add("");
                }
                grid.add(row);
            }
        }
        public Grid(List<String> input) {
            grid = new ArrayList<>();
            for (String line : input) {
                List<String> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    row.add(String.valueOf(line.charAt(i)));
                }
                grid.add(row);
            }
        }
        public int getWidth() {
            return grid.get(0).size();
        }
        public int getHeight() {
            return grid.size();
        }
        public void setValue(int x, int y, String value) {
            grid.get(y).set(x, value);
        }
        public void setValue(Day10.Point point, String value) {
            grid.get(point.getY()).set(point.getX(), value);
        }
        public String getValue(int x, int y) {
            return grid.get(y).get(x);
        }
        public String getValue(Day10.Point point) {
            return grid.get(point.getY()).get(point.getX());
        }
        public List<String> getRow(int y) {
            return grid.get(y);
        }
        public void setRow(int y, List<String> row) {
            grid.set(y, row);
        }
        public List<String> getColumn(int x) {
            List<String> column = new ArrayList<>();
            for (int y = 0; y < grid.size(); y++) {
                column.add(grid.get(y).get(x));
            }
            return column;
        }
        public void setColumn(int x, List<String> column) {
            for (int y = 0; y < column.size(); y++) {
                grid.get(y).set(x, column.get(y));
            }
        }
        public void drawGrid() {
            for (int y = 0; y < grid.size(); y++) {
                for (int x = 0; x < grid.get(0).size(); x++) {
                    System.out.print(grid.get(y).get(x));
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static class Point {
        private int x;
        private int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
    }
}
