package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day04.txt");
        assertEquals(25004, Day04.solvePart1(input));
        assertEquals(14427616, Day04.solvePart2(input));
    }
}
