package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day14.txt");
        assertEquals(211692000, Day14.solvePart1(input));
        assertEquals(6587, Day14.solvePart2(input));
    }
}
