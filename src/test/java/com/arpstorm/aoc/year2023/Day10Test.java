
package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day10.txt");
        assertEquals(7063, Day10.solvePart1(input));
        assertEquals(589, Day10.solvePart2(input));
    }
}
