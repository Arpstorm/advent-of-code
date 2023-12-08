package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;

import java.util.*;
import java.util.function.Predicate;

public class Day05 {

    private static List<long[]> seedToSoilMap = null;
    private static List<long[]> soilToFertilizerMap = null;
    private static List<long[]> fertilizerToWaterMap = null;
    private static List<long[]> waterToLightMap = null;
    private static List<long[]> lightToTemperatureMap = null;
    private static List<long[]> temperatureToHumidityMap = null;
    private static List<long[]> humidityToLocationMap = null;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> input = Utils.readInput("/year2023/input.day05.txt");

        long part1 = solvePart1(input);
        long half = System.currentTimeMillis();
        System.out.println("Part 1 (" + (half - start) / 1000 + " s): " + part1);

        long part2 = solvePart2(input);
        System.out.println("Part 2 (" + (System.currentTimeMillis() - half) / 1000 + " s): " + part2);

    }

    public static long solvePart1(List<String> input) {
        long[] seeds = Arrays.stream(input.get(0).split(": ")[1].split(" "))
                .mapToLong(Long::parseLong).toArray();
        initMappings(input);
        long minLocation = Long.MAX_VALUE;
        for (long seed : seeds) {
            long location = combinedMaps(seed);
            if (location < minLocation) minLocation = location;
        }
        return minLocation;
    }

    public static long solvePart2(List<String> input) {
        long[] seeds = Arrays.stream(input.get(0).split(": ")[1].split(" "))
                .mapToLong(Long::parseLong).toArray();
        initMappings(input);
        long minLocation = Long.MAX_VALUE;
        for (int i = 0; i < seeds.length; i+=2) {
            long seedStart = seeds[i];
            long seedRange = seeds[i + 1];
            for (long seed = seedStart; seed < seedStart + seedRange; seed++) {
                long location = combinedMaps(seed);
                if (location < minLocation) minLocation = location;
            }
            System.out.println("Finished seed range " + (i/2 + 1));
        }
        return minLocation;
    }

    private static long combinedMaps(long seed) {
        long soil = applyMapping(seedToSoilMap, seed);
        long fertilizer = applyMapping(soilToFertilizerMap, soil);
        long water = applyMapping(fertilizerToWaterMap, fertilizer);
        long light = applyMapping(waterToLightMap, water);
        long temperature = applyMapping(lightToTemperatureMap, light);
        long humidity = applyMapping(temperatureToHumidityMap, temperature);
        return applyMapping(humidityToLocationMap, humidity);
    }

    private static void initMappings(List<String> input) {
        Iterator<String> it = input.iterator();
        while (it.hasNext()) {
            String line = it.next();
            if (line.contains("seed-to-soil map:")) {
                seedToSoilMap = parseMap(it);
            }
            if (line.contains("soil-to-fertilizer map:")) {
                soilToFertilizerMap = parseMap(it);
            }
            if (line.contains("fertilizer-to-water map:")) {
                fertilizerToWaterMap = parseMap(it);
            }
            if (line.contains("water-to-light map:")) {
                waterToLightMap = parseMap(it);
            }
            if (line.contains("light-to-temperature map:")) {
                lightToTemperatureMap = parseMap(it);
            }
            if (line.contains("temperature-to-humidity map:")) {
                temperatureToHumidityMap = parseMap(it);
            }
            if (line.contains("humidity-to-location map:")) {
                humidityToLocationMap = parseMap(it);
            }
        }
    }

    private static long applyMapping(List<long[]> map, long input) {
        for (long[] m : map) {
            if (input >= m[1] && input < m[1] + m[2]) {
                return input + m[0] - m[1];
            }
        }
        return input;
    }

    private static List<long[]> parseMap(Iterator<String> it) {
        List<long[]> map = new ArrayList<>();
        while (it.hasNext()) {
            String line = it.next();
            if (line.isEmpty()) break;
            long[] mapRow = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();;
            map.add(mapRow);
        }
        return map;
    }
}
