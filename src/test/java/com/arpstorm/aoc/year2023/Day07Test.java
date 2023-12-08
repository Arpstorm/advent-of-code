package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day07.txt");
        assertEquals(253603890, Day07.solvePart1(input));
        assertEquals(253630098, Day07.solvePart2(input));
    }
}
