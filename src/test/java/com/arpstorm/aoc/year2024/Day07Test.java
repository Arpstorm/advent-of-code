package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day07.txt");
        assertEquals(4555081946288L, Day07.solvePart1(input));
        assertEquals(227921760109726L, Day07.solvePart2(input));
    }
}
