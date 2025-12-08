package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2025/input.day07.txt");
        assertEquals(1504L, Day07.solvePart1(input));
        assertEquals(5137133207830L, Day07.solvePart2(input));
    }
}
