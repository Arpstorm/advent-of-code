package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2025/input.day01.txt");
        assertEquals(1040, Day01.solvePart1(input));
        assertEquals(6027, Day01.solvePart2(input));
    }
}
