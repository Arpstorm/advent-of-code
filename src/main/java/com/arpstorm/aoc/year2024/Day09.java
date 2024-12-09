package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Day09 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2024/input.day09.txt");

        long part1 = solvePart1(input.get(0));
        long part2 = solvePart2(input.get(0));

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(String input) {
        List<Integer> blocks = expandNotation(input);
        List<Integer> rearrangedBlocks = moveBlocks(blocks);
        long result = 0;
        for (int i = 0; i < rearrangedBlocks.size(); i++) {
            result += (long) i * ((long) rearrangedBlocks.get(i));
        }
        return result;
    }

    public static long solvePart2(String input) {
        List<File> files = convertToFileNotation(input);
        rearrangeFiles(files);
        long result = 0;
        int index = 0;
        for (File file : files) {
            for (int i = 0; i < file.getSize(); i++) {
                if (file.getId() != null) {
                    result += (long) index * ((long) file.getId());
                }
                index++;
            }
        }
        return result;
    }

    private static List<File> convertToFileNotation(String input) {
        List<File> files = new ArrayList<>();
        int fileId = 0;
        for (int i = 0; i < input.toCharArray().length; i = i + 2) {
            int fileSize = Character.getNumericValue(input.charAt(i));
            files.add(new File(fileId, fileSize));
            if (i + 1 < input.toCharArray().length) {
                int freeSpaceSize = Character.getNumericValue(input.charAt(i + 1));
                if (freeSpaceSize > 0) {
                    files.add(new File(null, freeSpaceSize));
                }
            }
            fileId++;
        }
        return files;
    }

    private static void rearrangeFiles(List<File> files) {
        for (int i = files.size() - 1; i >= 0; i--) {
            File moveCandidate = files.get(i);
            if (moveCandidate.getId() == null) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                File checkedFile = files.get(j);
                if (checkedFile.getId() == null && moveCandidate.getSize() <= checkedFile.getSize()) {
                    if (moveCandidate.getSize() == checkedFile.getSize()) {
                        checkedFile.setId(moveCandidate.getId());
                        checkedFile.setSize(moveCandidate.getSize());
                    } else {
                        int remainingSpace = checkedFile.getSize() - moveCandidate.getSize();
                        checkedFile.setId(moveCandidate.getId());
                        checkedFile.setSize(moveCandidate.getSize());
                        files.add(j + 1, new File(null, remainingSpace));
                        i++;
                    }
                    moveCandidate.setId(null);
                    break;
                }
            }
        }
    }

    private static List<Integer> moveBlocks(List<Integer> blocks) {
        List<Integer> rearrangedBlocks = new ArrayList<>();
        int frontIndex = 0;
        int backIndex = blocks.size() - 1;
        while (frontIndex < backIndex) {
            if (blocks.get(frontIndex) == null) {
                while (blocks.get(backIndex) == null && frontIndex < backIndex) {
                    backIndex--;
                }
                rearrangedBlocks.add(blocks.get(backIndex));
                backIndex--;
                frontIndex++;
            } else {
                rearrangedBlocks.add(blocks.get(frontIndex));
                frontIndex++;
            }
        }
        return rearrangedBlocks;
    }

    private static List<Integer> expandNotation(String input) {
        List<Integer> blocks = new ArrayList<>();
        int fileId = 0;
        for (int i = 0; i < input.toCharArray().length; i = i + 2) {
            int fileSize = Character.getNumericValue(input.charAt(i));
            for (int j = 0; j < fileSize; j++) {
                blocks.add(fileId);
            }
            if (i + 1 < input.toCharArray().length) {
                int freeSpaceSize = Character.getNumericValue(input.charAt(i + 1));
                for (int j = 0; j < freeSpaceSize; j++) {
                    blocks.add(null);
                }
            }
            fileId++;
        }
        return blocks;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class File {
        private Integer id;
        private int size;
    }
}
