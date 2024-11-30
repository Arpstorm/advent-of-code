package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    @Test
    void testResults() {
        List<String> input = Utils.readInput("/year2022/input.day05.txt");
        List<List<String>> stacks = Day05.getInitialStacks(input);
        List<List<Integer>> operations = Day05.getOperations(input);
        assertEquals("RLFNRTNFB", Day05.solvePart1(stacks, operations));

        stacks = Day05.getInitialStacks(input);
        assertEquals("MHQTLJRLB", Day05.solvePart2(stacks, operations));
    }
}
