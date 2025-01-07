package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day16.txt");
        assertEquals(211692000, Day16_Dfs.solvePart1(input));
        assertEquals(6587, Day16_Dfs.solvePart2(input));
    }
}
