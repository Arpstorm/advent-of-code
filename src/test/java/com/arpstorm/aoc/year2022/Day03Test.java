package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    @Test
    void testResults() {
        List<String> input = Utils.readInput("/year2022/input.day03.txt");
        assertEquals(7863, Day03.solvePart1(input));
        assertEquals(2488, Day03.solvePart2(input));
    }
}
