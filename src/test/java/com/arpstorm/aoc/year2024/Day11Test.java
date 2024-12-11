package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day11.txt");
        assertEquals(193607L, Day11.solvePart1(input.get(0)));
        assertEquals(229557103025807L, Day11.solvePart2(input.get(0)));
    }
}
