package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day01.txt");
        Day01.initGroups(input);
        assertEquals(1873376, Day01.solvePart1());
        assertEquals(18997088, Day01.solvePart2());
    }
}
