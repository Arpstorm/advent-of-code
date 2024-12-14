package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day13.txt");
        assertEquals(28262, Day13.solvePart1(input));
        assertEquals(101406661266314L, Day13.solvePart2(input));
    }
}
