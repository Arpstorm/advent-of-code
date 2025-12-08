package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2025/input.day06.txt");
        assertEquals(7644505810277L, Day06.solvePart1(input));
        assertEquals(12841228084455L, Day06.solvePart2(input));
    }
}
