package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.*;

public class Day07 {

    private static Integer totalSum = 0;
    private static List<Integer> totalSizeList = new ArrayList<>();

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day07.txt");

        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }

    public static int solvePart1(List<String> lines) {
        TreeNode<String> root = getDirectoryTree(lines);
        int totalSize = getTotalSizeOfNode(root);
        root.setTotalSizes(totalSize);
        totalSum = 0;
        sumUpSizes(root, 100000);
        return totalSum;
    }

    public static int solvePart2(List<String> lines) {
        TreeNode<String> root = getDirectoryTree(lines);
        int totalSize = getTotalSizeOfNode(root);
        root.setTotalSizes(totalSize);
        int spaceAvailable = 70000000 - totalSize;
        int spaceToDelete  = 30000000 - spaceAvailable;
        getTotalSizeList(root, spaceToDelete);
        return Collections.min(totalSizeList);
    }

    private static void getTotalSizeList(TreeNode<String> node, int spaceRequired) {
        if (node.getTotalSize() > spaceRequired) {
            totalSizeList.add(node.getTotalSize());
        }
        for (TreeNode<String> child :  node.getChildren()) {
            getTotalSizeList(child, spaceRequired);
        }
    }

    private static void sumUpSizes(TreeNode<String> node, int limit) {
        int totalSize = node.getTotalSize();
        if (totalSize <= limit) {
            totalSum += totalSize;
        }
        for (TreeNode child : node.getChildren()) {
            sumUpSizes(child, limit);
        }
    }

    private static int getTotalSizeOfNode(TreeNode<String> node) {
        if (!node.getChildren().isEmpty()) {
            int totalSizeChildren = 0;
            for (TreeNode child : node.getChildren()) {
                int totalSize = getTotalSizeOfNode(child);
                child.setTotalSizes(totalSize);
                totalSizeChildren += totalSize;
            }
            return node.getSize() + totalSizeChildren;
        } else {
            return node.getSize();
        }
    }

    private static TreeNode<String> getDirectoryTree(List<String> lines) {
        TreeNode<String> root = null;
        TreeNode<String> currentDir = null;
        for (String line : lines) {
            if (line.equals("$ cd /")) {
                root = new TreeNode("/");
                currentDir = root;
            } else if (line.startsWith("$ cd ") && !line.startsWith("$ cd /") && !line.equals("$ cd ..")) {
                TreeNode<String> leaf = new TreeNode<>(line.substring(5));
                currentDir.addChild(leaf);
                currentDir = leaf;
            } else if (line.equals("$ cd ..")) {
                currentDir = currentDir.getParent();
            } else if (!line.equals("$ ls")) {
                if (!line.startsWith("dir")) {
                    currentDir.setSize(currentDir.getSize() + Integer.parseInt(line.split(" ")[0]));
                }
            }
        }
        return root;
    }
}
