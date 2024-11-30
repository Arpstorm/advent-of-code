package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;

import java.util.*;

public class Day06 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2022/input.day06.txt");

        System.out.println("Part 1: " + solvePart1(input.get(0)));
        System.out.println("Part 2: " + solvePart2(input.get(0)));
        System.out.println("Part 2 alt: " + solvePart3(input.get(0)));
    }

    public static int solvePart1(String message) {
        int packetStart = -1;
        for (int i = 0; i < message.length() - 4; i++) {
            Set<Character> fourCharSet = new HashSet<>();
            fourCharSet.add(message.charAt(i));
            fourCharSet.add(message.charAt(i + 1));
            fourCharSet.add(message.charAt(i + 2));
            fourCharSet.add(message.charAt(i + 3));
            if (fourCharSet.size() == 4) {
                packetStart = i + 4;
                break;
            }
        }
        return packetStart;
    }

    public static int solvePart2(String message) {
        int messageStart = -1;
        for (int i = 0; i < message.length() - 14; i++) {
            Set<Character> fourteenCharSet = new HashSet<>();
            fourteenCharSet.add(message.charAt(i));
            fourteenCharSet.add(message.charAt(i + 1));
            fourteenCharSet.add(message.charAt(i + 2));
            fourteenCharSet.add(message.charAt(i + 3));
            fourteenCharSet.add(message.charAt(i + 4));
            fourteenCharSet.add(message.charAt(i + 5));
            fourteenCharSet.add(message.charAt(i + 6));
            fourteenCharSet.add(message.charAt(i + 7));
            fourteenCharSet.add(message.charAt(i + 8));
            fourteenCharSet.add(message.charAt(i + 9));
            fourteenCharSet.add(message.charAt(i + 10));
            fourteenCharSet.add(message.charAt(i + 11));
            fourteenCharSet.add(message.charAt(i + 12));
            fourteenCharSet.add(message.charAt(i + 13));
            if (fourteenCharSet.size() == 14) {
                messageStart = i + 14;
            }
        }
        return messageStart;
    }


    public static int solvePart3(String message) {
        List<Integer> packetStart = new ArrayList<>();
        List<Integer> messageStart = new ArrayList<>();
        for (int i = 0; i < message.length() - 4; i++) {
            Set<Character> fourCharSet = new HashSet<>();
            fourCharSet.add(message.charAt(i));
            fourCharSet.add(message.charAt(i + 1));
            fourCharSet.add(message.charAt(i + 2));
            fourCharSet.add(message.charAt(i + 3));
            if (fourCharSet.size() == 4) {
                packetStart.add(i + 4);
            }
        }
        for (int i = 0; i < message.length() - 14; i++) {
            Set<Character> fourteenCharSet = new HashSet<>();
            fourteenCharSet.add(message.charAt(i));
            fourteenCharSet.add(message.charAt(i + 1));
            fourteenCharSet.add(message.charAt(i + 2));
            fourteenCharSet.add(message.charAt(i + 3));
            fourteenCharSet.add(message.charAt(i + 4));
            fourteenCharSet.add(message.charAt(i + 5));
            fourteenCharSet.add(message.charAt(i + 6));
            fourteenCharSet.add(message.charAt(i + 7));
            fourteenCharSet.add(message.charAt(i + 8));
            fourteenCharSet.add(message.charAt(i + 9));
            fourteenCharSet.add(message.charAt(i + 10));
            fourteenCharSet.add(message.charAt(i + 11));
            fourteenCharSet.add(message.charAt(i + 12));
            fourteenCharSet.add(message.charAt(i + 13));
            if (fourteenCharSet.size() == 14) {
                return i + 14;
            }
        }
        throw new RuntimeException();
    }
}
