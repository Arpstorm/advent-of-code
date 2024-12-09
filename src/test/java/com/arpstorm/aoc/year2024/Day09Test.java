package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day09.txt");
        assertEquals(6283170117911L, Day09.solvePart1(input.get(0)));
        assertEquals(6307653242596L, Day09.solvePart2(input.get(0)));
    }
}
