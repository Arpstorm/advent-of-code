package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2025/input.day03.txt");
        assertEquals(17432, Day03.solvePart1(input));
        assertEquals(173065202451341L, Day03.solvePart2(input));
    }
}
