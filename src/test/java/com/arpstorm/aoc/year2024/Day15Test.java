package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day15.txt");
        assertEquals(1514353, Day15.solvePart1(input));
        assertEquals(1533076, Day15.solvePart2(input));
    }
}
