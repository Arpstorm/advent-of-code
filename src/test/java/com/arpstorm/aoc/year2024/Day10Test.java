package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day10.txt");
        assertEquals(674, Day10.solvePart1(input));
        assertEquals(1372, Day10.solvePart2(input));
    }
}
