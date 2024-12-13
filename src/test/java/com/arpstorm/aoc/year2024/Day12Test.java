package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day12.txt");
        assertEquals(1489582, Day12.solvePart1(input));
        assertEquals(914966, Day12.solvePart2(input));
    }
}
