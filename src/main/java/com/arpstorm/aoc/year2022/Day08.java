package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day08 {

    private static Integer totalSum = 0;
    private static List<Integer> totalSizeList = new ArrayList<>();

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day08.txt");

        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }

    public static int solvePart2(List<String> lines) {
        int[][] scenicScore = new int[lines.size()][lines.get(0).length()];
        // Init grid
        List<List<Integer>> grid = new ArrayList<>();
        for (String line : lines) {
            List<Integer> row = new ArrayList<>();
            grid.add(row);
            for (Character character : line.toCharArray()) {
                row.add(Integer.parseInt(character.toString()));
            }
        }
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                scenicScore[i][j] = getScenicScore(i, j, grid);
            }
        }
        int maxScenicScore = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                if (scenicScore[i][j] > maxScenicScore) {
                    maxScenicScore = scenicScore[i][j];
                }
            }
        }
        return maxScenicScore;
    }

    private static int getScenicScore(int i, int j, List<List<Integer>> grid) {
        int scoreLeft = 0;
        int scoreRight = 0;
        int scoreTop = 0;
        int scoreBottom = 0;
        // Iterate over columns left: j is column index
        for (int left = j - 1; left >= 0; left--) {
            if (grid.get(i).get(left) < grid.get(i).get(j)) {
                scoreLeft++;
            } else {
                scoreLeft++;
                break;
            }
        }
        // Iterate over columns right: j is column index
        for (int right = j + 1; right < grid.size(); right++) {
            if (grid.get(i).get(right) < grid.get(i).get(j)) {
                scoreRight++;
            } else {
                scoreRight++;
                break;
            }
        }
        // Iterate over rows above: i is row index
        for (int above = i - 1; above >= 0; above--) {
            if (grid.get(above).get(j) < grid.get(i).get(j)) {
                scoreTop++;
            } else {
                scoreTop++;
                break;
            }
        }
        // Iterate over rows below: i is row index
        for (int below = i + 1; below < grid.get(0).size(); below++) {
            if (grid.get(below).get(j) < grid.get(i).get(j)) {
                scoreBottom++;
            } else {
                scoreBottom++;
                break;
            }
        }
        return scoreLeft * scoreRight * scoreTop * scoreBottom;
    }

    public static int solvePart1(List<String> lines) {
        // Init visible array
        int[][] visible = new int[lines.size()][lines.get(0).length()];
        Arrays.fill(visible[0], 1);
        Arrays.fill(visible[lines.size() - 1], 1);
        for (int i = 0; i < lines.get(0).length(); i++) {
            visible[i][0] = 1;
            visible[i][lines.size() - 1] = 1;
        }
        // Init grid
        List<List<Integer>> grid = new ArrayList<>();
        for (String line : lines) {
            List<Integer> row = new ArrayList<>();
            grid.add(row);
            for (Character character : line.toCharArray()) {
                row.add(Integer.parseInt(character.toString()));
            }
        }
        visibleFromLeft(grid, visible);
        visibleFromRight(grid, visible);
        visibleFromTop(grid, visible);
        visibleFromBottom(grid, visible);
        return getVisibleTrees(visible);
    }

    private static int getVisibleTrees(int[][] visible) {
        int sum = 0;
        for (int[] row : visible) {
            for (int cell : row) {
                if (cell == 1) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static void visibleFromLeft(List<List<Integer>> grid, int[][] visible) {
        for (int i = 1; i < grid.size() - 1; i++) {
            List<Integer> row = grid.get(i);
            for (int j = 1; j < row.size() - 1; j++) {
                boolean vis = true;
                for (int k = 0; k < j; k++) {
                    if (row.get(j) <= row.get(k)) {
                        vis = false;
                        break;
                    }
                }
                if (vis) {
                    visible[i][j] = 1;
                }
            }
        }
    }

    private static void visibleFromRight(List<List<Integer>> grid, int[][] visible) {
        for (int i = 1; i < grid.size() - 1; i++) {
            List<Integer> row = grid.get(i);
            for (int j = row.size() - 2; j > 0; j--) {
                boolean vis = true;
                for (int k = row.size() - 1; k > j; k--) {
                    if (row.get(j) <= row.get(k)) {
                        vis = false;
                        break;
                    }
                }
                if (vis) {
                    visible[i][j] = 1;
                }
            }
        }
    }

    private static void visibleFromTop(List<List<Integer>> grid, int[][] visible) {
        // Iterate over columns: i is column index
        for (int i = 1; i < grid.get(0).size() - 1; i++) {
            List<Integer> col = new ArrayList<>();
            for (List<Integer> row : grid) {
                col.add(row.get(i));
            }
            // Iterate over rows: j is row index
            for (int j = 1; j < col.size() - 1; j++) {
                boolean vis = true;
                for (int k = 0; k < j; k++) {
                    if (col.get(j) <= col.get(k)) {
                        vis = false;
                        break;
                    }
                }
                if (vis) {
                    visible[j][i] = 1;
                }
            }
        }
    }

    private static void visibleFromBottom(List<List<Integer>> grid, int[][] visible) {
        // Iterate over columns: i is column index
        for (int i = 1; i < grid.get(0).size() - 1; i++) {
            List<Integer> col = new ArrayList<>();
            for (List<Integer> row : grid) {
                col.add(row.get(i));
            }
            // Iterate over rows: j is row index
            for (int j = col.size() - 2; j > 0; j--) {
                boolean vis = true;
                for (int k = col.size() - 1; k > j; k--) {
                    if (col.get(j) <= col.get(k)) {
                        vis = false;
                        break;
                    }
                }
                if (vis) {
                    visible[j][i] = 1;
                }
            }
        }
    }
}
