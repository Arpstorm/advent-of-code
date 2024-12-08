package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day08.txt");
        assertEquals(371, Day08.solvePart1(input));
        assertEquals(1229, Day08.solvePart2(input));
    }
}
