package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {

    @Test
    void testResults() {
        List<String> input = Utils.readInput("/year2022/input.day09.txt");
        assertEquals(5960, Day09.solvePart1(input));
        assertEquals(2327, Day09.solvePart2(input));
    }
}
