package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2025/input.day04.txt");
        assertEquals(1495, Day04.solvePart1(input));
        assertEquals(8768, Day04.solvePart2(input));
    }
}
