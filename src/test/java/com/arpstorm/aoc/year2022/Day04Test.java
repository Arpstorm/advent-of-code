package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    @Test
    void testResults() {
        List<String> input = Utils.readInput("/year2022/input.day04.txt");
        assertEquals(534, Day04.solvePart1(input));
        assertEquals(841, Day04.solvePart2(input));
    }
}
