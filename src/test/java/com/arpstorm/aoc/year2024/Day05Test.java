package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day05.txt");
        assertEquals(5509, Day05.solvePart1(input));
        assertEquals(4407, Day05.solvePart2(input));
    }
}
