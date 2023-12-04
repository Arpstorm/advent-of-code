package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import lombok.Data;

import java.util.*;

public class Day03 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day03.txt");

        int part1 = solvePart1(input);
        int part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        int sum = 0;
        Map<String, Part> schematic = createInventory(input);
        List<Character> filterList = Arrays.asList('%','*','#','&','$','@','/','=','+','-');
        HashMap<String, Part> finalSchematic = filterForParts(input, schematic, filterList);
        for (Part part : finalSchematic.values()) {
            sum += Integer.parseInt(part.getNumber());
        }
        return sum;
    }

    public static int solvePart2(List<String> input) {
        int sum = 0;
        Map<String, Part> schematic = createInventory(input);
        List<Character> filterList = Arrays.asList('*');
        List<Integer> gearRatios = filterForGearRatios(input, schematic, filterList);
        for (int ratio : gearRatios) {
            sum += ratio;
        }
        return sum;
    }

    private static Map<String, Part> createInventory(List<String> input) {
        HashMap<String, Part> schematic = new HashMap<>();
        boolean workingOnExistingPart = false;
        int partsuffix = 0;
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                String line = input.get(y);
                if (line.toCharArray()[x] >= '0' && line.toCharArray()[x] <= '9') {
                    String partNumber = parseFullNumber(line, x);
                    if (!workingOnExistingPart) {
                        while (schematic.get(partNumber + "_" + partsuffix) != null) {
                            partsuffix++;
                        }
                        Part part = new Part(partNumber);
                        part.getCoordinates().add(new Point(x, y));
                        schematic.put(partNumber + "_" + partsuffix, part);
                        workingOnExistingPart = true;
                    } else {
                        schematic.get(partNumber + "_" + partsuffix).getCoordinates().add(new Point(x, y));
                    }
                } else {
                    workingOnExistingPart = false;
                    partsuffix = 0;
                }
            }
            workingOnExistingPart = false;
            partsuffix = 0;
        }
        return schematic;
    }

    private static HashMap<String, Part> filterForParts(List<String> input, Map<String, Part> schematic, List<Character> filterList) {
        HashMap<String, Part> finalSchematic = new HashMap<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.size(); x++) {
                String line = input.get(y);
                char character = line.toCharArray()[x];
                if (filterList.contains(character)) {
                    for (String key : schematic.keySet()) {
                        Part part = schematic.get(key);
                        for (Point p : part.coordinates) {
                            if ((p.x == x + 1 || p.x == x || p.x == x - 1) && (p.y == y + 1 || p.y == y || p.y == y - 1)) {
                                finalSchematic.put(key, part);
                            }
                        }
                    }
                }
            }
        }
        return finalSchematic;
    }

    private static List<Integer> filterForGearRatios(List<String> input, Map<String, Part> schematic, List<Character> filterList) {
        List<Integer> gears = new ArrayList<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.size(); x++) {
                String line = input.get(y);
                char character = line.toCharArray()[x];
                if (filterList.contains(character)) {
                    Set<Part> adjacentParts = new HashSet<>();
                    for (String key : schematic.keySet()) {
                        Part part = schematic.get(key);
                        for (Point p : part.coordinates) {
                            if ((p.x == x + 1 || p.x == x || p.x == x - 1) && (p.y == y + 1 || p.y == y || p.y == y - 1)) {
                                adjacentParts.add(part);
                            }
                        }
                    }
                    if (adjacentParts.size() == 2) {
                        Iterator<Part> it = adjacentParts.iterator();
                        int partNumber1 = Integer.parseInt(it.next().getNumber());
                        int partNumber2 = Integer.parseInt(it.next().getNumber());
                        gears.add(partNumber1 * partNumber2);
                    }
                }
            }
        }
        return gears;
    }

    private static String parseFullNumber(String line, int x) {
        int startIndex = x, endIndex = x;
        while (startIndex > 0) {
            Character character = line.toCharArray()[startIndex - 1];
            if (character < '0' || character > '9') {
                break;
            }
            startIndex--;
        }
        while (endIndex < line.length()) {
            Character character = line.toCharArray()[endIndex];
            if (character < '0' || character > '9') {
                break;
            }
            endIndex++;
        }
        StringBuilder number = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            number.append(line.toCharArray()[i]);
        }
        return number.toString();
    }

    @Data
    private static class Part {
        final String number;
        final List<Point> coordinates = new ArrayList<>();
    }

    @Data
    public static class Point {
        final int x;
        final int y;
    }
}


