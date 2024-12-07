package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day06.txt");
        assertEquals(5331, Day06.solvePart1(input));
        assertEquals(1812, Day06.solvePart2(input));
    }
}
