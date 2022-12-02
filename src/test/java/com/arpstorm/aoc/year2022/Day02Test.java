package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    @Test
    void testResults() {
        List<String> input = Utils.readInput("/year2022/input.day02.txt");
        assertEquals(13809, Day02.solvePart1(input));
        assertEquals(12316, Day02.solvePart2(input));
    }
}
