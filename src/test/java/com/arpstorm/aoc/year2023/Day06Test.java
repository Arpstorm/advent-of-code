package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day06.txt");
        assertEquals(1624896, Day06.solvePart1());
        assertEquals(32583852, Day06.solvePart2());
    }
}
