package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    @Test
    void verifyResults() {
        String input = Utils.readInputAsString("/year2024/input.day03.txt");
        assertEquals(173419328, Day03.solvePart1(input));
        assertEquals(90669332, Day03.solvePart2(input));
    }
}
